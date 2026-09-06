# Gate 4 — Choose the test boundary for the risk

Use with [gate4-exercise.md](gate4-exercise.md). These are practical workshop
categories, not fixed time limits or quotas for a test pyramid.

- **Fast:** deterministic Java objects in memory; no Spring or database startup.
- **Slow:** framework/persistence integration. The full MockMvc + Spring +
  JPA/H2 fixture is slower than a policy test even though it runs in one process.
  MockMvc does not require a real HTTP server.
- **Very slow:** a real provider, separately running application, or deployed
  workflow. Use it only for a defined risk that cheaper checks cannot establish.

| Behavior or failure risk | Observation to assert | Smallest sufficient boundary | Cost category / measured time | What failure would suggest | Known limit |
| --- | --- | --- | --- | --- | --- |
| Reactivation decision | | | | | |
| Request/response and error mapping | | | | | |
| Invoice lookup and persisted outcomes | | | | | |
| Failure after an earlier state update | | | | | |
| External provider or authentication compatibility | | | | | |

Prefer real objects. Introduce a fake, stub, or mock only for a specific boundary
that needs it. Avoid repository call counts and private implementation details
unless the interaction itself is an owned contract.

Record actual timings only when measured. Mark untested risks and missing
contracts clearly. A normal-path Spring/JPA test does not automatically prove
rollback, production-database compatibility, or provider authentication.
