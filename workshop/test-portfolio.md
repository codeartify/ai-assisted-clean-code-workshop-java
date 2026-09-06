# Gate 4 sample — Two feedback loops for the payment callback

Read with [gate4-exercise.md](gate4-exercise.md) and the
[worked solution](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-04-test-design/workshop/solutions/gate4-solution.md).

| Cost category | Boundary in this repository | What the sample exercises | Limit |
| --- | --- | --- | --- |
| Fast | Java policy and real data objects in memory | Four decisions: allow reactivation, unpaid invoice remains, period ended, other suspension reason | No HTTP, query, persistence, or transaction proof; the policy still accepts a JPA entity |
| Slow | MockMvc + Spring + JPA/H2 in one process | Nine callback scenarios covering lookup, response fields/status, and normal-path stored outcomes | Not every stored row/error-body outcome is asserted; no deliberate rollback failure or production DB check |
| Very slow | A real provider, separately running application, or deployed flow | No such test is added in this sample | The contract and risk must be known before a test can establish compatibility/authentication |

## What changes from 03 to 04

The reactivation decision and four fast tests are extracted. All nine HTTP/JPA
tests remain. No repository call-count assertions existed to remove. This is
the first useful split, not a claim that the slower suite has been fully pruned.

No mocks are needed for the policy. Test dates are explicit. The H2 fixture and
MockMvc do not require a real external process or HTTP server. Classify this
fixture as slow relative to the policy tests because it starts and integrates
the framework and persistence components.

Run the policy suite first, then the callback suite, then the full Java suite.
Use failures to locate the problem, not to assume its cause without inspecting
the assertion or exception. Report actual timings/results when measured.
