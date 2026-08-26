package com.workshop.architecture.fitness;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/** Coordinates the application operation behind the payment callback. */
@Service
public class RecordMembershipPayment {

    private final MembershipRepository membershipRepository;
    private final MembershipBillingReferenceRepository billingReferenceRepository;
    private final MembershipReactivationPolicy reactivationPolicy;

    public RecordMembershipPayment(
            MembershipRepository membershipRepository,
            MembershipBillingReferenceRepository billingReferenceRepository
    ) {
        this.membershipRepository = membershipRepository;
        this.billingReferenceRepository = billingReferenceRepository;
        this.reactivationPolicy = new MembershipReactivationPolicy();
    }

    @Transactional
    public PaymentReceivedResponse handle(PaymentReceivedRequest request) {
        validateIdentifier(request);

        MembershipBillingReferenceEntity billingReference = findBillingReference(request);
        Instant paidAt = request.paidAt() == null ? Instant.now() : request.paidAt();
        boolean paymentWasAlreadyRecorded = billingReference.isPaid();

        if (!paymentWasAlreadyRecorded) {
            billingReference.markPaid(paidAt);
            billingReference = billingReferenceRepository.save(billingReference);
        }

        MembershipEntity membership = findMembership(billingReference.getMembershipId());
        String previousMembershipStatus = membership.getStatus();
        boolean reactivated = reactivateWhenAllowed(membership, paidAt);

        return new PaymentReceivedResponse(
                paidAt,
                membership.getId().toString(),
                billingReference.getId().toString(),
                previousMembershipStatus,
                membership.getStatus(),
                reactivated,
                responseMessage(membership, paymentWasAlreadyRecorded, reactivated)
        );
    }

    private void validateIdentifier(PaymentReceivedRequest request) {
        if (isBlank(request.externalInvoiceId())
                && isBlank(request.externalInvoiceReference())
                && isBlank(request.membershipId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "At least one invoice or membership identifier must be provided"
            );
        }
    }

    private MembershipBillingReferenceEntity findBillingReference(PaymentReceivedRequest request) {
        MembershipBillingReferenceEntity billingReference = null;

        if (!isBlank(request.externalInvoiceId())) {
            billingReference = billingReferenceRepository
                    .findByExternalInvoiceId(request.externalInvoiceId())
                    .orElse(null);
        }

        if (billingReference == null && !isBlank(request.externalInvoiceReference())) {
            billingReference = billingReferenceRepository
                    .findByExternalInvoiceReference(request.externalInvoiceReference())
                    .orElse(null);
        }

        if (billingReference == null && !isBlank(request.membershipId())) {
            billingReference = billingReferenceRepository
                    .findByMembershipId(UUID.fromString(request.membershipId()))
                    .stream()
                    .findFirst()
                    .orElse(null);
        }

        if (billingReference == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No billing reference was found");
        }
        return billingReference;
    }

    private MembershipEntity findMembership(UUID membershipId) {
        return membershipRepository.findById(membershipId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Membership %s was not found".formatted(membershipId)
                ));
    }

    private boolean reactivateWhenAllowed(MembershipEntity membership, Instant paidAt) {
        boolean allRelevantInvoicesArePaid = billingReferenceRepository
                .findByMembershipId(membership.getId())
                .stream()
                .allMatch(MembershipBillingReferenceEntity::isPaid);

        if (!reactivationPolicy.allowsReactivation(
                membership,
                paidAt.atZone(ZoneOffset.UTC).toLocalDate(),
                allRelevantInvoicesArePaid
        )) {
            return false;
        }

        membership.reactivateAfterPayment();
        membershipRepository.save(membership);
        return true;
    }

    private String responseMessage(
            MembershipEntity membership,
            boolean paymentWasAlreadyRecorded,
            boolean reactivated
    ) {
        if (membership.isCancelled()) {
            return "Payment recorded; membership is cancelled and remains unchanged";
        }
        if (reactivated) {
            return "Payment recorded; membership reactivated";
        }
        if (paymentWasAlreadyRecorded) {
            return "Payment was already recorded; membership status unchanged";
        }
        return "Payment recorded; membership status unchanged";
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
