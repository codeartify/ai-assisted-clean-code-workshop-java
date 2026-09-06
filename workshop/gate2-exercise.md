# Gate 2 — Answer the review questions with evidence

Start: `ai-day1-01-unguided-baseline`

Sample solution: `ai-day1-02-verified-contracts`

Skills: repository instructions only; no specialist skills yet.

## What this adds

Gate 1 identified questions. Now resolve each one, make the smallest supported
correction, and name the questions the repository cannot answer.

A compiler checks types and available symbols. A test checks the observations
it exercises. Build files and owned interfaces establish technical contracts.
Business and security owners supply missing policy. These sources answer
different questions; they are not interchangeable levels of authority.

## Your task

1. Bring your Gate 1 question list. Inspect the prepared branch 01 implementation
   so everyone has the same code to investigate.
2. Use [contract-evidence-template.md](contract-evidence-template.md) to record
   each question, its evidence, result, and remaining uncertainty.
3. Classify each result as **CLOSE**, **FIX**, **TEST**, or **ESCALATE**.
4. Apply only corrections justified by the evidence. Leave behavior discovery
   and refactoring for later gates.
5. Run the narrow checks and report what they proved and what they did not.

Inspect `java/pom.xml`, `MembershipController.paymentReceived`,
`PaymentReceivedRequest`, `PaymentReceivedResponse`, the membership and billing
repositories/entities, `GlobalControllerAdvice`, and
`PaymentReceivedControllerTest`. Trace the local invoice-provider callback and
look for actual security configuration. A local provider simulator alone does
not establish a real provider's production contract.

## Sample agent prompt

```text
Work from ai-day1-01-unguided-baseline. Use normal repository instructions;
do not invoke the specialist workshop skills yet.

Investigate the review questions about the payment callback. Use
workshop/contract-evidence-template.md and produce workshop/contract-evidence.md.
For each claim, cite the exact build file, method, record, test, or owned API
contract; state what it establishes and what remains unknown.

Check Java and Spring Boot versions, identifier lookup, request/response and
error contracts, invoice/member updates and transaction scope, existing test
coverage, the invoice-provider callback, and authentication evidence.
Trace whether suspected edge cases are actually reachable on this callback.

Classify each question:
CLOSE: the evidence answers it; state the conditions and limits.
FIX: the correction follows from the established contract.
TEST: intended behavior is known, but executable coverage is missing.
ESCALATE: an owner or external contract must supply the missing rule.

Make only the smallest supported technical corrections. Preserve the public
API and established behavior. Do not invent authentication, invoice relevance,
or a rule for ambiguous membership-only lookup. Do not refactor or add the
Gate 3 behavior suite yet. Run compilation and the focused callback tests.
Report the commands, actual results, corrections, and stop-and-ask list.
```

## Expected output

- A completed evidence sheet with concrete answers, not just “verified.”
- A small correction diff, with its justification.
- Missing tests explicitly separated from missing business/security policy.
- A stop-and-ask list naming the question, the needed owner or contract, and the
  change that must wait for that answer.

Preserving an existing identifier fallback or error response for this exercise
does not approve it as a future product rule.

## Checks and stop condition

Run from `java/` with the version required by `pom.xml`:

```sh
mvn -q -DskipTests compile
mvn -q -Dtest=PaymentReceivedControllerTest test
```

After a production change, run `mvn -q test` before declaring completion, as
required by the repository instructions. Record an environment failure
separately from a compilation or assertion failure.

Stop after the supported corrections and evidence sheet. A green build does
not resolve the open product/security decisions or prove rollback under failure.

## Compare, debrief, and next step

Open the [Gate 2 worked solution on branch 02](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-02-verified-contracts/workshop/solutions/gate2-solution.md).
Which question was closed by tracing the code? Which led to a correction? Which
became a test candidate, and which still needs an owner?

Take the known-but-untested behavior into [Gate 3](gate3-exercise.md).
**Find evidence, make the supported correction, and leave unknown policy explicit.**
