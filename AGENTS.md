# Repository guidance for coding agents

## Scope

The Day 1 workshop uses the Java application under `java/`. Treat the Python application as an optional parallel implementation; do not keep both synchronized unless a task explicitly asks for it.

## Commands

Run commands from `java/` with Java 21:

- Focused payment callback tests: `mvn -q -Dtest=PaymentReceivedControllerTest test`
- Full Java verification: `mvn -q test`

Run the focused command first while iterating, then the full command before declaring completion.

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

Use the repository-specific orchestration skill when present. If product, security, or ownership knowledge is missing, stop and ask rather than manufacturing a rule.
