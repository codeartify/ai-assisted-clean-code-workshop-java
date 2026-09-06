# Gate 6 — Decide where the payment code belongs

Start: `ai-day1-05-safe-refactoring`

Sample solution: `ai-day1-06-vsa-blast-radius`

Skill: `modern-application-architecture`.

## What this adds

Gate 5 extracted the payment operation. Now make related code easy to find and
name the dependencies that can affect other features. Add a check for the
dependency rule you choose.

**Blast radius** means the code and behavior a change could affect. Putting
files in one package helps navigation; it does not erase shared dependencies.

| Idea | What it helps with | A reason to use it |
| --- | --- | --- |
| Vertical Slice | Groups code for one use case | Find and change payment-specific code together |
| Hexagonal / ports and adapters | Separates a use case from external technology | Two providers need different adapters behind a defined contract |
| Clean Architecture | Makes outer technology depend on inner rules | Keep long-lived policy independent of delivery and persistence details |

Choose based on a current problem. Unknown authentication is missing knowledge,
not a reason to invent an interface. Keep real shared business knowledge shared.

## Exercise 6A — assess placement (8 minutes)

1. Ask the agent to trace `MembershipController.paymentReceived`,
   `RecordMembershipPayment`, `MembershipReactivationPolicy`, their callers,
   shared types, and tests. Do not edit yet.
2. For each file, require **MOVE**, **KEEP SHARED**, or **LEAVE ALONE**, with a
   reason. “Keep for now” is valid when it explicitly bounds this exercise.
3. Compare a new reactivation rule with a new HTTP identifier: which files and
   tests would each change affect?
4. Review the proposal and choose the smallest useful change. Challenge each
   proposed port or extra layer: what current problem does it solve?

## Prompt 1 — assess before moving

```text
Work from ai-day1-05-safe-refactoring. Use modern-application-architecture to
review where the payment callback code belongs. Do not edit yet.

Trace MembershipController.paymentReceived, RecordMembershipPayment,
MembershipReactivationPolicy, their callers, shared types, and tests. For each
file propose MOVE, KEEP SHARED, or LEAVE ALONE with a reason. Explain what
would change for a new reactivation rule versus a new HTTP identifier.

Compare grouping by use case with adding ports or layers. Recommend the
smallest useful change. Keep shared business knowledge shared. Do not invent
authentication or resolve undefined invoice policy. Name the shared
dependencies that can still affect other operations. Propose a dependency
rule and explain how a test could check it. Wait for my approval.
```

## Exercise 6B — move and check (12 minutes)

1. Approve the specific files. Have the agent move only that code and update
   the necessary imports.
2. Preserve HTTP behavior, invoice selection, stored outcomes, and the complete
   transaction around recording payment and possible reactivation.
3. Add the agreed dependency test. Demonstrate a temporary violation causing
   an assertion failure; remove it and rerun. A compilation error is different
   evidence from a failing dependency assertion.
4. Run the behavior suites and full Java suite. Inspect the remaining shared
   dependencies and record the check's blind spots.

## Prompt 2 — approve the bounded change

```text
Proceed with the payment-specific moves I approved. Keep the public endpoint,
response/error behavior, shared types, stored outcomes, and complete
transaction unchanged. Add the agreed dependency check. Demonstrate a
temporary forbidden dependency failing that check, then remove it and rerun.

Run the fast policy tests, HTTP/JPA tests, boundary test, and full Java suite.
Report moved/shared/unchanged files, actual results, the check's limitations,
and why you stopped. Record the decision in workshop/architecture-decision.md.
Stop before redesigning other membership operations.
```

## Expected output and checks

- A file-by-file placement proposal and your approval.
- A bounded package change that preserves behavior.
- A dependency check with observed failure/recovery and stated limitations.
- `workshop/architecture-decision.md` with the shared dependencies and stop point.

Run from `java/`, after the boundary test exists:

```sh
mvn -q -Dtest=MembershipReactivationPolicyTest test
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q -Dtest=VerticalSliceBoundaryTest test
mvn -q test
```

## Compare, debrief, and next step

Open the [Gate 6 worked solution on branch 06](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-06-vsa-blast-radius/workshop/solutions/gate6-solution.md)
after the placement decision. What became easier to find? What stayed shared?
What can your test miss? Would changing a shared entity still affect other use
cases?

[Gate 7](gate7-exercise.md) records the useful decisions for future agents.
**Put related code together, keep shared knowledge shared, and test the rule
you chose.**
