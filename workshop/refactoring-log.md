# Gate 5 sample: ranked safe-refactoring assessment

## Target

Evaluate `MembershipController.paymentReceived` on
`ai-day1-04-test-design`. Do not start by rewriting it. First identify and rank
the design pressures, then authorize one behavior-preserving sequence.

## Observable behavior to preserve

The endpoint, request and response records, HTTP statuses, identifier lookup,
idempotent callback, invoice state, membership state, and the “all relevant
invoices paid” rule remain unchanged. Gate 4 provides two feedback loops:

- 4 fast `MembershipReactivationPolicyTest` cases for the deterministic rule;
- 9 `PaymentReceivedControllerTest` cases for HTTP, lookup, JPA/H2, persistence,
  and response behavior.

## Ranked findings

| Rank | Finding | Evidence in `paymentReceived` | Impact | Cost / behavior risk | Decision |
| --- | --- | --- | --- | --- | --- |
| 1 | Mixed responsibilities | One method validates the request, resolves an invoice, records payment, loads the membership, coordinates reactivation, persists state, and creates the HTTP result. | High: every callback change requires understanding the whole workflow. | Medium effort; controlled by the fast and HTTP/JPA suites. | **SELECT** |
| 2 | Nested and complicated flow | Identifier fallback, mutable response state, payment idempotency, and reactivation conditions are interleaved. | Medium: individual outcomes require tracing several branches. | Low to medium when named inside the selected extraction. | **IMPROVE WITHIN THE SELECTED MOVE** |
| 3 | Primitive obsession | Membership status, suspension reason, and several identifiers are represented as strings across the wider model. | Potentially high, but not local to this callback. | High blast radius and some unresolved domain semantics. | **DEFER** |
| 4 | Speculative generality | The repository has no owned callback-authentication contract and no demonstrated need for a provider abstraction, command hierarchy, or port per repository. | No proven benefit for the current change. | Adds concepts and navigation without protecting known variation. | **REJECT** |

Method length is a symptom. Repeated null or blank checks are repeated syntax,
not automatically duplicated business knowledge.

## Highest-value safe move

Extract one application operation named `RecordMembershipPayment`. This removes
the dominant responsibility pressure without changing the public endpoint or
the established rule. Keep `MembershipReactivationPolicy` as the existing pure
decision boundary.

Low-cost naming improvements such as `validateIdentifier`,
`findBillingReference`, `findMembership`, `reactivateWhenAllowed`, and
`responseMessage` are made inside that extraction. They are not a separate
general cleanup campaign.

## Authorized baby steps

1. Confirm the fast policy and HTTP/JPA tests describe the behavior to preserve.
2. Create `RecordMembershipPayment` and move the existing callback orchestration
   without changing decisions or response messages.
3. Leave `MembershipController.paymentReceived` as the HTTP adapter that delegates
   one request to the application operation.
4. Keep `MembershipReactivationPolicy` unchanged and name the orchestration steps
   inside the extracted operation.
5. Move `@Transactional` with the complete operation so invoice and membership
   updates keep the same transaction scope.
6. Run the fast policy tests, the focused HTTP/JPA tests, and then the full suite.

Commands from `java/`:

```text
mvn -q -Dtest=MembershipReactivationPolicyTest test
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q test
```

## Rejected abstractions

Do not add a universal billing framework, authentication port, payment event,
base command, generic handler hierarchy, or port per repository. None is required
to perform or verify the current refactoring.

## Stop condition

Stop when:

- `MembershipController` adapts HTTP and delegates;
- `RecordMembershipPayment` names and owns the transaction and workflow;
- `MembershipReactivationPolicy` owns the deterministic reactivation decision;
- the established behavior remains green.

Package movement and architectural dependency rules belong to Gate 6.

