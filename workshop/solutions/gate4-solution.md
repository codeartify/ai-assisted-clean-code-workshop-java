# Gate 4 — Worked test design

Reveal after [the participant exercise](../gate4-exercise.md).
Compare `ai-day1-03-behaviour-specification` with `ai-day1-04-test-design`.

## What gets extracted

The reactivation decision moves from `MembershipController` into
`fitness.MembershipReactivationPolicy`. The controller still coordinates the
callback; extracting that operation belongs to Gate 5.

`MembershipReactivationPolicyTest` contains four fast tests:

| Conditions | Policy result |
| --- | --- |
| All relevant fixture invoices paid, non-payment suspension, period not ended | Allow reactivation |
| A relevant invoice remains unpaid | Reject reactivation |
| Membership period ended | Reject reactivation |
| A different suspension reason | Reject reactivation |

The policy receives a payment date and an all-paid boolean. Its tests use real
Java objects and fixed dates; they start no Spring context or database and need
no doubles. They still use the JPA-annotated `MembershipEntity` as data. This is
fast execution without framework startup, not a fully independent domain model.

## What stays slower

All nine `PaymentReceivedControllerTest` methods remain. They use MockMvc,
Spring, and JPA/H2 for request mapping, lookup, response status/fields, and
normal-path stored-state behavior. H2 is in process; a real HTTP server is not
started by MockMvc.

**The sample removes no HTTP/JPA test and no interaction assertion.** Branch 03
has no repository call-count assertions to remove. The first useful split adds
fast rule feedback; it does not yet prune every overlapping scenario.

## Demonstration and interpretation

Run from `java/`:

```sh
mvn -q -Dtest=MembershipReactivationPolicyTest test
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q test
```

- A policy-test failure points to the decision, its inputs, or the test's
  expected result. Inspect those first.
- Fast tests passing while callback tests fail points toward HTTP mapping,
  invoice lookup, orchestration, persistence, or wiring; inspect the actual
  failure before deciding.
- Both passing still leaves untested cases. Neither suite demonstrates
  production-database compatibility, failure-path rollback, or real provider
  authentication. The new error cases currently assert status, not body.

No very-slow provider/authentication test is fabricated. Unknown integration
contracts need evidence first. Gate 5 can now run the fast suite after small
moves and the HTTP/JPA suite at application-wiring checkpoints.
