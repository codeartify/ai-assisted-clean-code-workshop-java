# Gate 3 — Let the agent find the next useful behavior test

Start: `ai-day1-02-verified-contracts`

Sample solution: `ai-day1-03-behaviour-specification`

Skill: `non-brittle-tests` only.

## What this adds

Find a case where a plausible wrong implementation and the required business
rule would produce different results. Let the agent discover the gap; review
its reasoning before it writes the test.

Use James Grenning's [ZOMBIES](https://blog.wingman-sw.com/tdd-guided-by-zombies)
as a guide to the next scenario, not an instruction to generate every possible
combination:

| | Zero | One | Many |
| --- | --- | --- | --- |
| **B — Boundary behavior** | Relevant zero case | Relevant one-case boundary | Relevant many-case boundary |
| **I — Interface definitions** | Empty input / no match | One public request | Repetition or ambiguity |
| **E — Exceptional behavior** | Relevant failure | Relevant failure | Relevant failure |

**S — Simple scenarios, simple solutions** applies to the whole matrix.

## Your task

1. Invoke `non-brittle-tests` and ask for a read-only coverage assessment using
   [behaviour-matrix-template.md](behaviour-matrix-template.md).
2. Have the agent distinguish **PROVED**, **MISSING**, and **ASK**, with sources.
   Existing tests demonstrate observations. An explicit API definition can
   establish its shape, but that is contract evidence, not an executed outcome.
   Current implementation alone cannot authorize a new business rule.
3. Ask it to recommend the single smallest missing scenario that could reveal
   incorrect behavior. Check that the expected outcome follows from a known rule.
4. Approve that scenario. Have the agent add and run the behavior test, then add
   only directly related, owned input/lookup cases that you approve.
5. Review the responses and stored-state assertions. Keep unknown policy as ASK.

## Prompt 1 — discover the gap

```text
Work from ai-day1-02-verified-contracts. Use non-brittle-tests and normal
repository context. Do not use the other specialist skills or read later
solution branches. Do not edit code yet.

Inspect the payment callback, the feature request, the contract evidence, and
all existing PaymentReceivedControllerTest cases. Create a ZOMBIES assessment:
Zero, One, Many across the columns; Boundary behavior, Interface definitions,
Exceptional behavior down the rows. Apply S: simple scenarios and solutions.

For each relevant case, state the public action, starting conditions, expected
HTTP/stored-state outcome, and evidence. Mark it PROVED by an existing test,
MISSING when a known outcome has no test, or ASK when policy is undefined.
Label explicit interface declarations as contract evidence separately from
executed coverage. Do not treat an implementation branch as intended policy.

Recommend the one smallest missing scenario that could distinguish the
required rule from a plausible wrong implementation. Explain why an existing
test cannot already distinguish them. Wait for me to approve the scenario.
```

## Prompt 2 — implement the approved test

```text
Implement the scenario I approved in PaymentReceivedControllerTest.
Use the real HTTP/JPA fixture already present. Assert the public response and
reload invoice and membership state to check the stored outcomes. Use fixed
dates where time affects the result. Do not assert repository call counts,
private methods, or package placement.

Run the test. If it already passes, report that fact; do not manufacture a
production change. If it fails for a behavior defect, make only the correction
justified by the approved rule and rerun it. Do not encode unresolved invoice
relevance, membership-only selection, or callback authentication.

Save the assessment in workshop/behaviour-matrix.md. Report the selected case,
why it matters, what now has test evidence, remaining ASK items, and commands
with actual results. Stop before extracting a policy or redesigning tests.
```

## Expected output and checks

- A source-backed matrix and an explanation of the selected coverage gap.
- An approved regression test that checks HTTP results and stored state.
- A clear record of whether production code needed to change.
- The outstanding business/security questions, without invented answers.

Run from `java/`:

```sh
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q test
```

## Compare, debrief, and next step

After choosing and implementing your case, open the
[Gate 3 worked solution on branch 03](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-03-behaviour-specification/workshop/solutions/gate3-solution.md).
Which wrong implementation would your test catch? What made it the smallest
useful scenario? What must remain ASK?

[Gate 4](gate4-exercise.md) decides which rules can give feedback without
starting Spring. **The agent finds the gap; you validate the rule; the agent
writes and runs the approved test.**
