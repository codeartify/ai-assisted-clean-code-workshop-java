---
name: membership-payment-change
description: Safely plan, implement, test, and review changes to the membership payment callback and reactivation behavior in this repository. Use when work touches payment-received requests, billing-reference resolution, invoice payment state, non-payment suspension, membership reactivation, callback retries, or the record-payment vertical slice.
---

# Membership payment change workflow

Use this repository-specific skill to orchestrate the general Clean Code,
non-brittle testing, and architecture skills. Repository facts remain in code,
tests, `AGENTS.md`, and the evidence documents; this skill defines the repeatable
workflow.

## 1. Establish the contract before proposing code

Inspect, in this order:

1. `java/pom.xml` for installed Java and Spring versions.
2. `PaymentReceivedRequest`, `PaymentReceivedResponse`, and
   `MembershipController.paymentReceived` for the public HTTP contract.
3. `fitness/payment/record/RecordMembershipPayment` for orchestration.
4. `MembershipBillingReferenceEntity`, `MembershipEntity`, and their repositories
   for persisted state and available queries.
5. `PaymentReceivedControllerTest` and `MembershipReactivationPolicyTest` for
   executable behavior.
6. `workshop/contract-evidence.md` for known gaps that still need an owner.

Do not answer framework questions from memory when the installed version, source,
or a focused executable test can answer them.

## 2. Choose the next behavior test with ZOMBIES

Before generating tests or implementation, complete
`workshop/behaviour-matrix-template.md`:

1. move from Zero to One to Many or more complex cases;
2. at each step scan boundary behavior, interface definition, and exceptional
   behavior;
3. choose the smallest unproven scenario that can make a wrong rule and the
   intended rule produce different results;
4. keep both the scenario and the production change simple.

For this callback, one invoice cannot distinguish “reactivate after any payment”
from “reactivate after all relevant invoices are paid.” The first revealing case
uses two unpaid invoices and pays only one. Also consider duplicate delivery,
cancelled and ended memberships, other suspension reasons, missing and unknown
identifiers, invalid identifiers when parsing changes, failure atomicity, and
callback authentication.

If “relevant invoice,” identifier precedence, authentication, or failure behavior
is not proven, record the question and ask the responsible person. Do not invent a
product or security rule.

## 3. Choose tests with `non-brittle-tests`

Invoke the general `non-brittle-tests` skill. Protect observable behavior through
the public surface when HTTP, JPA, or transaction wiring is the risk. Protect the
reactivation decision with fast deterministic policy tests. Prefer persisted state
and response assertions over repository call counts or private-method tests.

Run the focused checks first from `java/`:

```bash
mvn -q -Dtest=MembershipReactivationPolicyTest,PaymentReceivedControllerTest test
```

## 4. Make one safe change with `clean-code-refactoring`

Invoke `clean-code-refactoring` in Full Format:

1. restate behavior to preserve;
2. identify one primary smell or change pressure;
3. make the smallest useful move;
4. run focused checks after each meaningful step;
5. re-check names, responsibilities, cohesion, and coupling;
6. stop at the stated stop condition.

Do not create a universal billing framework, generic command hierarchy, or new
port merely because the pattern is familiar.

## 5. Reconsider placement only when pressure is architectural

Invoke `modern-application-architecture` when the change crosses a use-case
boundary, changes protected rules, or expands the expected blast radius. Classify
the feature, list protected rules and change axes, then choose the lightest
structure. A payment slice may use explicit shared membership concepts but must
not depend directly on another payment slice.

Run the boundary check when placement changes:

```bash
mvn -q -Dtest=VerticalSliceBoundaryTest test
```

## 6. Verify and stop

Run the full Java suite before completion:

```bash
mvn -q test
```

Stop when:

- requested observable behavior is protected and passes;
- the HTTP contract is unchanged unless explicitly requested;
- invoice and membership updates remain one transaction;
- names express business rules rather than repository mechanics;
- the slice boundary test passes when placement changed;
- unresolved product or security questions are visible;
- no speculative abstraction remains in the diff.

## Completion report

Report:

- behavior added or preserved;
- evidence inspected;
- tests added or changed, grouped by fast/slow/very slow;
- architecture boundary affected, if any;
- commands run and results;
- unresolved product, security, or ownership questions;
- the stop condition that ended the change.
