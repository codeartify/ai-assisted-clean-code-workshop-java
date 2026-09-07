# Gate 2 — Answer the review questions with evidence

Start: `ai-day1-01-unguided-baseline`

Sample solution: `ai-day1-02-verified-contracts`

Skills: repository instructions only; no specialist skills yet.

## What this adds

Gate 1 asked what might be wrong. Here you check the code and tests, fix what you
can justify, and name the questions that need a business or security decision.

For example, `allMatch(isPaid)` compiles. That does not tell you which invoices
the business wants to count. The query shows which invoices the code counts today;
the tests show which payment scenarios it actually checks.

## Your task

1. Inspect branch 01 and bring your Gate 1 questions. If the trainer is using a
   participant's implementation, record that branch/commit and review its actual code.
2. Complete the six rows in [contract-evidence-template.md](contract-evidence-template.md).
   Put the answer and its source in **What did you find?**
3. Choose **CLOSE**, **FIX**, **TEST**, or **ESCALATE** for each row.
4. Make the smallest justified correction. Record missing tests for Gate 3 and
   questions that need an owner. Keep refactoring for later.
5. Run compilation and the focused callback tests. Report actual results and limits.

Use `java/pom.xml` to check versions and dependencies. Trace the callback through
`MembershipController.paymentReceived`, the request/response records, repositories,
entities, and `GlobalControllerAdvice`. Read `PaymentReceivedControllerTest` and
the local invoice-provider code; look for an actual callback authentication contract.

## Sample agent prompt

```text
Review the payment callback on ai-day1-01-unguided-baseline. Use repository
instructions, but do not invoke the specialist workshop skills yet.

Fill workshop/contract-evidence.md using workshop/contract-evidence-template.md.
Keep its three columns: Question; What did you find?; CLOSE / FIX / TEST / ESCALATE.
Answer all six questions:
- Which invoices count?
- Can the list be empty?
- Which invoice does membershipId mean?
- Do both updates commit together?
- Who may send the callback?
- Is the many-invoice rule tested?

For each answer, name the exact file, method, test, or contract that supports it.
Check pom.xml, the callback, request/response records, repository queries, tests,
and provider/security code. Trace whether an empty invoice list can occur here.
Distinguish what the code does from what the requirements say it should do.

CLOSE a question the evidence answers. FIX a supported technical defect.
TEST a known outcome that lacks coverage or still needs an executable check.
ESCALATE a rule that needs a business/security owner or provider contract.

Inspect existing tests for partial payment and payment of the final invoice.
If they already cover these cases and pass, mark that coverage CLOSE. Do not
assume a missing test, missing transaction, or a required HOLD decision.

Make only the smallest justified technical corrections. Preserve the HTTP API
and established behavior. Do not invent invoice relevance, membership-only
selection, or authentication. Leave new behavior tests and refactoring to later gates.

Record the branch/commit reviewed. Run compilation and the focused callback tests;
follow repository verification instructions after a code change. Report actual
commands/results, corrections, missing checks, and questions needing an owner.
If a check cannot run, say so; do not report it as passed.
```

## Expected output

- The completed six-question table, with concrete findings and an action per row.
- A small correction diff, if a supported defect was found; otherwise explain why no fix is needed.
- Commands and actual results, including any check that could not run.
- Test gaps for Gate 3 and separate questions for a business/security owner.

The prepared solution is one example. A participant's agent may already have added
the transaction or many-invoice tests. Credit that evidence and investigate what is
still unresolved. Do not remove a good test to recreate the sample's gap.

## Checks and stop condition

Run from `java/` with the Java version required by `pom.xml`:

```sh
mvn -q -DskipTests compile
mvn -q -Dtest=PaymentReceivedControllerTest test
```

After a production change, run `mvn -q test` before declaring completion, as
required by the repository instructions. Report an environment failure separately
from a compilation or assertion failure.

Stop after the supported corrections and evidence sheet. An added transaction
annotation is a code change; testing rollback is a separate check. Passing tests
do not answer an undefined invoice-selection or authentication question.

## Compare, debrief, and next step

Open the [Gate 2 worked solution on branch 02](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-02-verified-contracts/workshop/solutions/gate2-solution.md).
Which question could you close? What did you fix? What still needs a test or an owner?

Take the remaining test gaps into [Gate 3](gate3-exercise.md). If the many-invoice
case is already covered, use ZOMBIES to find another missing case whose expected
outcome is known.

**Each question ends with an answer, a correction, a test to run, or a named decision to ask for.**
