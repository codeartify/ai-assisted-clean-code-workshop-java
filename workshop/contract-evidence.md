# Payment callback contract evidence

This is the sample output for Exercise 2. It records what the repository proves,
what the exercise deliberately preserves, and what still needs an owner.

| Question | Evidence | Decision for this branch |
|---|---|---|
| Which runtime contract applies? | `java/pom.xml` declares Java 21 and Spring Boot 4.0.6. | Use APIs available in that installed version; do not answer from model memory alone. |
| What is the public request shape? | `PaymentReceivedRequest` has external invoice id, external reference, membership id, and paid timestamp. | Preserve all four fields and their current precedence. |
| What is the public response shape? | `PaymentReceivedResponse` and the existing controller tests define seven fields and current messages. | Preserve the response shape and established messages. |
| How is a billing reference resolved? | The controller tries external id, then external reference, then the first result for membership id. | Preserve precedence for now. “First” is not a stable business rule and is an open product decision. |
| What does “all invoices paid” mean here? | The repository exposes all billing references for a membership; the entity exposes `isPaid()`. | For this exercise, all stored billing references for that membership must be paid. Relevance by date or cancellation is unresolved. |
| What state changes together? | One callback marks an invoice paid and may reactivate a membership. Both use JPA repositories. | Make the application operation transactional so partial state is not committed. |
| How are failures mapped? | The controller maps missing identifiers to 400 and unknown references/memberships to 404. | Preserve those mappings. Invalid UUID formatting is not specified and needs a deliberate contract. |
| How is the callback authenticated? | No security dependency, filter, signature field, or authentication test is present. | Do not invent a mechanism. Record this as a security decision for the product owner. |
| What proves idempotency? | `isPaid()` guards the state update, and an existing public-surface test repeats a callback. | Preserve retry behavior and add no interaction-based assertion. |

## Smallest verified correction

`paymentReceived` is now transactional because paying the invoice and possibly
reactivating the membership form one application operation. This is supported by
the installed Spring/JPA stack. No authentication scheme or invoice-selection
rule was manufactured from guesswork.

## Still unresolved

- Which invoice is selected when only a membership id is provided?
- Do voided, future, disputed, or otherwise irrelevant invoices count?
- Which party owns callback authentication and replay protection?
- Should an invalid UUID be a 400, and is that part of this API's contract?
