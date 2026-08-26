# Sample safe-refactoring log

## Observable behavior to preserve

The endpoint, request and response records, HTTP statuses, idempotent callback,
invoice state, membership state, and the “all relevant invoices paid” rule stay
unchanged. The tests from the previous branch are the safety net.

## Primary smell

`MembershipController.paymentReceived` mixes HTTP adaptation with identifier
validation, reference resolution, transaction orchestration, business decisions,
persistence, and response wording. The primary issue is mixed responsibilities,
not merely method length.

## Smallest useful move

Extract one application operation named `RecordMembershipPayment`. Keep the HTTP
controller as an adapter and retain the pure `MembershipReactivationPolicy`.
Do not introduce a billing framework, base command class, generic handler, or
new port for each repository.

## Baby steps used for the sample

1. Move the existing orchestration without changing the public endpoint.
2. Name validation, lookup, reactivation, and response wording inside the use case.
3. Keep transaction ownership with the complete application operation.
4. Re-run the focused public-surface and policy tests after each meaningful move.

## Stop condition

Stop when the controller delegates one request to one application operation, the
business decision remains explicit and fast to test, and the established behavior
passes. Further package or architecture movement belongs to the next exercise.
