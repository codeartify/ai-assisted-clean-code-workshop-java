# Gate 2 — Worked evidence sheet

This is the review of the **prepared `ai-day1-01-unguided-baseline`**, followed by
the correction on `ai-day1-02-verified-contracts`. It is kept on later branches as
the Gate 2 example. It is not a claim about the coverage of every participant output
or later branch.

| **Question** | **What did you find?** | **CLOSE / FIX / TEST / ESCALATE** |
| --- | --- | --- |
| Which invoices count? | `MembershipBillingReferenceRepository.findByMembershipId` returns every stored billing reference for this membership. The callback checks `isPaid()` on all of them. The README says “all relevant” but does not define whether future, voided, or disputed invoices count. | **ESCALATE** — ask the business owner which invoices are relevant; preserve the current query during this exercise. |
| Can the list be empty? | Before the all-paid query, `MembershipController.paymentReceived` has found a stored billing reference and taken its membership ID. The later query should include that reference on consistent data without concurrent deletion. Although `allMatch` returns true for an empty stream, that alone does not show a bug on this path. | **CLOSE** — for this callback path under those conditions. This does not define a general rule for memberships with no invoices. |
| Which invoice does membershipId mean? | The callback falls back to `findByMembershipId(...).stream().findFirst()`. A membership can have several invoices, and the repository query defines no ordering or selection rule. | **ESCALATE** — ask the API/business owner which invoice to select, or whether an invoice identifier should be required. |
| Do both updates commit together? | The callback saves the invoice, then may save the membership. Branch 01 has no transaction around the whole operation. `pom.xml` includes Spring Data JPA, which supports such a boundary. A later failure could otherwise leave the payment saved on its own. | **FIX** — add the enclosing transaction. A rollback test is still needed to demonstrate failure behavior. |
| Who may send the callback? | The README explicitly leaves authentication unresolved. The local provider and existing tests do not define a production sender-verification contract. | **ESCALATE** — ask the security owner and provider for the callback authentication requirements. |
| Is the many-invoice rule tested? | The six tests in branch 01's `PaymentReceivedControllerTest` each use one invoice. None pays one of two open invoices and checks that the membership stays suspended, then pays the final invoice and checks reactivation. | **TEST** — add that scenario in Gate 3. |

Sources for this prepared review: [callback](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-01-unguided-baseline/java/src/main/java/com/workshop/architecture/fitness/MembershipController.java),
[invoice repository](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-01-unguided-baseline/java/src/main/java/com/workshop/architecture/fitness/MembershipBillingReferenceRepository.java),
[tests](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-01-unguided-baseline/java/src/test/java/com/workshop/architecture/PaymentReceivedControllerTest.java),
[build](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-01-unguided-baseline/java/pom.xml),
and [business story](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-01-unguided-baseline/README.md#day-1-business-story).

## What branch 02 changes

It adds `org.springframework.transaction.annotation.Transactional` and
`@Transactional` to `MembershipController.paymentReceived`. The invoice update
and possible membership update now have one declared transaction boundary.
The all-paid query, identifier fallback, HTTP responses, and six tests remain unchanged.

Record the fix as applied. Do not close the rollback question just because the
annotation exists or the happy-path tests pass: that failure path still needs a test.

## If the agent already wrote the many-invoice test

Change the last row to match the actual implementation. For example, after running
a test like `partialPaymentAndRepeatedCallbackKeepMembershipSuspendedUntilFinalInvoiceIsPaid`:

| **Question** | **What did you find?** | **CLOSE / FIX / TEST / ESCALATE** |
| --- | --- | --- |
| Is the many-invoice rule tested? | The passing test pays one of two open invoices, retries that payment, and then pays the final invoice. It checks the HTTP responses and reloads invoice/membership state. | **CLOSE** — for those scenarios. This does not settle which other invoice types should count. |

Use the real test name and observed result. If it exists but could not run, record
**TEST** with the execution blocker. Do not claim it is missing or passed.
If the agent already added a transaction, inspect its scope instead of applying
the sample fix again; rollback evidence remains a separate question.

The prepared branch 03 adds
`paymentOfOneInvoiceKeepsMembershipSuspendedUntilEveryInvoiceIsPaid`, retained on
branches 04–07. It checks the membership status in HTTP responses for partial and final payment.
It does not reload persisted state for assertions in that scenario. Review those
assertions as they are; do not describe them as the stronger participant test above.

## Record your run

From `java/`, run `mvn -q -DskipTests compile` and
`mvn -q -Dtest=PaymentReceivedControllerTest test`. After a production change,
also run `mvn -q test` as required by the repository instructions.
Write down the branch/commit, commands, actual results, and any checks not run.
This worked sheet does not substitute for executing them on the implementation reviewed.

## Questions that still need an owner

- Business owner: which invoices count toward reactivation?
- API/business owner: which invoice does a membership-only callback identify?
- Security owner/provider contract: how is the callback sender authenticated?

Keep other questions from the actual diff, such as malformed-identifier responses
or replay requirements, in the review notes. A **HOLD** must name an unresolved
decision or missing check. Neither `allMatch` nor a difference from the prepared
test suite is a reason by itself.
