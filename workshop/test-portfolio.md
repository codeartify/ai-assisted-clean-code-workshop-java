# Test portfolio for the payment callback

The goal is fast trustworthy feedback, not a pyramid-shaped quota.

| Speed | Boundary | What this branch protects | Why it earns its cost |
|---|---|---|---|
| Fast | Pure Java object, in process | The reactivation decision for unpaid invoices, dates, and suspension reasons | Runs without Spring or persistence; gives the agent immediate rule feedback |
| Slow | Spring HTTP + JPA, out-of-process boundary simulated in memory | JSON mapping, response status, repository queries, transaction wiring, and persisted outcomes | These risks only exist when framework and persistence pieces meet |
| Very slow | Real external invoice provider or deployed callback | Provider compatibility, network/security configuration, and production-like delivery | Useful before release or in contract checks; too expensive for every edit |

## Deliberate choices

- State assertions protect business outcomes; there are no assertions about how
  many times a repository method was called.
- The pure policy tests do not repeat JSON or database wiring.
- The integration tests retain the public HTTP surface and persistence contract.
- A true external-provider test is named but not fabricated in this repository.

Point to remember: pay for realism where the risk crosses a boundary; keep the
business rule fast enough to guide every agent iteration.
