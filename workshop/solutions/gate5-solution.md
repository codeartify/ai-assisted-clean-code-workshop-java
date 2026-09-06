# Gate 5 — Worked diagnosis and extraction

Reveal after [the participant exercise](../gate5-exercise.md).
Compare `ai-day1-04-test-design` with `ai-day1-05-safe-refactoring`.
The detailed assessment is the branch's
[refactoring-log.md](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-05-safe-refactoring/workshop/refactoring-log.md).

## One defensible ranking

| Priority | Finding and evidence | Decision |
| --- | --- | --- |
| 1 | `paymentReceived` validates identifiers, resolves an invoice, records payment, loads membership, coordinates reactivation, persists, and builds the response | Select the mixed-responsibility problem: high impact, medium effort, behavior tests available |
| 2 | Identifier fallback and response wording are interleaved with the workflow | Name steps inside the selected extraction |
| 3 | Status, reason, and identifiers are strings across the wider model | Defer broad value/domain modelling until its meaning and scope are justified |
| 4 | A proposed auth port or universal handler framework has no established contract/variation | Reject speculative additions; do not pretend those abstractions already exist |

The highest-value move extracts `RecordMembershipPayment` in the existing
`fitness` package. `MembershipController.paymentReceived` delegates the HTTP
request. `MembershipReactivationPolicy` remains the existing decision object.

## Replay the sequence

1. Establish feedback from the four fast policy and nine callback test methods.
2. Move the callback operation into `RecordMembershipPayment` while preserving
   lookup order, state outcomes, retry handling, and response messages.
3. Put the complete transaction on `RecordMembershipPayment.handle`; leave the
   controller as the HTTP adapter.
4. Name the internal steps: `validateIdentifier`, `findBillingReference`,
   `findMembership`, `reactivateWhenAllowed`, and `responseMessage`.
5. Run policy feedback, callback feedback, and the full suite. Review the diff
   after meaningful steps rather than accepting a whole-controller rewrite.

These are trainer replay steps, not a claim that the published branch contains
one historical commit per step. The operation retains Spring/JPA dependencies
and HTTP exception types; this is not a complete ports-and-adapters redesign.

Stop when the controller delegates, the operation owns the complete workflow
and transaction, the policy still expresses the rule, and existing behavior
checks pass. Package movement belongs to Gate 6.
