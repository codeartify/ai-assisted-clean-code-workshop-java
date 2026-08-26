# Payment callback behavior matrix

This sample matrix names observable outcomes before choosing test mechanics.

| Given | When | Then | Risk protected | Smallest sufficient test |
|---|---|---|---|---|
| Active membership; one open invoice | Known invoice is paid | Invoice becomes paid; membership stays active | Callback state transition | HTTP + JPA integration |
| Suspended for non-payment; one open invoice; period active | Invoice is paid | Membership becomes active | Reactivation rule and wiring | HTTP + JPA integration |
| Suspended for non-payment; two open invoices | First invoice is paid | First invoice becomes paid; membership stays suspended | Partial-payment defect | HTTP + JPA integration |
| Same membership; one remaining open invoice | Remaining invoice is paid | Membership becomes active | Completion of multi-invoice flow | HTTP + JPA integration |
| Already-paid invoice | Callback is retried | No additional state transition; successful idempotent response | Delivery retry | HTTP + JPA integration |
| Cancelled membership | Invoice is paid | Invoice becomes paid; membership stays cancelled | Terminal status invariant | Policy unit + one wiring test |
| Ended membership period | Invoice is paid | Invoice becomes paid; membership stays suspended | Date boundary | Policy unit + one wiring test |
| Suspension reason is not non-payment | Invoice is paid | Invoice becomes paid; suspension remains | Reason invariant | Policy unit |
| No identifier | Callback arrives | 400 response | Input contract | HTTP test |
| Unknown invoice | Callback arrives | 404 response | Lookup contract | HTTP test |

## Explicit limits

The matrix does not decide whether future, voided, or disputed invoices are
relevant. It also does not invent callback authentication. Those are product and
security questions, not gaps to fill with model intuition.
