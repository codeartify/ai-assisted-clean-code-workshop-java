# Unguided feature request

Use this prompt with the normal repository context and no extra workshop guidance:

> Fix the payment callback. A membership suspended for non-payment must not be
> reactivated until all of its invoices have been paid. Keep the existing API
> working and update the implementation and tests as needed.

## Review before editing

Treat the agent's output as a hypothesis. Annotate its decisions about:

- what “all invoices” means;
- which invoice is selected when only `membershipId` is supplied;
- transaction boundaries;
- callback authentication;
- behavior for cancelled or ended memberships;
- test scope and untested paths.

The sample branch intentionally contains a plausible, compact fix without
resolving all of those decisions.
