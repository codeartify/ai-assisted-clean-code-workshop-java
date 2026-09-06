# Gate 1 — Worked review

Reveal after [the participant exercise](../gate1-exercise.md).
Compare `ai-day1-00-start` with `ai-day1-01-unguided-baseline`.

## What the sample actually changes

`MembershipController.paymentReceived` now loads the membership's billing
references, calls `allMatch(MembershipBillingReferenceEntity::isPaid)`, and
includes that result in the reactivation condition. It adds no callback test.

Useful: the agent found existing APIs and localized the change. It retained the
HTTP records and the existing single-invoice, retry, cancellation, ended-period,
and other-suspension behavior covered by six test methods.

## Decisions to mark

| Decision | Origin | Review question |
| --- | --- | --- |
| Check every stored billing reference | New all-paid query | Are voided, disputed, historical, or future invoices relevant? |
| Empty stream means all paid | Consequence of the new `allMatch` | Can this callback actually reach an empty collection? |
| Membership-only lookup chooses an unordered first result | Inherited | Which invoice does the caller mean? |
| Invoice and membership updates have no enclosing application transaction | Inherited omission retained by the change | Could a later failure leave a partial update? |
| No established callback authentication | Existing contract gap | Who may change this financial and membership state? |
| No new multi-invoice test | Generated change omits it | Would the tests distinguish any-paid from all-paid? |

## One defensible ranking and verdict

1. Partial state if invoice payment commits but reactivation fails.
2. Incorrect activation or continued suspension if invoice relevance is wrong.
3. Unauthorized callbacks changing invoice/member state when authentication is
   undefined. Retry idempotency is not proof of authenticated delivery.

**HOLD.** Keep the useful work, but do not accept it yet. Another ranking is
valid if its consequences and assumptions are explained.

This gate identifies questions. It has not yet proved reachability of the
empty-set concern, transaction rollback, or production security. Gate 2 does
the investigation. Compare two participant diffs to show that nondeterminism
changes the implementation, not the need to review decisions.
