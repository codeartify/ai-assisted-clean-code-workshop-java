# Day 1 — Trainer branch and reveal guide

Use [exercises.md](exercises.md) as the participant entry point. Every solution
becomes the next exercise's starting point. Keep participant branches intact;
create a fresh branch from the named start when rejoining the prepared path.

| Gate | Participant start | Reveal after participant work |
| --- | --- | --- |
| 1 | `ai-day1-00-start` | [Branch 01 worked review](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-01-unguided-baseline/workshop/solutions/gate1-solution.md) |
| 2 | `ai-day1-01-unguided-baseline` | [Branch 02 evidence and correction](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-02-verified-contracts/workshop/solutions/gate2-solution.md) |
| 3 | `ai-day1-02-verified-contracts` | [Branch 03 ZOMBIES solution](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-03-behaviour-specification/workshop/solutions/gate3-solution.md) |
| 4 | `ai-day1-03-behaviour-specification` | [Branch 04 test design](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-04-test-design/workshop/solutions/gate4-solution.md) |
| 5 | `ai-day1-04-test-design` | [Branch 05 diagnosis and extraction](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-05-safe-refactoring/workshop/solutions/gate5-solution.md) |
| 6 | `ai-day1-05-safe-refactoring` | [Branch 06 placement and check](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-06-vsa-blast-radius/workshop/solutions/gate6-solution.md) |
| 7 | `ai-day1-06-vsa-blast-radius` | [Branch 07 reusable workflow](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-07-workflow-skills/workshop/solutions/gate7-solution.md) |

## Run each concrete-practice loop

1. Explain the question this gate answers and the artifact it consumes.
2. Let participants direct the agent and inspect its work before showing answers.
3. Reveal the sample branch and the smallest relevant code/test diff.
4. Run the relevant checks and state exactly what their assertions establish.
5. Debrief the decision and remaining uncertainty. State the next gate's task.

Keep solution text out of the unguided agent's initial context. For Gate 3,
let the agent recommend the missing scenario before revealing the sample.
For Gates 5 and 6, let participants justify their diagnosis or placement before
showing the extracted operation or package map.

The guides describe a trainer replay sequence. They do not claim that each
small move is a separate historical commit, or that a test ran merely because
its expected result is written down. Participant implementations can differ
while satisfying the same behavior and review requirements.

## Gate 7 — General coordination and transfer (15:50–16:40)

Concept, 10 minutes: distinguish local facts from general procedure. Map the
six gates to understand; discover/verify; specify behavior and choose feedback;
decide placement; implement incrementally; review/finish. Keep Gate 3 (what to
test) distinct from Gate 4 (how). Gate 1 reviews throughout. The workshop's
unguided baseline is a teaching comparison, not a mandatory development step.
Architecture may come early; protected refactoring may precede a behavior
change. Every gate is considered; full specialist work is conditional.

Practice, 40 minutes, using the exact requests in [gate7-exercise.md](gate7-exercise.md):

1. **5 min — Baseline A:** fresh session on branch 06, without the coordinator.
   Save the transaction-reference planning response. Do not give this baseline
   session the exercise text, trainer answers, or proposed workflow.
2. **12 min — Draft/review:** create `.agents/skills/implement-feature/SKILL.md`
   from the six gates and reference the unchanged specialist skills. Discover
   local facts; avoid payment paths and rules in the coordinator.
3. **8 min — Guided A:** fresh session, identical request/model settings/code
   and repository guidance, explicitly invoke the new skill. Compare decisions.
4. **5 min — Transfer B:** fresh session, unchanged skill, plan-title filter.
   Look for PlanController/PlanService and suitable tests instead of payment
   assumptions, automatic ports, or compulsory policy extraction.
5. **10 min — Debrief/reveal:** inspect actual plans, improve the skill where
   justified, and reveal branch 07. If the skill changes, name its version and
   repeat the affected check. Save observations in `workshop/gate7-plan-comparison.md`.

Both requests are plan-only; participants do not implement either feature.
Keep repository guidance unchanged during the A comparison; any justified
AGENTS.md cleanup follows it. Sample branch 07 supplies the skill and assessment
criteria, not fabricated before/after results. A longer plan is not improvement.

Debrief: Which unsupported decision became visible? Which scenario distinguishes
the intended rule? Which test boundary is useful? Which specialist was not
needed? When did the order change, and why? Did B work without domain edits to
the skill? Which decision still needs an owner?
