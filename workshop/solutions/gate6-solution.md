# Gate 6 — Worked placement and dependency check

Reveal after [the participant exercise](../gate6-exercise.md).
Compare `ai-day1-05-safe-refactoring` with `ai-day1-06-vsa-blast-radius`.
See the branch's [architecture-decision.md](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-06-vsa-blast-radius/workshop/architecture-decision.md)
for the detailed decision.

## What moves, and what stays

| File or group | Sample decision |
| --- | --- |
| `RecordMembershipPayment` and `MembershipReactivationPolicy` | Move from `fitness` to `fitness.payment.record` |
| `MembershipReactivationPolicyTest` | Move to the matching test package; the four scenarios remain, with fixture formatting simplified |
| `MembershipController` | Keep shared; update its operation import |
| Payment request/response records | Keep their current location for this exercise |
| Membership/billing entities and repositories | Keep shared; do not clone the same stored concepts per slice |
| `PaymentReceivedControllerTest` | Keep its location and behavior assertions |
| `VerticalSliceBoundaryTest` | Add a source-import check |

A reactivation-rule change now starts with the grouped policy and its fast
tests. A new HTTP identifier still involves the shared request/controller and
callback tests. A shared entity change can still affect other membership use
cases. This is a first package boundary, not complete isolation from HTTP,
Spring, or JPA. The transaction remains on the whole application operation.

## Show exactly what the dependency test does

`VerticalSliceBoundaryTest` scans Java source under `fitness/payment/record`.
It rejects ordinary imports beginning with
`com.workshop.architecture.fitness.payment.` unless they stay in `payment.record`.

Run the normal boundary command from `java/` first:

```sh
mvn -q -Dtest=VerticalSliceBoundaryTest test
```

For the trainer demonstration:

1. Create a temporary, valid public `OtherOperation` class in package
   `com.workshop.architecture.fitness.payment.other`.
2. Add its ordinary import to `RecordMembershipPayment` inside `payment.record`.
3. Run the command again and show the forbidden-import assertion failing.
   Do not count an unresolved-symbol compiler error as this demonstration.
4. Remove the temporary class and import, rerun, and check the diff contains
   neither temporary change. Run policy, callback, and full tests as in the guide.

The check misses fully qualified references, static-import forms, reflection,
and dependencies through shared code. It proves no HTTP or persisted-state
outcome. Name these limits rather than claiming that every cross-slice
dependency is prevented.

Stop after the agreed grouping and check. No undefined authentication port,
provider abstraction, event, CQRS split, or extra architectural layer is added.
