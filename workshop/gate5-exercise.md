# Gate 5 — Ask for a ranked diagnosis, then choose a refactoring

Start: `ai-day1-04-test-design`

Sample solution: `ai-day1-05-safe-refactoring`

Skill: `clean-code-refactoring`, in **Full Format**.

## What this adds

Use the agent to find the improvements with the most value for the least risk.
You choose which recommendation to implement. The existing fast and HTTP/JPA
tests protect behavior while the agent changes the structure in small steps.

The target is **`MembershipController.paymentReceived`**. There is no production
class called `PaymentReceivedController`; `PaymentReceivedControllerTest` is
the endpoint test class. The reactivation policy already exists at this start.

## Smells to recognize

A smell suggests a question, not an automatic refactoring. Report only smells
actually found in the scoped code, with evidence.

| Smell | What to look for | Possible small response |
| --- | --- | --- |
| Primitive obsession | Raw strings, flags, or dates carry business meaning | Name the meaning; introduce a value concept when its rules are known |
| Deeply nested control flow | Several condition levels hide the outcome | Guard clauses or a named decision, preserving every path |
| Complicated boolean expression | A reader must translate conditions into a business sentence | Extract a named predicate or policy |
| Feature envy | Code repeatedly inspects another concept's data to make its decision | Move the decision toward the concept that owns it |
| Duplication | The same business rule must be changed in several places | Consolidate that shared knowledge; similar syntax alone is insufficient |
| Bad names | Names describe mechanics but hide intent | Rename around the business action or outcome |
| Speculative generality | Unused hooks, speculative ports, or imagined variations | Remove/defer the abstraction until a real need exists |
| Deep inheritance hierarchy | Understanding behavior requires chasing overrides through levels | Simplify the hierarchy or use composition where it helps |

Mixed responsibilities can explain why several of these signals appear
together. Do not force all eight into the assessment. **Parallel Change** is an
expand/migrate/contract refactoring strategy, not a smell or a synonym for
things that change together. Shotgun surgery is not part of this exercise's
smell checklist.

## Your task

1. Ask for a diagnosis before edits. Require code locations and an explanation
   of how each finding makes a future change harder.
2. Rank findings by impact, effort, and risk of changing behavior. Separate
   low-cost improvements from larger responsibility changes.
3. Select the highest-value safe improvement. Agree on baby steps and a clear
   stopping point; reject suggestions that need facts the repository lacks.
4. Authorize and review one meaningful step at a time. Run the policy tests
   first and HTTP/JPA tests after orchestration or wiring changes.
5. Record what moved, evidence from tests, rejected/deferred work, and why you
   stopped in `workshop/refactoring-log.md`.

## Prompt 1 — diagnose and rank

```text
Work from ai-day1-04-test-design. Invoke clean-code-refactoring in Full Format
and evaluate MembershipController.paymentReceived. Do not change code yet.

State the behavior to preserve. Find concrete smells and responsibility
problems, with file/method evidence. For each, explain the cost to a future
change, estimate improvement impact, implementation effort, and behavior risk,
and rank it. Separate quick improvements from wider model changes. Do not
claim a smell exists merely because it appears in the workshop checklist.

Recommend the highest-value safe refactoring, the smallest sequence of steps,
tests after meaningful moves, and a stop condition. List speculative
abstractions to reject and larger changes to defer. The policy already exists;
do not propose extracting it as though Gate 4 had not happened.
Wait for me to select or approve the work.
```

## Prompt 2 — implement the selected move

```text
Implement the refactoring I selected, one meaningful step at a time. Preserve
the endpoint, request/response and error behavior, identifier lookup, retry
behavior, stored outcomes, reactivation rule, and complete transaction.

Run MembershipReactivationPolicyTest first. Run PaymentReceivedControllerTest
after orchestration or wiring changes and the full Java suite at completion.
Report each step and its actual checks in workshop/refactoring-log.md.

Keep package movement for Gate 6. Do not add an authentication port, payment
event, generic command hierarchy, or billing framework without a requirement.
Stop at the agreed responsibility change and report what was deferred.
```

## Expected output and checks

- A ranked assessment with code evidence, impact, effort, and behavior risk.
- Your selected improvement and the reason for choosing it.
- A small sequence of reviewed diffs and focused feedback.
- A refactoring log with remaining concerns and the stopping point.

Run from `java/`:

```sh
mvn -q -Dtest=MembershipReactivationPolicyTest test
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q test
```

## Compare, debrief, and next step

After making your choice, reveal the [Gate 5 worked solution on branch 05](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-05-safe-refactoring/workshop/solutions/gate5-solution.md).
Which finding ranked first, and why? Which responsibility moved? What did the
tests demonstrate? Which attractive suggestion was speculative?

[Gate 6](gate6-exercise.md) decides where the resulting responsibilities belong.
**Let the agent diagnose and propose; choose the useful change and verify each step.**
