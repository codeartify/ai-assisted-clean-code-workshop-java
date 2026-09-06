# Gate 6 — Where should the payment code live?

Start from `ai-day1-05-safe-refactoring`. Gate 5 extracted the operation;
Gate 6 decides where related code belongs and which dependencies to forbid.

## Participant prompt

Start from ai-day1-05-safe-refactoring. Use modern-application-architecture to review where the payment callback code belongs. Do not edit yet.

Trace MembershipController.paymentReceived, RecordMembershipPayment, MembershipReactivationPolicy, their callers, shared types, and tests. For each file propose MOVE, KEEP SHARED, or LEAVE ALONE, with a reason. Explain what would change for a new reactivation rule versus a new HTTP identifier.

Compare grouping by use case with adding ports or layers. Recommend the smallest useful change; do not assume every pattern is needed. Keep real shared business knowledge shared. Do not invent authentication or resolve undefined invoice policy.

Wait for approval. After approval, move only the agreed code, preserve HTTP behavior and transaction scope, and add a test for the agreed dependency rule. Demonstrate that the test detects a temporary forbidden dependency, then remove it. Run the policy tests, callback tests, boundary test, and full Java suite.

Report moved/shared/unchanged files, test results, limitations of the dependency check, and why you stopped.

## Example approval prompt

Proceed with the agreed payment-specific moves only. Keep the public endpoint,
response/error behavior, shared types, and complete transaction unchanged.
Add the agreed dependency check, demonstrate failure and recovery, and report
its blind spots. Stop before redesigning other membership operations.

## Deliverables

1. A file list: MOVE, KEEP SHARED, or LEAVE ALONE, with reasons.
2. A small reviewed package change, without new business behavior.
3. A dependency test, its observed failure/recovery, and its limitations.
4. Actual command results; mark unexecuted checks explicitly.

## Debrief

What became easier to find? Which dependency remains shared? Would changing that
shared type still affect other use cases? What can the dependency test miss?

The sample is on `ai-day1-06-vsa-blast-radius` in
`workshop/architecture-decision.md`. Read it after the exercise.
