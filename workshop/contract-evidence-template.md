# Gate 2 — Answer the six review questions

Use with [gate2-exercise.md](gate2-exercise.md). Record the branch or commit you
reviewed: __________.

In “What did you find?”, give a short answer, name the file/method/test that
supports it, and say what remains unknown. Use the same six questions as the slides.

| **Question** | **What did you find?** | **CLOSE / FIX / TEST / ESCALATE** |
| --- | --- | --- |
| Which invoices count? | | |
| Can the list be empty? | | |
| Which invoice does membershipId mean? | | |
| Do both updates commit together? | | |
| Who may send the callback? | | |
| Is the many-invoice rule tested? | | |

- **CLOSE:** the evidence answers this question; state its limits.
- **FIX:** the code needs a specific correction supported by the requirements and repository.
- **TEST:** a known outcome needs a test, or an existing test still needs to be run.
- **ESCALATE:** a business/security owner or provider contract must supply the missing rule.

The answer depends on the code you actually received. If the many-invoice test
already exists, inspect its assertions and run it. Do not report it as missing.
A passing test can close that coverage question without settling invoice relevance
or callback authentication.

## Checks and changes

- Commands run and actual results: __________.
- Corrections made and why: __________.
- Checks not run, or evidence still missing: __________.

A compilation result is not a business decision. `@Transactional` declares a
transaction boundary; a passing happy-path test does not prove rollback on failure.

## Questions for an owner

For each **ESCALATE** row, record who needs to answer (role or contract), the
question, and which change must wait. Put any additional Gate 1 questions here too.
Do not invent an invoice-selection rule or authentication scheme to complete the sheet.
