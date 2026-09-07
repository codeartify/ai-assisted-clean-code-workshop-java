# Gate 7 — Build a general feature-coordination skill

Start: `ai-day1-06-vsa-blast-radius`

Sample solution: `ai-day1-07-workflow-skills`

Materials: findings from Gates 1–6, repository guidance, and the three supplied
specialist skills. Preserve those specialist skills.

## What this adds

Extract a reusable `implement-feature` workflow from the payment example. It
must discover the affected feature and coordinate the six engineering gates
without hardcoding payment classes, invoice rules, or payment-only commands.
Each application of the workflow still has a bounded change scope.

| Course gate | Place in the general workflow |
| --- | --- |
| 1 — Review agent decisions | Review assumptions, the plan, meaningful increments, and the final diff |
| 2 — Verify with evidence | Understand and discover contracts, installed APIs, current behavior, and unknowns |
| 3 — Find the next behavior test | Use ZOMBIES to select a revealing scenario with an owned expected outcome |
| 4 — Choose feedback boundaries | Use `non-brittle-tests` to choose useful realism, speed, and assertions |
| 5 — Refactor safely | Diagnose/rank actual design pressure and make a small protected change when justified |
| 6 — Decide placement | Discover the use case, shared dependencies, and lightest useful boundary |

Suggested phases: understand the request; discover and verify scope; specify
behavior and choose tests; decide placement; implement incrementally; review
and finish. Gate 3 decides **what** to test; Gate 4 decides **how** to test it.
Bring architecture or refactoring forward when evidence justifies it. Review
throughout. The unguided baseline from Gate 1 is not a required production step.

## Your task

1. On a participant branch from 06, save a read-only baseline plan for request A
   below in a fresh agent session. Keep the new coordinating skill absent. Do not
   give that session this exercise, later solutions, or the proposed workflow.
   Normal repository guidance and the three supplied skills remain available.
2. Inspect the day's findings. Keep business meaning in code/names, observable
   outcomes in behavior tests, enforceable dependencies in architecture tests,
   and local paths/commands/examples in `AGENTS.md`. Leave unknown policy with
   its responsible owner. Put reusable sequencing and decisions in the skill.
3. Draft `.agents/skills/implement-feature/SKILL.md` with a clear trigger, the
   six-phase workflow, explicit gate mapping, conditional specialist use,
   adaptable ordering, verification, stop conditions, and completion report.
   Read the three supplied skills and reference them instead of copying them.
4. Review the draft. In a fresh session, explicitly invoke `implement-feature`
   with the identical request A. Keep application code, request text, repository
   guidance, and model settings the same for this comparison. Record concrete
   decision differences in `workshop/gate7-plan-comparison.md`.
5. Use the unchanged skill in another fresh session with request B. Check that
   it discovers the plan feature and selects its own evidence/tests without
   payment assumptions or mandatory architecture/refactoring work.
6. Record the transfer result and improve the skill where it fails to guide a
   decision. If it changes, state which runs used which version and repeat the
   affected comparison. Make any justified `AGENTS.md` cleanup after the comparison.

## Request A — use unchanged before and after adding the skill

```text
Plan support for looking up a callback invoice by a provider transaction
reference. Do not change application code. Identify the existing code and
contracts you would inspect, questions that must be answered, proposed test
boundaries, likely file changes, verification commands, and a stop condition.
Do not invent missing provider, identifier-precedence, or authentication rules.
```

## Request B — transfer to another feature

```text
Plan an optional titleContains query parameter for GET /api/plans. An omitted
or blank parameter keeps the existing full list. A supplied value selects plans
whose title contains that value, ignoring case; no matches returns an empty
list. Preserve the response shape. Do not change application code.
Inspect the plan feature, identify the next revealing scenario and its test
boundary, likely file changes, verification commands, unresolved decisions,
and a stop condition. Explain whether architecture or refactoring work is
needed. Do not impose payment rules or invent a new ordering contract.
```

This is a practical comparison of plans and a transfer check. It does not prove
that a skill improves every model output. More prose is not itself improvement.

## Sample prompt — create the coordinator

```text
Work from ai-day1-06-vsa-blast-radius. Use our findings from Gates 1–6 and
inspect the repository instructions and three supplied specialist skills.
Create .agents/skills/implement-feature/SKILL.md for planning and implementing
features across this repository, including changes to existing behavior.

Define: understand intent and acceptance criteria; discover and verify affected
scope; select a revealing behavior scenario with ZOMBIES and separately choose
its test boundary; decide placement; implement in small verified increments;
review and finish. Map these decisions explicitly to course Gates 1–6.

Use non-brittle-tests for behavior/test design, clean-code-refactoring for an
evidenced design problem, and modern-application-architecture when placement
or dependencies need a decision. Keep their contents unchanged. Consider every
gate without requiring a full specialist assessment for every tiny change.
Allow architecture early and protected refactoring before a behavior change.
Review throughout; never require an unguided implementation as the first step.

Discover paths, contracts, rules, and commands from the affected code, tests,
and AGENTS.md. Do not hardcode payment-specific examples as universal policy.
Respect plan-only requests and existing authorization. Ask about missing rules
that block the next step; report unrelated inherited gaps without expanding
scope. Define verification, ACCEPT/HOLD/REJECT criteria, stop conditions, and
a concise report of actual results and limits.

Change only the coordinating skill for the comparison. Explain how to evaluate
it on request A and transfer it to request B. Do not implement either feature.
```

## Expected output and checks

- The reviewed general skill, with resolvable links to unchanged specialists.
- A comparison note containing A's baseline and guided plans, B's guided plan,
  source branch/commit, skill version, and actual observations.
- Compare intent/assumptions, evidence, scenario choice, feedback boundary,
  placement, refactoring need, scope, owner questions, verification, and stopping.
- An honest report if no useful improvement or transfer was demonstrated.

Check that referenced paths and commands exist on the inspected branch. The
coordinator should discover focused commands for the affected feature; it must
not prescribe the payment test suite for request B. Keep proposed commands and
executed results separate. Inspect the diff for application changes and follow
the repository's verification requirements for any authorized edits.

## Compare and conclude

Open the [Gate 7 worked solution on branch 07](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-07-workflow-skills/workshop/solutions/gate7-solution.md)
after reviewing your own skill and plans. Which decision improved? Which gate
moved earlier, and why? Which specialist was unnecessary? What still needs an
owner? Did request B work without editing the skill's domain assumptions?

Stop after the reviewed skill and plan comparison. No application feature is
implemented in Gate 7. Take one reusable workflow improvement into your own
repository, with facts and behavior protected in their appropriate homes.
