# Gate 2 — Worked evidence and correction

Reveal after [the participant exercise](../gate2-exercise.md).
Compare `ai-day1-01-unguided-baseline` with `ai-day1-02-verified-contracts`.
The [completed six-question sheet](../contract-evidence.md) gives the source for
each answer. This solution describes that prepared comparison, even when read
from a later branch.

## Sample answer for the slides

| **Question** | **What did you find?** | **CLOSE / FIX / TEST / ESCALATE** |
| --- | --- | --- |
| Which invoices count? | The query checks all stored invoices for the membership. “Relevant” invoices are not defined. | **ESCALATE** — ask the business owner. |
| Can the list be empty? | This callback already found an invoice for the membership. The list should contain it on consistent data without concurrent deletion. | **CLOSE** — for this path and those conditions. |
| Which invoice does membershipId mean? | The code chooses the first result; no ordering or selection rule is defined. | **ESCALATE** — ask which invoice should be selected. |
| Do both updates commit together? | Branch 01 has no transaction around the invoice and membership updates. | **FIX** — add the enclosing transaction; rollback still needs a test. |
| Who may send the callback? | The repository does not define callback authentication. | **ESCALATE** — ask the security owner/provider. |
| Is the many-invoice rule tested? | The prepared baseline tests only one invoice per scenario. | **TEST** — cover partial payment and payment of the final invoice. |

## Show the correction

The entire Java change from prepared branch 01 to 02 is:

```diff
+import org.springframework.transaction.annotation.Transactional;

 @PostMapping("/payment-received")
+@Transactional
 ResponseEntity<PaymentReceivedResponse> paymentReceived(@RequestBody PaymentReceivedRequest request) {
```

Both updates belong to recording one payment and its effect on the membership.
`pom.xml` declares Spring Data JPA, so the operation can use an enclosing transaction.
The `allMatch(isPaid)` query, HTTP API, identifier fallback, and tests are unchanged.

Run from `java/` on branch 02:

```sh
mvn -q -DskipTests compile
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q test
```

Report the actual results. Compilation checks available APIs and types. The
focused suite has six scenarios. If they pass, those scenarios pass; it does not
demonstrate rollback on failure or the missing many-invoice scenario.

## If a participant's result is different

- **Many-invoice tests already exist and pass:** mark the coverage question
  **CLOSE**, name the tests, and state what they assert. Keep the tests.
- **Tests exist but have not run:** mark **TEST** and record the missing execution
  or blocker. Do not call existing coverage missing.
- **A transaction already surrounds both updates:** no duplicate code fix is
  needed. Check its scope and record whether rollback has been tested.
- **No many-invoice gap remains:** use ZOMBIES in Gate 3 to find another missing
  scenario with a known expected outcome. Do not invent an unknown business rule.

The prepared many-invoice gap is filled on branch 03 and remains covered on
branches 04–07. The table above remains a review of branch 01, not those later branches.

A **CLOSE** applies to one question. Invoice relevance, membership-only selection,
or authentication may still need an answer. A **HOLD** must identify the unresolved
decision or missing check; it is not a required outcome for every agent output.

## Trainer debrief

Ask: “What could we close? What did we fix? What needs a test? Who must answer
the remaining questions?”

Gate 2 has turned the review into specific actions: one small fix in the prepared
example, a test gap for Gate 3, and three questions for owners. The next exercise
works on actual remaining test gaps.
