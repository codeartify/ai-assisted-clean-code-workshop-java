# Gate 3 — Payment callback ZOMBIES template

Use with [gate3-exercise.md](gate3-exercise.md).
[ZOMBIES](https://blog.wingman-sw.com/tdd-guided-by-zombies) guides selection of
the next useful test. Move from **Zero** to **One** to **Many**; consider
**Boundary behavior**, **Interface definitions**, and **Exceptional behavior**.
Apply **S — Simple scenarios, simple solutions** throughout.

| | Zero | One | Many |
| --- | --- | --- | --- |
| **B — Boundary behavior** | | | |
| **I — Interface definitions** | | | |
| **E — Exceptional behavior** | | | |

Only fill relevant cells. This is not an exhaustive Cartesian-product test plan.

For each scenario, record starting conditions, public action, observable
outcome, status, and source:

- **PROVED:** an existing test demonstrates the stated outcome. Name the test
  and assertions; distinguish a test you ran from one you only inspected.
- **MISSING:** a known expected outcome has no protecting test.
- **ASK:** intended product, API, or security policy cannot be established.

Explicit interface declarations can establish shape; label those as contract
evidence rather than executed behavior. The implementation alone does not
authorize the intended rule.

## Choose and approve the next test

1. Which plausible incorrect behavior would still pass the existing tests?
2. What is the smallest scenario that distinguishes it from the known rule?
3. What evidence establishes the expected outcome?
4. Which HTTP or stored-state assertions would demonstrate it?
5. Has the participant approved that scenario?

After approval, write and run the test. Change production code only if the
approved scenario reveals a defect. If it already passes, report the coverage
gained and leave production code unchanged.

Keep undefined invoice relevance, membership-only selection, and callback
authentication as ASK.
