---
name: implement-feature
description: Coordinate planning, implementation, testing, and review of a feature or behavior change using verified contracts, useful test boundaries, safe refactoring, and deliberate placement. Use for new features, changes to existing features, and bug fixes, including explicit plan-only requests. Discover the affected use cases instead of assuming a particular domain or vertical slice.
---

# Implement a feature through six engineering gates

Use the Day 1 gates as recurring decision checks. The suggested phases below
can change order when the change requires it. Consider every gate; invoke a
specialist only when its decision is needed. Keep the working notes proportional
to the task instead of creating a document for every gate.

| Course gate | Reusable question |
| --- | --- |
| 1 — Review | What did the agent decide, what is unsupported, and can this change be accepted? |
| 2 — Verify | Which source can establish this API, contract, or required outcome? |
| 3 — ZOMBIES | Which smallest missing scenario distinguishes the intended rule from a plausible wrong implementation? |
| 4 — Feedback | Which test boundary detects that failure with useful realism and speed? |
| 5 — Refactor | Which evidenced design problem is worth changing safely? |
| 6 — Architecture | Where does this behavior belong, and what else could the change affect? |

Read repository instructions and discover local paths, commands, examples, and
constraints. Business rules belong in requirements, code, and behavior tests;
dependency rules belong in executable checks where practical. This skill carries
the procedure, not a fixed file list or a copy of one feature's rules.

## 1. Understand the request

State the observable change, acceptance criteria, behavior to preserve, and
scope exclusions. Identify consequential assumptions. Inspect available evidence
before asking the user questions that the repository can answer.

Honor the requested mode. For plan-only work, inspect and propose; do not edit
application code or run mutation-oriented steps. Describe proposed verification
as proposed, and never report an unexecuted check as passed.

## 2. Discover the affected scope and verify contracts

Trace the relevant entry points, use cases, rules, dependencies, shared state,
and existing tests. Check installed versions and actual interfaces before using
framework APIs. Use authoritative documentation for the installed version when
local evidence cannot answer a technical question.

Keep current behavior, required behavior, and unverified assumptions distinct.
A compiler establishes API availability; tests exercise selected outcomes;
neither defines an unknown business or security policy.

Classify material findings as CLOSE (answered), FIX (supported defect), TEST
(known outcome needs executable evidence), or ESCALATE (owner decision needed).
Identify likely changed files and behavior that could be affected through shared
dependencies. Revisit the scope when new evidence changes it.

## 3. Specify behavior, then choose feedback

Use [non-brittle-tests](../non-brittle-tests/SKILL.md) when assessing behavior
coverage or designing tests. Make two separate decisions:

1. **Gate 3 — What to test.** Apply ZOMBIES: grow from Zero to One to Many;
   inspect Boundaries, Interfaces, and Exceptions; keep scenarios and solutions
   Simple. Identify existing coverage, known outcomes missing coverage, and ASK
   items with undefined outcomes. Choose the smallest revealing missing case.
   Do not mechanically fill a matrix or infer intended policy from implementation.
2. **Gate 4 — How to test it.** Choose fast deterministic tests for decisions;
   retain framework, persistence, transaction, or external checks when those
   boundaries are part of the risk. Prefer real objects and observable outcomes.
   Use a fake, stub, or mock for a specific need; assert an interaction only when
   that interaction is the required behavior. Harmless internal changes should
   not break the test.

Name the scenario's starting state, public action, expected outcome, source for
that outcome, test boundary, and actual local command. Distinguish reading a test
from executing it and report limits of the selected environment.

## 4. Decide placement and contain the blast radius

Establish where the feature belongs. Use
[modern-application-architecture](../modern-application-architecture/SKILL.md)
when placement, dependencies, shared rules, or change axes need reconsideration.
Choose the lightest useful structure. Keeping existing placement is valid.

Keep genuinely shared business knowledge shared; do not equate similar syntax
with the same rule. Grouping by use case does not remove coupling through shared
types. Justify each new port, layer, or abstraction with a current problem.
When adding an enforced dependency rule, choose a suitable check, demonstrate
that it detects a valid-code violation, remove the violation, and state its limits.

## 5. Implement in small verified increments

Within the user's authorized scope, implement one meaningful scenario at a time.
Run its test before the behavior change when useful. If it already passes, report
the coverage improvement; do not manufacture a production change. If it fails,
confirm the failure concerns the intended behavior before making the correction.

Use [clean-code-refactoring](../clean-code-refactoring/SKILL.md) when an evidenced
smell or responsibility problem warrants structural work. Rank findings by
impact, effort, and behavior risk. Use its Full Format for a substantive ranked
assessment and refactoring sequence; do not impose that ceremony on every edit.
Establish adequate behavior protection before moving code. Keep intentional
behavior changes distinguishable from behavior-preserving refactoring.

Run focused checks after meaningful steps. Inspect the diff for misleading names,
mixed responsibilities, duplicated business knowledge, and speculative flexibility.
Stop structural work at the justified improvement; do not expand into general cleanup.

## 6. Review, verify, and finish

Apply Gate 1 to the proposed approach, meaningful increments, and final diff.
The course's unguided baseline is a teaching comparison, not a development step.
Review new and inherited assumptions, observable behavior, errors, state changes,
affected contracts, test quality, and dependencies. Investigate security or
transaction effects when relevant to the request.

Run the repository's required completion checks after implementation, including
broader checks justified by changed shared code. Reopen the relevant gate when
review finds a gap. Recommend ACCEPT only when the requested behavior and scope
are supported by adequate evidence and no unresolved decision blocks acceptance;
HOLD for missing decisions or required verification; REJECT a proposal that
violates known requirements. A plan-only result is a reviewed plan, not a verified
implementation or release approval.

## Adapt the order and stop deliberately

- Bring architecture forward when the request crosses boundaries or changes shared rules.
- Refactor before adding behavior when existing structure blocks a safe change;
  protect current behavior first and keep the refactoring separately reviewable.
- Return to intent or evidence when a test or design finding invalidates an assumption.
- Proceed within existing authorization. Ask when a missing business/security rule
  or consequential scope decision blocks the next step; pause that dependent work.
  Report unrelated inherited gaps without silently expanding the assignment.
- Stop at satisfied acceptance criteria and required verification. Report an
  environment blocker separately from a behavior failure. Do not invent rules,
  force every specialist workflow, or claim that passing checks prove all behavior.

## Completion report

Report behavior changed or planned, evidence inspected, tests and actual results,
placement/refactoring decisions (including justified non-use), remaining questions
or verification gaps, acceptance recommendation, and the condition that ended work.
