package com.workshop.architecture.fitness.payment.record;

import com.workshop.architecture.fitness.MembershipEntity;
import java.time.LocalDate;

/**
 * The business decision for lifting a non-payment suspension.
 *
 * <p>This class is deliberately free of Spring, persistence, HTTP, and time
 * sources so that the rule gives a coding agent fast, deterministic feedback.</p>
 */
public final class MembershipReactivationPolicy {

    public boolean allowsReactivation(
            MembershipEntity membership,
            LocalDate paymentDate,
            boolean allRelevantInvoicesArePaid
    ) {
        return allRelevantInvoicesArePaid
                && membership.isSuspendedForNonPayment()
                && !paymentDate.isAfter(membership.getEndDate());
    }
}
