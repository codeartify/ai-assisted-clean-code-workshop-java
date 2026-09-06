# Gate 2 — Worked evidence and correction

Reveal after [the participant exercise](../gate2-exercise.md).
Compare `ai-day1-01-unguided-baseline` with `ai-day1-02-verified-contracts`.
The branch's detailed artifact is
[contract-evidence.md](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-02-verified-contracts/workshop/contract-evidence.md).

## Results of the investigation

| Gate 1 question | What the source establishes | Result |
| --- | --- | --- |
| Are the APIs installed? | `pom.xml`: Java 21, Spring Boot 4.0.6, JPA. The repository and entity define the query and `isPaid()`. | CLOSE the API-availability claim; execute compilation too |
| Can the all-paid query be empty here? | The callback has already resolved a stored billing reference for this membership before querying its references. | CLOSE for this path on consistent data; do not invent a general zero-invoice policy |
| Which invoices count? | The query returns all stored references, not a product definition of relevance. | ESCALATE relevance beyond the workshop's existing fixture |
| Which invoice does membership ID identify? | One membership can have several invoices; `findFirst()` has no defined ordering. | ESCALATE; preserve the current fallback for this exercise |
| Can the two updates be one operation? | Both updates use JPA and belong to recording this payment and possible reactivation. Spring transaction support is installed. | FIX the missing enclosing transaction |
| Is callback authentication defined? | No authentication/signature contract or security test establishes it. | ESCALATE before production use |
| Does coverage exercise partial payment? | The six existing callback tests use one invoice each. | TEST next: multiple invoices with one still unpaid |

## Smallest correction

The Java diff adds the Spring `Transactional` import and `@Transactional` to
`MembershipController.paymentReceived`. It preserves the all-paid check from
branch 01, the identifier fallback, request/response records, and HTTP mappings.
It does not implement a new authentication or invoice-selection rule.

Run from `java/`:

```sh
mvn -q -DskipTests compile
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q test
```

Compilation establishes availability/type compatibility. The focused suite
exercises its six existing scenarios. Neither demonstrates rollback after a
mid-operation failure, production-database behavior, or the missing many-invoice
case. The transaction annotation supplies the intended boundary; a failure-path
test would be additional evidence that it works as required.

## Stop-and-ask list

Keep invoice relevance, membership-only selection, invalid-UUID response policy,
and callback authentication/replay requirements visible. Identify the business,
API, or security owner required; do not invent a person's name.

The handoff is concrete: one supported technical correction, several closed
questions, a coverage gap for Gate 3, and unresolved owner decisions.
