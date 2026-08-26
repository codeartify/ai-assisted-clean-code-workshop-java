# Test scope map

Use Jimmy Bogard's pragmatic labels for this workshop:

- **Fast:** runs in-process and gives the agent tight feedback on policy or use-case behavior.
- **Slow:** crosses an out-of-process boundary such as a real database connection or HTTP adapter.
- **Very slow:** starts or depends on external processes; keep only when it proves a cross-process risk no cheaper test can establish.

| Risk | Suggested proof | Cost class | What a failure diagnoses |
| --- | --- | --- | --- |
| Reactivation policy | Pure policy/use-case test | Fast | Business rule regression |
| Request/response and error contract | MockMvc application test | Fast/in-process in this fixture | HTTP mapping or orchestration regression |
| JPA query or transaction semantics | Repository/application test with real H2 | Slow relative to policy test | Persistence behavior |
| External invoice provider compatibility | Targeted adapter/contract test | Slow or very slow | Provider contract mismatch |
