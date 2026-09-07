# Repository guidance for coding agents

## Scope

The Day 1 workshop uses the Java application under `java/`. Treat the Python application as an optional parallel implementation; do not keep both synchronized unless a task explicitly asks for it.

## Commands

Run commands from `java/` with Java 21:

- Payment callback example: `mvn -q -Dtest=PaymentReceivedControllerTest test`
- Plan endpoint example: `mvn -q -Dtest=PlanControllerTest test`
- Full Java verification: `mvn -q test`

Choose focused tests for the affected feature; the examples are not mandatory
for unrelated work. Run the focused checks first while iterating, then the full
command before declaring an implementation complete. For plan-only requests,
report proposed commands separately from any checks actually executed.

## Evidence and boundaries

- Preserve the public `/api/memberships/payment-received` HTTP request and response shape unless the exercise explicitly changes it.
- Verify Spring, JPA, and repository behavior against `java/pom.xml`, the existing repository interfaces, and executable tests.
- Do not invent callback authentication. The current repository does not prove the security mechanism; record it as an unresolved decision.
- `MembershipController` is intentionally broad workshop code. Do not treat its current size or package layout as a preferred pattern.
- Prefer business language such as “all relevant invoices are paid” over repository-call language such as “findByStatus returns empty.”

## Skills

Reusable workflows live under `.agents/skills/`:

- `non-brittle-tests` for behavior definition and test-scope decisions
- `clean-code-refactoring` for small behavior-preserving refactoring
- `modern-application-architecture` for placement, boundaries, and escalation gates

Follow the current exercise's skill restrictions during the workshop. Outside
those controlled comparisons, use a specialist when its decision is needed.
If missing product, security, or ownership knowledge blocks the next step,
ask its owner rather than manufacturing a rule.
