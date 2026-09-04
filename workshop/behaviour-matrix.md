# Payment callback ZOMBIES matrix — sample solution

The existing tests all use one invoice. That means both rules below pass:

- wrong rule: reactivate after **any** invoice is paid;
- intended rule: reactivate only after **all relevant** invoices are paid.

The smallest revealing scenario is therefore **Many**: create two unpaid
invoices and pay only one.

|  | Zero | One | Many / more complex |
| --- | --- | --- | --- |
| **B — Boundary behavior** | No matching invoice → `404 Not Found` | Last unpaid invoice is paid → membership becomes `ACTIVE` | One invoice is still unpaid → membership remains `SUSPENDED` |
| **I — Interface definition** | No invoice or membership identifier → `400 Bad Request` | External invoice ID or reference selects one invoice | `membershipId` can match several invoices; which one was paid → **ASK** |
| **E — Exceptional behavior** | Empty identifier input is rejected | Already-paid invoice is retried → success, no additional state change | Cancelled membership, ended period, or another suspension reason → invoice is paid but membership state is unchanged |

**S — Simple scenarios, simple solutions:** add one two-invoice flow test, then
the smallest missing input and lookup tests. Do not build a general scenario
framework.

## Tests that realize the matrix

- `paymentOfOneInvoiceKeepsMembershipSuspendedUntilEveryInvoiceIsPaid`
  proves both sides of the Many boundary: the first payment leaves the
  membership suspended; the last payment activates it.
- `callbackWithoutAnIdentifierIsRejected` proves the empty-input interface.
- `callbackForUnknownInvoiceIsReportedAsNotFound` proves the zero-match boundary.
- Existing tests continue to prove the one-invoice path, retry behavior,
  cancellation, ended membership, and another suspension reason.

## Explicit limits

The matrix does not decide which future, voided, or disputed invoices are
relevant. It does not decide which invoice a membership-only callback means, and
it does not invent callback authentication. Those cells remain **ASK** because
the repository cannot authorize product or security policy.
