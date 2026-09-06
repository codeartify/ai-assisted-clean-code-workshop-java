# Gate 3 — Worked ZOMBIES discovery

Reveal after [the participant exercise](../gate3-exercise.md).
Compare `ai-day1-02-verified-contracts` with `ai-day1-03-behaviour-specification`.
Read the branch's [completed matrix](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-03-behaviour-specification/workshop/behaviour-matrix.md).

## The missing case the agent should find

Every existing callback test uses one invoice. With one invoice, both “activate
after any payment” and “activate after all invoices are paid” produce the same
result. The smallest distinguishing case has two open invoices and pays one.

Use a membership suspended for `NON_PAYMENT` whose period has not ended, and
ordinary open invoices in the existing fixture. Do not introduce disputed or
voided invoices whose relevance is undefined.

| Action | Expected result |
| --- | --- |
| Pay the first of two open invoices | That invoice is paid; the other remains open; membership stays `SUSPENDED` |
| Pay the final open invoice | All fixture invoices are paid; membership becomes `ACTIVE` |
| Send no identifier | HTTP 400 |
| Send an unknown invoice identifier | HTTP 404 |

## What branch 03 contains

`PaymentReceivedControllerTest` gains three methods:

- `paymentOfOneInvoiceKeepsMembershipSuspendedUntilEveryInvoiceIsPaid`
- `callbackWithoutAnIdentifierIsRejected`
- `callbackForUnknownInvoiceIsReportedAsNotFound`

The first method exercises both payments through MockMvc. It asserts the HTTP
response's membership status and reactivation flag, including the second
response's previous status. The two error tests assert status codes.

Be precise about the sample's limits: the new two-invoice test does not
explicitly reload both invoice rows and the membership after each request;
the new 400/404 tests do not assert the error body. Participants can strengthen
those checks where an owned contract defines the result. Existing tests do
reload stored state for some other scenarios. Do not describe the whole matrix
as exhaustive persistence or error-body coverage.

## A passing new test is a valid result

Branch 02 already implements the all-paid decision correctly for this fixture.
Branch 03 adds tests and the matrix; **it makes no production-code correction**.
The new test can pass on its first run. Do not introduce a production change
just to make the exercise look like a red/green demonstration.

Run from `java/`:

```sh
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q test
```

The sample callback class contains nine test methods. Report executed results,
not a presumed pass based on that count.

Keep authentication, membership-only selection, and broader invoice relevance
as ASK. Gate 4 now chooses which known rules deserve faster feedback.
