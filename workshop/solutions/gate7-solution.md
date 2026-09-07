# Gate 7 — Worked general feature workflow

Reveal after [the participant exercise](../gate7-exercise.md).
Compare `ai-day1-06-vsa-blast-radius` with `ai-day1-07-workflow-skills`.

## The sample artifact

Read [implement-feature/SKILL.md](../../.agents/skills/implement-feature/SKILL.md).
It replaces the former payment-specific coordinator. Its trigger covers new
features, behavior changes, bug fixes, and explicit plan-only work. It discovers
the affected use case; the payment slice is an example rather than its scope.

| Phase | Gate and decision |
| --- | --- |
| Understand | Gate 1: expose intent, acceptance criteria, preserved behavior, assumptions, and scope |
| Discover and verify | Gate 2: use appropriate evidence; CLOSE, FIX, TEST, or ESCALATE |
| Specify and choose feedback | Gate 3: revealing ZOMBIES scenario; Gate 4: suitable test boundary |
| Decide placement | Gate 6: current home, shared dependencies, and justified boundaries |
| Implement incrementally | Gates 3–4: useful checks; Gate 5: ranked, protected refactoring when needed |
| Review and finish | Gate 1 throughout; revisit other gates, verify, and recommend ACCEPT/HOLD/REJECT |

Architecture can come earlier; protected refactoring can precede a behavior
change. A passing new test may require no production change. Undefined outcomes
need an owner. Plan-only work stops at a reviewed plan. These are decision rules,
not instructions to perform every specialist workflow on every request.

`AGENTS.md` supplies repository facts and example commands for different areas.
The three specialist skills stay unchanged. Branch 07 changes documentation and
workflow guidance; the Java implementation and Gate 6 import check remain the
same. That check still does not prove complete isolation through shared types.

## Assess the comparison and transfer

The following are evaluation criteria, not recorded results from agent runs.

| Check | Request A: transaction-reference lookup | Request B: plan-title filter |
| --- | --- | --- |
| Discover scope | Callback request/lookup, persisted references, provider contract, tests | PlanController, PlanService, plan storage/response, PlanControllerTest |
| Identify unknowns | Uniqueness, invoice mapping, identifier precedence, authentication | Unproven matching details or ordering; preserve the explicitly supplied filter rules |
| Select feedback | HTTP/lookup/persistence checks; keep unaffected policy tests fast | Omitted/blank input, mixed-case substring, no match; query mapping and returned plans |
| Decide design work | Justify any new boundary from the actual provider/lookup requirement | Existing placement may suffice; no automatic policy extraction, port, or payment-slice move |
| Stop honestly | Review the plan and owner questions; no implementation or claimed execution | Review the plan and its scope; no implementation or invented test results |

Participants save A's before/after plans and B's transfer plan in
`workshop/gate7-plan-comparison.md`, recording the branch/commit and skill version.
Hold application code, repository guidance, request A, and model settings constant
between A's runs. Use fresh sessions. Do not claim a controlled experiment or
universal improvement from a small comparison.

## Review the sample too

Can it discover a different feature without being edited? Are Gate 3 and Gate 4
distinct? Does it avoid unnecessary specialist work while considering every gate?
Are facts/commands grounded in the repository? Does it respect plan-only scope
and existing authorization? Are unknown rules visible and actual results honest?

Keep general procedure in the coordinator, detailed technique in specialists,
and business meaning, examples, commands, and enforced rules in the repository.
