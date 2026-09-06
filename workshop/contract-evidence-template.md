# Gate 2 — Contract evidence sheet

Use with [gate2-exercise.md](gate2-exercise.md). Bring your Gate 1 questions and
add rows when the diff raises another material question.

| Question / claim | Exact source and observation | What that evidence establishes | CLOSE / FIX / TEST / ESCALATE | Correction or remaining question |
| --- | --- | --- | --- | --- |
| Does the installed Java/Spring version support the generated code? | | | | |
| How are the callback identifiers resolved? | | | | |
| What do the request/response and error contracts define? | | | | |
| What does the query establish about the invoices being considered? | | | | |
| Is the suspected empty-result case reachable here? | | | | |
| What transaction surrounds the state changes? | | | | |
| Which callback behaviors do existing tests exercise? | | | | |
| What evidence defines provider/authentication requirements? | | | | |

- **CLOSE:** evidence answers the question under stated conditions.
- **FIX:** a specific correction follows from an established contract.
- **TEST:** a known expected outcome needs executable coverage.
- **ESCALATE:** an owner or external contract must supply missing policy.

Record commands and actual results separately from source inspection. State
the limits of a passing check. A transaction annotation is not a rollback test;
a record defines an API shape, not proof of every outcome.

## Stop-and-ask list

| Unanswered question | Required owner or contract | Which decision/change must wait? |
| --- | --- | --- |
| | | |

Do not invent authentication, invoice relevance, or an invoice-selection policy
to fill an empty cell.
