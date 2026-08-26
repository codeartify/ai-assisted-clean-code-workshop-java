# Architecture decision: record membership payment slice

## Feature classification

`Record membership payment` is one externally triggered use case. It has a clear
request, one transaction, observable outcomes, and a business rule about when a
membership may be reactivated. It is therefore a useful vertical slice boundary.

## Protected rules

- One callback marks at most its resolved billing reference paid.
- A non-payment suspension is lifted only while the membership period is active
  and all relevant invoices are paid.
- Cancelled memberships stay cancelled.
- Retried callbacks are idempotent.

## Expected change axes

- Callback delivery and identifier formats may change together.
- The definition of a relevant invoice may change with billing policy.
- Membership reactivation rules may change with product policy.
- Other membership use cases should not need edits for those changes.

## Lightest structure

The application operation and its policy live in
`fitness.payment.record`. Existing membership entities, DTOs, and repositories
remain explicit shared code for now. This is not a claim that every feature needs
its own domain model or port. Escalate only when actual change pressure appears.

## Blast-radius rule

A slice may use explicit shared concepts but must not depend directly on another
payment slice. `VerticalSliceBoundaryTest` makes that rule executable. This keeps
the intended scope visible to developers and coding agents.

## DRY, YAGNI, and domain language

Vertical slices do not excuse duplication. Remove duplicated policy when it is
the same business knowledge, and keep coincidentally similar workflow local. Do
not pre-build abstractions for imagined slices. Names such as
`allRelevantInvoicesArePaid` convey the business invariant in the natural
language an agent can reason about; repository mechanics do not.
