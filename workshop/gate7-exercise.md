# Gate 7 — Teach the next agent the workflow

Start: `ai-day1-06-vsa-blast-radius`

Sample solution: `ai-day1-07-workflow-skills`

Materials: the three supplied skills, repository instructions, and your findings
from Gates 1–6.

## What this adds

Turn a successful one-off change into a repeatable workflow. The goal is better
decisions on the next request, not a longer instruction file.

| Knowledge from today | Its useful home |
| --- | --- |
| Current business meaning and responsibilities | Code and names |
| Required observable outcomes | Behavior tests |
| A forbidden dependency that can be checked | Architecture test |
| Build commands, useful examples, and legacy traps | `AGENTS.md`, backed by the repository |
| When to inspect, test, refactor, ask, verify, and stop | A focused `SKILL.md` |
| Undefined invoice policy or callback authentication | A question for the responsible owner |

## Your task

1. On branch 06, save a baseline agent plan for the small follow-up request
   below. Keep it read-only. Use a fresh session and no repository-specific
   payment workflow skill; it does not exist on this start branch.
2. Review repeated findings from the day and inspect the three supplied skills.
   Decide which knowledge belongs in code/tests, repository facts, a workflow,
   or an owner question.
3. Ask the agent to draft `.agents/skills/membership-payment-change/SKILL.md`.
   Give it a clear trigger, evidence order, behavior/test steps, conditional
   specialist-skill use, verification commands, stop conditions, and report.
4. Keep stable facts in `AGENTS.md` and link to the actual code and tests. Keep
   the supplied general skills unchanged; reference them instead of copying
   their contents. Review the draft before using it.
5. In a fresh session, use the new skill with the same follow-up request. Compare
   the two plans, record concrete differences, and improve the skill where it
   fails to guide a decision. Do not implement the follow-up feature here.

## Shared follow-up request — use unchanged in both comparisons

```text
Plan support for looking up a callback invoice by a provider transaction
reference. Do not change application code. Identify the existing code and
contracts you would inspect, questions that must be answered, proposed test
boundaries, likely file changes, verification commands, and a stop condition.
Do not invent missing provider, identifier-precedence, or authentication rules.
```

Keep the underlying code and request the same. Save the baseline before adding
the skill, then start a fresh session for the comparison. For the second run,
explicitly invoke `membership-payment-change`. This is a practical comparison
of plans, not a controlled proof that a skill improves every model output.

## Sample agent prompt — create the workflow

```text
Work from ai-day1-06-vsa-blast-radius. Use our review findings, contract evidence,
ZOMBIES assessment, test portfolio, refactoring log, and architecture decision.
Inspect the existing repository instructions and the three supplied skills.

Create .agents/skills/membership-payment-change/SKILL.md with a name and
description that identify when it applies. Define an ordered workflow:
inspect installed and owned contracts; identify intended behavior and ASK
items; select the next useful test; choose its boundary; make the approved
change; verify; stop; report.

Reference non-brittle-tests for tests, clean-code-refactoring in Full Format
for refactoring, and modern-application-architecture when placement or
dependencies need reconsideration. Do not require every specialist workflow
for a tiny change. Preserve the supplied skills.

Keep facts and commands in AGENTS.md concise and backed by actual files.
Do not duplicate whole tests or business rules into prose. Require owner input
for undefined invoice relevance, ambiguous lookup, and authentication. State
that the current boundary test scans imports and does not prove full isolation.
Include focused/broad commands, stop conditions, and the completion report.

Change only the workflow/documentation for this exercise. Explain the trigger,
where each piece of knowledge belongs, and how I can evaluate the resulting
skill on a new planning request.
```

## Expected output and checks

- The focused workflow skill and any justified, concise `AGENTS.md` update.
- `workshop/gate7-plan-comparison.md`: both plans and a short comparison of
  evidence inspected, owner questions, test choice, change scope, and stopping.
- Examples of decisions improved by the workflow, or an honest report that a
  comparison showed no meaningful improvement yet.

Check that every referenced path and command exists on the solution branch.
Inspect the diff for accidental application changes. If the agent executes
verification, report actual results; do not claim it ran just because the
command appears in the skill. Follow the repository's verification requirements.

Relevant commands from `java/`:

```sh
mvn -q -Dtest=MembershipReactivationPolicyTest,PaymentReceivedControllerTest test
mvn -q -Dtest=VerticalSliceBoundaryTest test
mvn -q test
```

## Compare and conclude

Open the [Gate 7 worked solution on branch 07](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-07-workflow-skills/workshop/solutions/gate7-solution.md).
Did your skill change a decision or only add prose? Which instruction is better
expressed as a test? What must still make an agent ask a human?

Stop after the reviewed workflow and plan comparison. Choose one code, test,
or workflow improvement to carry into your own repository.
**Your codebase guides the next agent through facts, examples, tests, and a
repeatable procedure.**
