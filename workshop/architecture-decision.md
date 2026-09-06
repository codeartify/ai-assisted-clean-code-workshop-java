# Gate 6 sample — Put payment code together

## Decision

Group the payment-recording operation and reactivation policy in
`fitness.payment.record`. Keep genuinely shared types where they are.
This is a small first step toward organizing by use case, not a full conversion
to Hexagonal or Clean Architecture.

## What moves from branch 05 to branch 06?

| File | Decision | Reason |
| --- | --- | --- |
| RecordMembershipPayment | MOVE from fitness to fitness.payment.record | Names and coordinates this payment use case. |
| MembershipReactivationPolicy | MOVE to the same package | Owns the payment-specific reactivation decision. |
| MembershipReactivationPolicyTest | MOVE to the matching test package | Keeps fast rule feedback near the rule. |
| MembershipController | KEEP for now; update its import | The shared HTTP controller still serves other operations. |
| PaymentReceivedRequest / PaymentReceivedResponse | KEEP for now | Avoid expanding the exercise into API-type redesign. |
| MembershipEntity / MembershipBillingReferenceEntity | KEEP SHARED | Other operations use the same stored concepts; do not clone them per slice. |
| Existing repositories | KEEP SHARED | This exercise does not require new persistence interfaces. |
| PaymentReceivedControllerTest | LEAVE ALONE | Protects observable HTTP and stored-state behavior across the move. |
| VerticalSliceBoundaryTest | ADD | Detects imports from record-payment code into other payment packages. |

## Why this is enough for this exercise

A developer or agent can find the payment operation and policy in one package.
A new reactivation rule starts there; a new HTTP identifier still involves the
shared controller and request types. Changing a shared entity can still affect
pause, cancel, or other operations. A package does not erase that coupling.

The operation keeps its Spring transaction and HTTP exception types.
The policy runs without starting Spring but accepts a JPA entity. Neither fact
supports claiming full framework independence.

## What does the boundary test actually check?

`VerticalSliceBoundaryTest` scans Java source under `fitness/payment/record`.
It rejects import lines beginning
`com.workshop.architecture.fitness.payment.` unless they stay inside
`payment.record`.

This is a source-import smoke check, not a complete dependency analysis.
It does not detect fully qualified references, reflection, dependencies through
shared code, or every other way of coupling features.

## Trainer demonstration

Run from `java/` with Java 21 and Maven:

```sh
mvn -q -Dtest=MembershipReactivationPolicyTest test
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q -Dtest=VerticalSliceBoundaryTest test
mvn -q test
```

Report actual output, not expected counts.

To demonstrate the import detector without confusing a compiler error with a
test failure:

1. Compile and run the boundary test normally first.
2. Temporarily append the following scanner input to a Java source file inside
   the inspected package:
   `import com.workshop.architecture.fitness.payment.other.OtherOperation;`
3. Run `mvn -q -Dtest=VerticalSliceBoundaryTest surefire:test`, which runs the
   already compiled test against the changed source text. Confirm failure on
   the forbidden-import assertion.
4. Remove the temporary line immediately. It is artificial scanner input, not
   valid application code; do not commit it.
5. Rerun the normal boundary-test command, then check the diff is clean of the
   temporary change.

Alternatively create a valid temporary sibling class/import and use the normal
test lifecycle. The important evidence is a failing dependency assertion, not
an unresolved-symbol compilation failure.

## What stays unchanged?

The endpoint and HTTP contract, callback lookup behavior, persisted invoice and
membership outcomes, and the complete transaction on
`RecordMembershipPayment.handle`. Existing behavior tests remain necessary:
the import check proves none of those runtime outcomes.

## What we do not add

No invented authentication interface, provider adapter, event, CQRS split,
command hierarchy, or standard set of architectural layers. A new port needs a
defined conversation and a concrete reason for decoupling. Unknown authentication,
invoice relevance, and ambiguous membership-only selection remain questions
for their owners, not permissions to invent rules.

## Stop condition and Gate 7 handoff

Stop after the agreed package moves, import update, dependency check, and
verification. Leave other membership operations alone. Gate 7 records the useful
placement rules, commands, examples, and limitations so the next agent can follow
them. Share repeated business knowledge deliberately; do not use slices as an
excuse to duplicate it.
