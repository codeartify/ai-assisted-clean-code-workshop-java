package com.workshop.architecture.fitness;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class MembershipReactivationPolicyTest {

    private final MembershipReactivationPolicy policy = new MembershipReactivationPolicy();

    @Test
    void allowsReactivationWhenEveryRelevantInvoiceIsPaidWithinTheMembershipPeriod() {
        MembershipEntity membership = suspendedMembership(
                "NON_PAYMENT",
                LocalDate.parse("2027-01-01")
        );

        boolean allowed = policy.allowsReactivation(
                membership,
                LocalDate.parse("2026-02-10"),
                true
        );

        assertThat(allowed).isTrue();
    }

    @Test
    void rejectsReactivationWhileARelevantInvoiceRemainsOpen() {
        MembershipEntity membership = suspendedMembership(
                "NON_PAYMENT",
                LocalDate.parse("2027-01-01")
        );

        boolean allowed = policy.allowsReactivation(
                membership,
                LocalDate.parse("2026-02-10"),
                false
        );

        assertThat(allowed).isFalse();
    }

    @Test
    void rejectsReactivationAfterTheMembershipPeriod() {
        MembershipEntity membership = suspendedMembership(
                "NON_PAYMENT",
                LocalDate.parse("2025-12-31")
        );

        boolean allowed = policy.allowsReactivation(
                membership,
                LocalDate.parse("2026-02-10"),
                true
        );

        assertThat(allowed).isFalse();
    }

    @Test
    void rejectsReactivationForAnotherSuspensionReason() {
        MembershipEntity membership = suspendedMembership(
                "MANUAL_REVIEW",
                LocalDate.parse("2027-01-01")
        );

        boolean allowed = policy.allowsReactivation(
                membership,
                LocalDate.parse("2026-02-10"),
                true
        );

        assertThat(allowed).isFalse();
    }

    private MembershipEntity suspendedMembership(String reason, LocalDate endDate) {
        return new MembershipEntity(
                UUID.randomUUID(),
                "11111111-1111-1111-1111-111111111111",
                "aaaaaa12-aaaa-aaaa-aaaa-aaaaaaaaaa12",
                999,
                12,
                "SUSPENDED",
                reason,
                LocalDate.parse("2026-01-01"),
                endDate
        );
    }
}
