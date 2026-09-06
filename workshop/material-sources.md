# Sources and reconciliation for the Day 1 exercises

These guides combine the supplied trainer document and presentation (including
speaker notes) with the existing repository briefs and the actual branch diffs.

- [AI-Assisted Clean Code Workshop — Day 1 Design & Trainer Schedule](https://docs.google.com/document/d/1h4bXvkMorB8BFPruceNmVvkSDgQ_MLg2IQWtxj0vCOY)
- [AI-Assisted Clean Code Workshop — Day 1 slides](https://docs.google.com/presentation/d/123zizmbeCzcsFFFdBxZp7V64Hqlunne4XxUQNexBVtw)
- [Existing Day 1 plan](https://docs.google.com/document/d/1XkfkWm36xWhyYSrYRbuV1-Mt4tw9chwyuYCSTgflhH0/edit)
- Existing repository exercise briefs, templates, evidence, behavior matrix,
  test portfolio, refactoring log, architecture decision, and supplied skills.
- [James Grenning — TDD Guided by ZOMBIES](https://blog.wingman-sw.com/tdd-guided-by-zombies),
  as introduced in the trainer material. The B/I/E-by-Z/O/M matrix is the
  workshop's application of that scenario-selection guide.

## Read the named branch when checking a worked example

The learner outcome and the current sample's evidence are stated separately.
The guides resolve these inconsistencies in older wording:

- The production method is `MembershipController.paymentReceived`; the similarly
  named `PaymentReceivedControllerTest` is a test class.
- Gate 2 adds a transaction boundary. It does not settle invoice relevance,
  ambiguous membership-only lookup, or authentication.
- Gate 3 uses ZOMBIES. Branch 02 already has the all-paid implementation, so
  branch 03 adds coverage without a production-code correction. Its new error
  tests check status, not body; its two-invoice test does not explicitly reload
  every stored row after each request.
- Gate 4 adds four policy tests and keeps all nine HTTP/JPA tests. No repository
  call-count assertions existed to remove. Fast execution without starting
  Spring does not remove the policy's dependency on the JPA-annotated entity.
- Gate 5 begins with the existing policy and a ranked diagnosis. It extracts the
  application operation, not the same policy a second time.
- Gate 6 groups the operation, policy, and fast test. The shared HTTP controller,
  API records, entities, repositories, and callback test stay in their existing
  locations. The boundary check scans certain source imports; it is not a full
  dependency analyzer.
- Gate 7 contains a sample workflow skill. A real before/after agent-plan
  comparison is participant output, not something already proven by that file.

All exercise guides are available on every Day 1 branch. Detailed worked
solutions appear from their solution branch onward. The trainer and slide
documents remain external teaching sources; adding these Markdown guides does
not edit those documents.
