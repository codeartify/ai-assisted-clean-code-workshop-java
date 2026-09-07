# AI-Assisted Clean Code Workshop - Day 1 repository

This workshop starts from the Java `MembershipController` on the upstream `main` branch and follows one cumulative feature through evidence gathering, behavior specification, tests, safe refactoring, and architecture. Gate 7 extracts a general coordinating skill and checks its transfer to another feature.

**Participant entry point: [Seven exercise guides](exercises.md).**
Each includes the task, sample prompts, expected output, checks, debrief, and
a link to the worked solution on the matching sample branch.

## Primary job

Guide an AI coding agent to leave a future-proof, maintainable change. Clean Code, testing, domain language, and architecture are decision tools used only when they improve that job.

## Feature request

An external invoice payment callback may be delivered more than once. Recording a payment must be idempotent. A membership suspended for non-payment may become active only when all relevant billing references for that membership are paid and the membership period has not ended. A cancelled membership must never reactivate. An unknown invoice must produce the documented failure response.

The callback authentication mechanism is intentionally unresolved: the repository does not prove it. Agents must surface the missing contract instead of inventing a token or signature scheme.

## Branch progression

| Branch | Purpose |
| --- | --- |
| `ai-day1-00-start` | Upstream `main` plus workshop brief, templates, commands, and three supplied skills. |
| `ai-day1-01-unguided-baseline` | Prepared plausible agent implementation; useful, but with hidden design and contract decisions to review. |
| `ai-day1-02-verified-contracts` | Evidence ledger and the smallest contract corrections; missing security knowledge remains visible. |
| `ai-day1-03-behaviour-specification` | ZOMBIES scenario selection and public-surface regression tests. |
| `ai-day1-04-test-design` | Fast policy feedback plus slower HTTP/JPA proof at deliberately chosen scopes. |
| `ai-day1-05-safe-refactoring` | Controller orchestration extracted in small behavior-preserving steps with a stop condition. |
| `ai-day1-06-vsa-blast-radius` | Payment operation/policy grouped together, shared dependencies named, and a limited import check added. |
| `ai-day1-07-workflow-skills` | General `implement-feature` skill, comparison/transfer checks, concise guidance, and trainer material. |

Every solution branch is the starting point for the next exercise. Participant work can be local and nondeterministic; the trainer switches to the next branch only for the sample-solution walkthrough.

## Working agreement

1. Start each exercise from the branch named in its brief.
2. Let the agent show its plan and evidence, not only its diff.
3. Run the focused test command before the broad command.
4. Compare decisions and observable behavior during the debrief.
5. Keep unresolved business or security knowledge explicit.
6. Stop once the exercise teaching point is resolved; do not modernize the whole controller.
