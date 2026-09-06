# AI-Assisted Clean Code Workshop — Java

Runnable Java repository for Day 1 of the Codeartify AI-Assisted Clean Code
Workshop. One payment-callback change progresses through evidence gathering,
behaviour specification, test design, safe refactoring, architecture, and a
repository-specific agent workflow.

**Start the exercises:** [Day 1 participant guides](workshop/exercises.md).
Each gate includes the task, sample prompts, expected output, checks, and a
link to its worked solution. The `workshop/` sequence is the AI-assisted Day 1
course; the top-level `exercises/` folder is supporting upstream material.

## Source provenance

- The course material comes from
  [`modern-software-architecture-design-patterns-materials`](https://github.com/codeartify/modern-software-architecture-design-patterns-materials).
- That repository contains the course documents but no Java application on
  `main`.
- The runnable Java baseline was therefore extracted from the Java project on
  `main` in
  [`modern-software-architecture-design-patterns-mono`](https://github.com/codeartify/modern-software-architecture-design-patterns-mono),
  the repository containing the `MembershipController` selected for the
  workshop.
- Python sources and the earlier architecture-workshop branch progression are
  intentionally excluded.

## Requirements and commands

- Java 21+
- Maven 3.9+

```bash
cd java
mvn test
```

Run the application with:

```bash
cd java
mvn spring-boot:run
```

The health endpoint is `GET http://localhost:8080/health`.

## Repository structure

```text
.
├── .agents/       reusable and repository-specific agent skills
├── exercises/     supporting workshop material
├── java/          Spring Boot application and tests
└── workshop/      briefs, evidence sheets, decisions, and trainer material
```

## Day 1 business story

An external invoice payment callback may be delivered more than once.
Recording a payment must be idempotent. A membership suspended for non-payment
may become active only when all relevant billing references for that membership
are paid and the membership period has not ended. A cancelled membership must
never reactivate. An unknown invoice must produce the documented failure
response.

The callback authentication mechanism is intentionally unresolved because the
source repository does not prove it. Agents must record and escalate that gap
instead of inventing a token or signature scheme.

## Cumulative branch progression

| Branch | Outcome |
| --- | --- |
| `ai-day1-00-start` | Java baseline plus exercise brief, templates, commands, and supplied skills. |
| `ai-day1-01-unguided-baseline` | Deliberately plausible implementation containing reviewable, unsupported decisions. |
| `ai-day1-02-verified-contracts` | Contract evidence, smallest supported corrections, and explicit unresolved security knowledge. |
| `ai-day1-03-behaviour-specification` | ZOMBIES scenario selection and public-surface regression coverage. |
| `ai-day1-04-test-design` | Fast policy feedback combined with slower HTTP/JPA evidence. |
| `ai-day1-05-safe-refactoring` | One smell-driven, behaviour-preserving extraction with a stop condition. |
| `ai-day1-06-vsa-blast-radius` | Payment operation/policy grouped by use case, with a limited source-import dependency check. |
| `ai-day1-07-workflow-skills` | Repository-specific workflow skill, guidance, and synchronized trainer material. |

Each solution branch is the participant starting point for the next exercise.
The complete mapping is in
[`workshop/trainer-branch-guide.md`](workshop/trainer-branch-guide.md).

## Trainer material

- [Day 1 design and trainer schedule](https://docs.google.com/document/d/1h4bXvkMorB8BFPruceNmVvkSDgQ_MLg2IQWtxj0vCOY)
- [Day 1 plan and trainer schedule](https://docs.google.com/document/d/1XkfkWm36xWhyYSrYRbuV1-Mt4tw9chwyuYCSTgflhH0/edit)
- [Editable Google presentation](https://docs.google.com/presentation/d/123zizmbeCzcsFFFdBxZp7V64Hqlunne4XxUQNexBVtw/edit)
- Markdown presentation with speaker notes:
  `workshop/slides/ai_assisted_clean_code_workshop_day1.md`

## License

This workshop repository is provided under the
[Codeartify Workshop License Agreement](LICENSE.md).
