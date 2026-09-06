# Gate 4 — Get fast rule feedback and keep useful integration tests

Start: `ai-day1-03-behaviour-specification`

Sample solution: `ai-day1-04-test-design`

Skill: `non-brittle-tests` only.

## What this adds

Gate 3 found the behavior worth protecting. Now choose how to test it so the
agent gets useful feedback quickly during the next change.

- **Fast:** deterministic Java objects in memory, without starting Spring or a
  database. A failing test points at a business decision.
- **Slow:** framework or persistence wiring. Here, MockMvc + Spring + JPA/H2
  exercises HTTP mapping and stored outcomes in one process; no real network
  request is required to make this slower than the policy tests.
- **Very slow:** a real provider, separately running system, or deployed flow.
  Add that cost only when a defined integration risk needs it.

The categories describe the feedback in this workshop, not universal timing
thresholds. Measure commands instead of inventing speed claims.

## Your task

1. Ask the agent to map each important behavior and failure risk to the smallest
   sufficient test boundary. Use [test-scope-map.md](test-scope-map.md).
2. Extract the deterministic reactivation decision and add fast tests for it.
3. Keep endpoint tests where HTTP, lookup, response mapping, persistence, or
   application wiring is part of the risk. Explain each retained test's value.
4. Remove interaction assertions only if they exist and are not part of an owned
   contract. Do not remove useful tests to meet a test-count target.
5. Run fast feedback first, then the HTTP/JPA suite, then the full Java suite.

Prefer real objects. Use a fake, stub, or mock only when a specific unavailable
or expensive boundary requires a double. Do not mock a pure policy's own data.

## Sample agent prompt

```text
Work from ai-day1-03-behaviour-specification. Use non-brittle-tests and normal
repository context. Do not use the refactoring or architecture skills yet.

Review the callback's behavior matrix and tests. Map each risk to fast in-memory
feedback, Spring/HTTP/JPA feedback, or a genuinely external check. Explain what
each boundary proves and its cost in workshop/test-portfolio.md.

Extract only the deterministic membership reactivation decision into a small
policy and add fast behavior tests. Keep time explicit. Preserve the existing
HTTP contract, invoice selection, response messages, stored outcomes, and
transaction scope. Keep the slower tests that check those integrations.
Explain retained overlap; remove unowned interaction assertions only if present.

Do not turn private methods, collaborator names, or repository call counts into
assertions. Do not fabricate provider or authentication tests without a contract.
Do not extract the whole payment operation or move feature packages yet.

Run the fast policy suite, then the focused callback suite, then the full Java
suite. Report what changed, which risks each suite protects, remaining gaps,
and actual command results. State any framework/model coupling that remains.
```

## Expected output and checks

- A test-scope map explaining why each risk needs its chosen boundary.
- A small policy extraction with fast tests of the reactivation decision.
- Retained HTTP/JPA coverage and a reason for any removed assertion or test.
- Separate command results and a list of risks still not exercised.

Run from `java/`, after the new policy test exists:

```sh
mvn -q -Dtest=MembershipReactivationPolicyTest test
mvn -q -Dtest=PaymentReceivedControllerTest test
mvn -q test
```

Stop once rule changes can fail quickly and the integration checks still
protect the callback. Package movement and application-operation extraction
belong to later gates.

## Compare, debrief, and next step

Reveal the [Gate 4 worked solution on branch 04](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-04-test-design/workshop/solutions/gate4-solution.md)
after participants choose their test boundaries.

Which failure can the fast test diagnose? What extra evidence does H2 buy?
Would the test survive moving a collaborator? What remains untested when both
suites pass?

[Gate 5](gate5-exercise.md) uses these two feedback loops during refactoring.
**Pay for framework and database realism when the risk needs it; keep rule
feedback fast.**
