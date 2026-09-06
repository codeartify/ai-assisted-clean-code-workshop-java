# Day 1 — Participant exercise index

One payment-callback change runs through seven exercises. Each guide contains
the task, a sample agent prompt, expected output, checks, stopping point, and
debrief. Read the worked example only after producing your own result.

## Start here

- Work in `java/` with the Java/Maven versions required by the repository.
- Use the exercise's named start branch. Keep participant work on your own
  branch; do not overwrite sample branches or discard local work.
- The production target is `MembershipController.paymentReceived`.
  `PaymentReceivedControllerTest` is its test class, not a production controller.
- Invoke only the skills named for that gate. Gate 1 is intentionally unguided.
- Report actual command results. A method count, annotation, or command written
  in a document is not evidence that a check ran or a failure path was tested.

For example, from a clean checkout, begin Gate 1 with:

```sh
git fetch origin
git switch -c participant/gate-1 origin/ai-day1-00-start
```

Before the next gate, preserve your work and create a fresh participant branch
from its named start. Do not use a hard reset to switch exercises.

## Seven connected gates

| Gate and guide | Start branch | Sample branch | What you produce |
| --- | --- | --- | --- |
| [1 — Review the agent's decisions](gate1-exercise.md) | `ai-day1-00-start` | `ai-day1-01-unguided-baseline` | Annotated diff, three ranked consequences, acceptance verdict |
| [2 — Answer questions with evidence](gate2-exercise.md) | `ai-day1-01-unguided-baseline` | `ai-day1-02-verified-contracts` | Evidence sheet, supported correction, owner questions |
| [3 — Find the next behavior test](gate3-exercise.md) | `ai-day1-02-verified-contracts` | `ai-day1-03-behaviour-specification` | ZOMBIES assessment and approved regression coverage |
| [4 — Choose useful feedback boundaries](gate4-exercise.md) | `ai-day1-03-behaviour-specification` | `ai-day1-04-test-design` | Fast policy tests, retained HTTP/JPA checks, test portfolio |
| [5 — Rank and refactor](gate5-exercise.md) | `ai-day1-04-test-design` | `ai-day1-05-safe-refactoring` | Ranked diagnosis, small approved sequence, refactoring log |
| [6 — Decide where code belongs](gate6-exercise.md) | `ai-day1-05-safe-refactoring` | `ai-day1-06-vsa-blast-radius` | Placement decision, bounded move, dependency check and limits |
| [7 — Teach the next agent](gate7-exercise.md) | `ai-day1-06-vsa-blast-radius` | `ai-day1-07-workflow-skills` | Repository workflow skill and a before/after plan comparison |

## How to use the prompts and solutions

Prompts are examples to adapt, not magic wording. When a guide separates
assessment and implementation, review the assessment before authorizing edits.
Missing business or security policy remains a question for an owner.

All participant guides are available on `main` and branches 00–07. Worked
solutions live under `workshop/solutions/`, starting on their corresponding
solution branch and retained on later branches. Each guide links to the exact
sample branch, so you can compare without switching or losing your work.

For Gate 1, give the agent only the feature request and normal application
context. Do not feed it this index, other gates, or trainer answers before it
produces the baseline. Save the specialist workflows for their named exercises.

The older top-level `exercises/` folder contains upstream architecture-course
material. **This index is the AI-Assisted Clean Code Day 1 sequence.**

See [trainer-branch-guide.md](trainer-branch-guide.md) for the reveal sequence and
[material-sources.md](material-sources.md) for the sources and the differences
between intended exercises and existing sample coverage.
