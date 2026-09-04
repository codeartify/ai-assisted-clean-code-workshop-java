# Payment callback ZOMBIES matrix — participant template

[ZOMBIES](https://blog.wingman-sw.com/tdd-guided-by-zombies) is a guide for
choosing the next useful TDD scenario. Move from **Zero** to **One** to **Many**.
At each step, scan **Boundary behavior**, **Interface definition**, and
**Exceptional behavior**. The **S** applies throughout: keep scenarios and
solutions simple.

Fill only cells supported by the feature rule or repository evidence. Write
**ASK** when a product or security owner must decide.

|  | Zero | One | Many / more complex |
| --- | --- | --- | --- |
| **B — Boundary behavior** | What happens when no invoice matches? | What happens at the last-unpaid-invoice boundary? | What happens when one of several invoices remains unpaid? |
| **I — Interface definition** | What makes the request invalid? | Which identifier selects one invoice? | What is ambiguous when identifiers or invoices multiply? |
| **E — Exceptional behavior** | Which empty-input case matters? | What does a retry do? | Which membership states must block reactivation? |

## Choose the next test

1. Mark cases already proved by an existing test.
2. Find the smallest cell where a wrong rule and the intended rule produce
   different results.
3. Write that scenario before adding broader coverage.
4. Add only enough production code to pass it.
