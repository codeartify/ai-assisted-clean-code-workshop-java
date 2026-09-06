# Gate 1 — Review the agent's decisions

Start: `ai-day1-00-start`

Sample solution: `ai-day1-01-unguided-baseline`

Skills: **none**. This is the unguided comparison for the rest of the day.

## What this adds

Turn a plausible implementation into a reviewed diff and a short list of
questions. The task is to notice decisions before accepting or improving code.

The application already has a payment callback. You are asking the agent to
change when it reactivates a membership, not to build a new billing system.

## Your task

1. Start a fresh agent session on the start branch. Give it the feature request
   below and normal application context: code, build files, repository
   instructions, and existing tests. Do not invoke the supplied skills or feed
   it later exercises, trainer solutions, or solution branches.
2. Let it implement the request. Save its diff and reported checks.
3. Review the diff yourself. Mark each decision about business behavior, API
   contracts, errors, security, transactions, responsibilities, tests, or
   architecture. Record what is useful as well as what needs evidence.
4. Rank the three decisions with the largest possible consequence. Give an
   **ACCEPT**, **HOLD**, or **REJECT** verdict and explain why.
5. Stop. Do not repair or refactor the generated implementation yet.

## Feature prompt

Copy only this request into the first agent session:

```text
Fix the payment callback. A membership suspended for non-payment must not be
reactivated until all of its invoices have been paid. Keep the existing API
working and update the implementation and tests as needed.
```

The same request is in [prompts/01-unguided-feature.md](prompts/01-unguided-feature.md).
The review instructions in this exercise are for the participant, after the
agent has produced its implementation.

## Review hints

Ask these of the diff, without fixing it:

- What does the code mean by “all invoices”?
- How does the callback choose the invoice it marks paid?
- What happens if a later operation fails after the invoice update?
- Which membership states can become active?
- What authorizes the caller to record a payment?
- Which new behavior do the tests actually exercise?
- Which new class, dependency, or responsibility did the agent choose?

Mark whether a decision is new in the diff or inherited from existing code.
Preserving existing behavior is a decision too; it does not make that behavior
the desired business policy.

## Expected output

Create participant notes, for example `workshop/gate1-review.md`, containing:

| Location / decision | Evidence noticed | What remains unsupported | Possible consequence |
| --- | --- | --- | --- |
| File and method, plus the decision in plain language | Code, test, or requirement to verify | A concrete question | Who or what could be affected? |

Add the ranked top three, the acceptance verdict, and the agent's actual command
results. An unexecuted check must be recorded as unexecuted. You do not need to
prove every claim in this gate; that is the next exercise.

## Compare after the exercise

Open the [Gate 1 worked review on branch 01](https://github.com/codeartify/ai-assisted-clean-code-workshop-java/blob/ai-day1-01-unguided-baseline/workshop/solutions/gate1-solution.md).
Compare decisions with another participant's output. Different diffs still
require the same review judgment; the sample is not the only acceptable shape.

## Debrief and next step

Which decision had evidence? Which came from nearby code? Which needs a business
or security owner? Why did you rank one consequence above the others?

Carry the question list into [Gate 2](gate2-exercise.md). The takeaway is:
**generated code is a proposal to review, not a reason to trust it.**
