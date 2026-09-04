# Trainer branch guide

All branches are cumulative. Participants start from the branch in the second
column; the sample resolution is in the third. Preserve participant branches and
reset only through normal Git operations agreed with the group.

| Exercise | Participant start | Sample resolution | Resolution to reveal |
|---|---|---|---|
| 1. Unguided baseline | `ai-day1-00-start` | `ai-day1-01-unguided-baseline` | A compact all-invoices check looks credible but leaves transaction, security, selection, and meaning unresolved. |
| 2. Contract evidence | `ai-day1-01-unguided-baseline` | `ai-day1-02-verified-contracts` | Evidence supports an atomic transaction; missing auth and ambiguous selection stay visible. |
| 3. ZOMBIES behavior selection | `ai-day1-02-verified-contracts` | `ai-day1-03-behaviour-specification` | One invoice lets both “any paid” and “all paid” pass. The smallest revealing Many case pays one of two invoices and proves that the membership must stay suspended. Missing policy/security cells remain ASK. |
| 4. Test portfolio | `ai-day1-03-behaviour-specification` | `ai-day1-04-test-design` | Fast policy tests guide rule edits; HTTP/JPA tests retain contract coverage. |
| 5. Safe refactoring | `ai-day1-04-test-design` | `ai-day1-05-safe-refactoring` | One application operation removes mixed responsibility without creating a framework. |
| 6. Blast radius | `ai-day1-05-safe-refactoring` | `ai-day1-06-vsa-blast-radius` | The use case moves into an explicit slice and a test enforces no slice-to-slice dependency. |
| 7. Workflow skill | `ai-day1-06-vsa-blast-radius` | `ai-day1-07-workflow-skills` | A focused skill composes evidence, behavior, testing, refactoring, architecture, verification, and stopping. |

## Reveal discipline

For each concrete-practice loop:

1. Let participants produce and inspect their own result.
2. Demonstrate only the smallest diff that resolves the teaching point.
3. Debrief the decision and evidence, not the exact prompt wording.
4. State the one takeaway before moving to the next concept.

The sample is a worked decision, not the only acceptable code shape.
