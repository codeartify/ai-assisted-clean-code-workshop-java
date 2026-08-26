# Participant exercise briefs

## 1. Review an unguided AI change

Start: `ai-day1-00-start`  
Sample: `ai-day1-01-unguided-baseline`

Give the agent only the feature request and normal repository context. Review the resulting diff for decisions about behavior, ownership, failure semantics, transactions, security, test scope, and architecture. Do not fix the code yet.

Point: generated code is a reviewable hypothesis, not a trusted solution.

## 2. Verify contracts before implementation

Start: `ai-day1-01-unguided-baseline`  
Sample: `ai-day1-02-verified-contracts`

Complete `contract-evidence-template.md`. Verify the installed Spring version, repository APIs, request and response types, exception mapping, transaction behavior, and callback authorization evidence. Correct only claims the repository disproves. Keep missing knowledge visible.

Point: plausibility is not evidence; verify or explicitly escalate.

## 3. Make behavior explicit before test generation

Start: `ai-day1-02-verified-contracts`  
Sample: `ai-day1-03-behaviour-specification`

Use the `non-brittle-tests` skill. Write Given/When/Then behavior for one invoice, multiple invoices, callback retry, cancelled membership, unknown invoice, missing identifier, and ended membership. Add the smallest public-surface tests that protect these outcomes.

Point: behavior first; test shape second.

## 4. Give the agent feedback at the right cost

Start: `ai-day1-03-behaviour-specification`  
Sample: `ai-day1-04-test-design`

Choose the smallest sufficient scope for each risk. Keep HTTP/JPA tests for contracts that cross those boundaries; move the reactivation decision into a fast deterministic policy test. Remove interaction assertions that are not part of the business contract.

Point: pay for realism only where the risk requires it, and keep most feedback fast.

## 5. Guide one safe refactoring

Start: `ai-day1-04-test-design`  
Sample: `ai-day1-05-safe-refactoring`

Use `clean-code-refactoring` in Full Format. Identify one primary smell, choose the smallest useful refactoring, run focused tests after each meaningful step, and write a stop condition. Do not create a universal billing framework.

Point: safe refactoring is a sequence of behavior-preserving decisions, not a rewrite.

## 6. Constrain the blast radius

Start: `ai-day1-05-safe-refactoring`  
Sample: `ai-day1-06-vsa-blast-radius`

Use `modern-application-architecture`. Classify the feature, list protected rules and change axes, propose the lightest structure, then move the payment callback into a vertical slice. Do not make one slice depend directly on another. Add a boundary test.

Point: put code where its intended scope of change is visible and enforceable.

## 7. Encode the workflow for the next change

Start: `ai-day1-06-vsa-blast-radius`  
Sample: `ai-day1-07-workflow-skills`

Create a focused repository skill that tells the agent when to use it, what evidence to inspect, how to define behavior, when to invoke the three general skills, what not to invent, what commands to run, when to stop, and what completion report to produce. Compare an agent plan with and without the skill.

Point: codify repeatable judgment; keep facts, workflows, and executable constraints in their proper homes.
