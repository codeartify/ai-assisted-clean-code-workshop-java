---
marp: true
paginate: true
size: 16:9
theme: default
title: "AI-Assisted Clean Code Workshop — Day 1"
description: "Build future-proof, maintainable software effectively with AI"
style: |

    :root { --ink:#0e0e0e; --red:#a30000; --soft:#f3f3f3; --paper:#fff; }
    section { background:var(--paper); color:var(--ink); font-family:Roboto,Arial,sans-serif; padding:48px 64px; font-size:25px; }
    h1 { font-size:48px; line-height:1.02; margin:0 0 24px; } h2 { font-size:28px; } h3 { font-size:21px; }
    .kicker { color:var(--red); font:700 15px 'Roboto Mono',monospace; letter-spacing:.08em; text-transform:uppercase; }
    .callout { border-left:7px solid var(--red); font-weight:700; padding:16px 22px; margin-top:26px; }
    .grid { display:grid; grid-template-columns:1fr 1fr; gap:20px; margin-top:24px; }
    .card { background:var(--soft); border-top:5px solid var(--ink); padding:18px; }
    .code { background:#111; color:#eee; padding:22px; font:18px 'Roboto Mono',monospace; white-space:pre-wrap; }
    section.section { background:#111; color:white; display:flex; flex-direction:column; justify-content:center; }
    section.section h1 { font-size:62px; } section.section .number { color:var(--red); font:700 96px 'Roboto Mono'; }
    section.practice { border-left:26px solid var(--red); } section.resolution { background:var(--soft); }
    table { width:100%; border-collapse:collapse; font-size:18px; } th { background:#111; color:white; } th,td { padding:9px; border:1px solid #ddd; }

---

<p class="kicker">AI-ASSISTED CLEAN CODE · DAY 1</p>

# Build maintainable software effectively with AI

One payment change. Seven connected gates. A codebase that guides the next agent.

<!--
Story beat: Open with the outcome, not a catalogue of Clean Code rules. Trainer: Tell participants that the same payment-callback change will move through every block; nothing resets conceptually. Ask: What would make an AI-generated change safe to keep six months from now? Transition: We begin with their own experience of impressive but uncertain output. Timing: 2 minutes.
-->

---

<p class="kicker">CONNECT · 09:00–09:20</p>

# Two changes, two feelings

<div class="grid"><div class="card"><h3>Impressive</h3>What did a coding agent do recently that genuinely surprised you?</div><div class="card"><h3>Not fully trusted</h3>What looked convincing—but you still hesitated to keep?</div></div>

<div class="callout">Pairs: name the evidence you had and the evidence you wished you had.</div>

<!--
Story beat: Surface the emotional gap between fluency and trust before introducing terminology. Trainer: Give pairs six minutes, then collect examples under behavior, tests, design, security, and architecture. Ask: Was the hesitation about style, or about a decision whose source was unclear? Transition: The workshop turns that hesitation into an explicit workflow. Timing: 12 minutes pair work and 8 minutes harvest.
-->

---

<p class="kicker">THE JOB</p>

# Clean Code is supporting judgment—not the syllabus

## Guide an AI agent to leave the software easier for humans and future agents to understand, verify, and change.

<div class="callout">Success is a maintainable change and a reusable workflow—not memorizing a smell catalogue.</div>

<!--
Story beat: Set the north star. Trainer: Explain that testing, smells, refactorings, domain language, architecture, AGENTS.md, and skills enter only when the change needs them. This is why the first gates are evidence and behavior rather than names or formatting. Transition: Introduce the concrete membership-payment story that carries every concept. Timing: 3 minutes.
-->

---

<p class="kicker">CONNECTED CASE</p>

# One callback. Two invoices. One dangerous shortcut.

<div class="grid"><div class="card"><h3>State</h3>Membership is suspended for non-payment.</div><div class="card"><h3>Event</h3>A payment callback marks one invoice paid.</div><div class="card"><h3>Invariant</h3>Reactivate only when all relevant invoices are paid.</div><div class="card"><h3>Unknown</h3>Callback authentication has no repository evidence.</div></div>

<div class="callout">The agent can implement this request—and still invent what “relevant,” atomic, or authenticated means.</div>

<!--
Story beat: Anchor the day in MembershipController.paymentReceived. Trainer: Show the start branch and trace the current behavior: lookup a billing reference, mark it paid, load the membership, and possibly reactivate. Explain that a second open invoice exposes the defect. Ask: Which words in the feature request still need definition? Transition: The seven gates progressively turn this incomplete request into an owned change. Timing: 5 minutes.
-->

---

<p class="kicker">THE STORY ARC</p>

# Each gate produces the input for the next

1. Hypothesis
2. Evidence
3. Behavior
4. Feedback
5. Meaning
6. Boundary
7. Workflow

<div class="callout">The codebase becomes a better prompt one artifact at a time.</div>

<!--
Story beat: Make the handoffs visible before the first loop. Trainer: Name the artifact at each gate: decision inventory, contract sheet, behavior matrix, test portfolio, refactored use case, bounded slice, repository skill. Stress that later gates are unsafe without earlier outputs. Transition: Briefly show how these gates fit the 4C rhythm and schedule. Timing: 4 minutes.
-->

---

<p class="kicker">4C RHYTHM</p>

# Connect once. Alternate Concept and Practice. Conclude once.

| Time | Gate | Branch |
| --- | --- | --- |
| 09:20 | Review hypothesis | 00 → 01 |
| 10:10 | Verify contracts | 01 → 02 |
| 11:15 | Define behavior | 02 → 03 |
| 12:05 | Design feedback | 03 → 04 |
| 13:55 | Refactor toward meaning | 04 → 05 |
| 14:45 | Bound blast radius | 05 → 06 |
| 15:50 | Encode workflow | 06 → 07 |

<!--
Story beat: Explain the cadence, not every timetable detail. Trainer: Each loop starts with just-in-time theory, then participant work, trainer sample solution, debrief, and explicit handoff. Mention the breaks and lunch. Transition: Gate 1 starts with the unguided control condition. Timing: 3 minutes.
-->

---

<!-- _class: section -->

<p class="number">01</p>
<p class="kicker">GATE 1 · REVIEW</p>

# What did the agent decide?

Before fixing code, expose the decisions hidden inside a plausible diff.

<!--
Story beat: Move from intuition to a review discipline. Trainer: Say that this block is not yet about refactoring. It creates the list of claims that later blocks can verify. Transition: Show the intentionally minimal feature prompt. Timing: 1 minute.
-->

---

<p class="kicker">CONTROL CONDITION</p>

# Give the agent the request—not the workshop skills

<div class="code">Fix the payment callback.

A membership suspended for non-payment must not
be reactivated until all of its invoices are paid.

Keep the existing API working. Update implementation
and tests as needed.</div>

## Why unguided?

We need a baseline showing what normal repository context makes the agent infer on its own.

`START ai-day1-00-start`

<!--
Story beat: Establish the control condition. Trainer: Participants may use Codex, Claude Code, Cursor, Copilot, or another repository agent, but they must not invoke supplied skills. Explain that nondeterministic outputs are acceptable because the curated branch guarantees a common debrief. Transition: Inspect the generated all-paid line as a proposed interpretation, not as truth. Timing: 2 minutes.
-->

---

<p class="kicker">PLAUSIBLE OUTPUT</p>

# One good-looking line contains several business decisions

<div class="code">boolean allBillingReferencesArePaid = repository
    .findByMembershipId(membershipId)
    .stream()
    .allMatch(BillingReference::isPaid);</div>

## Hidden decisions

Which invoices count? What if none exist? Is the read consistent with the update? Who owns this rule?

<!--
Story beat: Demonstrate the review lens on one concrete line. Trainer: Acknowledge what is impressive: the agent found the relevant repository method and expressed the requested predicate locally. Then annotate invoice relevance, empty-set semantics, transaction consistency, and ownership. Ask: Which of those can code alone answer? Transition: Broaden the annotation to all decision categories. Timing: 5 minutes.
-->

---

<p class="kicker">DECISION INVENTORY</p>

# Review meaning and risk before elegance

<div class="grid"><div class="card"><h3>Behavior</h3>What does “all” mean? What is observable?</div><div class="card"><h3>Contracts</h3>Which identifier wins? Which errors remain?</div><div class="card"><h3>Safety</h3>Atomicity, retry, authorization, failure path.</div><div class="card"><h3>Design</h3>Who owns the rule? What else can this diff reach?</div></div>

<div class="callout">The output of this gate is a ranked question list—not cleaner code.</div>

<!--
Story beat: Give participants a reusable annotation taxonomy. Trainer: Keep style deliberately out of scope so teams do not polish an unverified solution. Add test scope and architecture to the design category if participants surface them. Transition: Put the taxonomy into practice on their diff and the common baseline. Timing: 4 minutes.
-->

---

<p class="kicker">CONCRETE PRACTICE 1</p>

# Annotate decisions. Do not fix them yet.

**Branch:** 00 → 01

1. Run or inspect the unguided change
2. Mark every unsupported decision
3. Rank the three largest consequences
4. Decide Accept · Hold · Reject

<div class="callout">Debrief: Which decision had evidence? Which required an owner? Which had the widest blast radius?</div>

<!--
Participant work: 22–25 minutes. Trainer sample: Switch to ai-day1-01-unguided-baseline and show useful work plus unproven invoice relevance, empty-set behavior, identifier fallback, atomicity, callback authentication, and narrow tests. Do not fix the diff during the walkthrough. Debrief with the three questions shown. Transition: The annotated decisions are now claims to verify in Gate 2. Timing: 50-minute loop including concept, sample, and debrief.
-->

---

<p class="kicker">GATE 1 RESOLVED</p>

# We have a hypothesis and a prioritized question list

## Now we have

Decision inventory: behavior · contracts · safety · ownership · test scope · architecture

<div class="callout">NEXT → Determine which claims the repository can prove and where the agent must stop.</div>

<!--
Story beat: Make the handoff explicit. Trainer: Ask one participant to state the output without mentioning code style. Reinforce that the agent may have produced a mostly correct diff; the review obligation is unchanged. Transition: Gate 2 introduces sources of authority and the limits of each. Timing: 2 minutes.
-->

---

<!-- _class: section -->

<p class="number">02</p>
<p class="kicker">GATE 2 · VERIFY</p>

# What is evidence here?

Turn every material claim into verified, disproved, or unresolved knowledge.

<!--
Story beat: The question list becomes an evidence task. Trainer: Explain that this block separates repository facts from product and security decisions. Transition: Define contract and evidence using the same callback. Timing: 1 minute.
-->

---

<p class="kicker">EVIDENCE LADDER</p>

# Different sources prove different things

- **Compiler:** Syntax, types, available symbols
- **Focused test:** Only the exercised observations
- **Repository contract:** Current API, persistence, conventions
- **Product / security owner:** Missing policy and authority

<div class="callout">More confidence from the model does not move a claim up this ladder.</div>

<!--
Story beat: Clarify proof categories. Trainer: Use a real example for each layer: compilation accepts allMatch; a happy-path test misses partial payment; request/response records preserve the HTTP shape; only an owner can define relevant invoices or authentication. Ask: Where does framework documentation fit? It supports installed API claims but not business intent. Transition: Apply the ladder to a claim/evidence/result sheet. Timing: 6 minutes.
-->

---

<p class="kicker">CONTRACT EVIDENCE</p>

# Claim → source → result → remaining uncertainty

| Claim | Inspect | Result |
| --- | --- | --- |
| Runtime API | pom.xml | Java 21 · Spring Boot 4.0.6 |
| Request/response | records + endpoint tests | Preserve shape and current errors |
| Atomic update | transaction boundary | Missing → add around use case |
| Authentication | security code + contract | No evidence → stop and ask |

<!--
Story beat: Show the actual structure participants will complete. Trainer: Open workshop/contract-evidence.md and demonstrate one verified row and one unresolved row. Stress that repository queries prove available data, not what the business calls relevant. Transition: Contrast a green build with semantically sufficient evidence. Timing: 5 minutes.
-->

---

<p class="kicker">PROOF BOUNDARIES</p>

# Green can still be semantically incomplete

<div class="grid"><div class="card"><h3>Compiler proves</h3>The symbols exist and the code is type-compatible with this build.</div><div class="card"><h3>It does not prove</h3>The invoice set is correct, the transaction is atomic, or the callback is authorized.</div></div>

<div class="callout">Passing one happy-path test proves even less than many teams assume.</div>

<!--
Story beat: Prevent Gate 2 from becoming a framework-documentation scavenger hunt. Trainer: Re-run the narrow command and ask participants to name the exact claim it supports. Show how the all-paid change can compile and pass while the partial-payment scenario is absent. Transition: Participants now verify and mark stop conditions themselves. Timing: 4 minutes.
-->

---

<p class="kicker">CONCRETE PRACTICE 2</p>

# Verify or explicitly escalate every material claim

**Branch:** 01 → 02

1. Inspect build and installed versions
2. Trace owned API and persistence contracts
3. Run the narrowest executable check
4. Write the stop-and-ask list

<div class="callout">Debrief: What did each source prove? Which current behavior is not a desired rule?</div>

<!--
Participant work: 20–22 minutes. Trainer sample: Switch to ai-day1-02-verified-contracts. Show the transactional correction and preserved request/response behavior. Keep invoice relevance, membership-ID selection, invalid UUID behavior, and callback authentication visible as unresolved. Contrast compile success with the missing partial-payment case. Transition: Known facts can now become behavior; unknowns must remain outside the tests. Timing: 50-minute loop.
-->

---

<p class="kicker">GATE 2 RESOLVED</p>

# We have verified contracts and named unknowns

## Now we have

Claim/evidence/result sheet + focused command + stop-and-ask list

<div class="callout">NEXT → Express only owned behavior as caller-observable outcomes.</div>

<!--
Story beat: The implementation is no longer merely plausible, but not every product/security question is resolved. Trainer: Ask participants to distinguish ‘current behavior’ from ‘desired behavior.’ Transition: Gate 3 converts owned rules into behavioral sentences before test mechanics. Timing: 2 minutes.
-->

---

<!-- _class: section -->

<p class="number">03</p>
<p class="kicker">GATE 3 · ZOMBIES</p>

# Which test should we write next?

Move from Zero to One to Many. Check boundaries, interfaces and exceptions as
the cases grow.

<!--
Story beat: Gate 2 ended with one concrete testing gap: every existing callback test uses one invoice. Gate 3 asks which test should come next. Trainer: Say: “We do not need more tests at random. We need the smallest next case that can prove or disprove the rule.” Introduce ZOMBIES as the guide used in this block. Transition: First show why the current One-invoice case cannot reveal the defect. Timing: 1 minute.

[Sources]
- James Grenning, “TDD Guided by ZOMBIES”: https://blog.wingman-sw.com/tdd-guided-by-zombies
-->

---

<p class="kicker">WHY ONE IS NOT ENOUGH</p>

# One invoice cannot expose the bug

## 1 invoice → paid → ACTIVE
## “Any payment” passes ✓
## “All paid” passes ✓

<div class="callout">Both rules pass. Move to MANY to reveal the difference.</div>

<!--
Story beat: Make the missing coverage obvious. Trainer: Explain that with one invoice, both implementations return ACTIVE after payment: the incorrect rule “reactivate after any payment” and the intended rule “reactivate after all payments.” Ask: “What is the smallest extra case that makes those rules produce different results?” The answer is two invoices. Transition: Moving from One to Many is exactly the kind of next-test decision ZOMBIES helps us make. Timing: 3 minutes.

[Sources]
- Workshop branch 02: https://github.com/codeartify/ai-assisted-clean-code-workshop-java/tree/ai-day1-02-verified-contracts
-->

---

<p class="kicker">ZOMBIES</p>

# A practical guide for choosing the next test

<div class="grid"><div class="card"><h3>Z → O → M</h3><strong>ZERO → ONE → MANY</strong><br/>Start with the smallest case. Add complexity only when it teaches something.</div><div class="card"><h3>B · I · E</h3><strong>BOUNDARIES · INTERFACES · EXCEPTIONS</strong><br/>Check each as the cases grow.</div></div>

<div class="callout">S — Keep scenarios and solutions simple.</div>

<!--
Story beat: Give participants a practical way to choose the next TDD scenario. Trainer: Explain the two dimensions. Move from Zero to One to Many. While doing that, check boundary behavior, the interface the caller needs, and exceptional behavior. The S is the discipline across every step: keep the scenario and the production change simple. ZOMBIES is a guide for choosing the next useful test, not a requirement to fill every possible combination. Transition: Apply the guide to this payment callback. Timing: 5 minutes.

[Sources]
- James Grenning, “TDD Guided by ZOMBIES”: https://blog.wingman-sw.com/tdd-guided-by-zombies
-->

---

<p class="kicker">ZOMBIES MATRIX</p>

# Map the payment callback from Zero to Many

| ZOMBIES | ZERO | ONE | MANY |
| --- | --- | --- | --- |
| **B · BOUNDARIES** | No matching invoice → 404 | Last unpaid invoice → ACTIVE | One still unpaid → SUSPENDED |
| **I · INTERFACES** | Callback requires an identifier | Invoice ID / reference selects one invoice | `membershipId` matches many → ASK |
| **E · EXCEPTIONS** | No identifier → 400 | Already paid → success, no change | Cancelled / ended / wrong reason → unchanged |

<div class="callout">S · Start with the smallest case that teaches something. Add only enough code to pass it.</div>

<!--
Story beat: Apply ZOMBIES to the payment callback rather than discussing the acronym in isolation. Trainer: Read the columns from Zero to Many. Then scan the rows. Highlight the Many/Boundary cell: paying one of two invoices must leave the membership SUSPENDED. That is the first case that distinguishes “any payment” from “all payments.” Highlight the Many/Interface cell marked ASK: a membership ID can identify several invoices, so the test must not invent which one was paid. The authentication mechanism and invoice-relevance rules also remain outside the matrix until an owner answers them. Transition: Participants now use the matrix to choose and implement the smallest missing tests. Timing: 7 minutes.

[Sources]
- James Grenning, “TDD Guided by ZOMBIES”: https://blog.wingman-sw.com/tdd-guided-by-zombies
- Workshop branch 03: https://github.com/codeartify/ai-assisted-clean-code-workshop-java/tree/ai-day1-03-behaviour-specification
-->

---

<p class="kicker">CONCRETE PRACTICE 3</p>

# Use ZOMBIES to choose the next test

**Branch:** 02 → 03

1. Invoke non-brittle-tests
2. Fill the Zero · One · Many columns
3. Check Boundaries · Interfaces · Exceptions
4. Write the smallest unproven scenario

<div class="callout">Debrief: Which MANY case exposed a rule that ONE could not? Which cell must remain ASK?</div>

<!--
Participant work: 22 minutes. Starting point: ai-day1-02-verified-contracts. Trainer instructions: (1) Participants invoke non-brittle-tests. (2) They fill the ZOMBIES matrix using only repository evidence and the agreed feature rule. (3) They mark unresolved policy or security cells ASK instead of inventing an answer. (4) They select the smallest missing scenario that distinguishes correct from incorrect behavior: two invoices, pay only one. (5) They add and run the focused callback tests. Sample solution: switch to ai-day1-03-behaviour-specification. Show paymentOfOneInvoiceKeepsMembershipSuspendedUntilEveryInvoiceIsPaid, callbackWithoutAnIdentifierIsRejected, and callbackForUnknownInvoiceIsReportedAsNotFound. Debrief: Ask which One test already passed, which Many case exposed the missing rule, and which matrix cells remain ASK. Transition: Gate 4 separates fast rule feedback from slower HTTP/JPA proof. Timing: 50-minute concept/practice loop.

[Sources]
- Workshop branch 03: https://github.com/codeartify/ai-assisted-clean-code-workshop-java/tree/ai-day1-03-behaviour-specification
-->

---

<p class="kicker">GATE 3 RESOLVED</p>

# The Many case now protects the rule

## Branch 03 proves

First of two paid → SUSPENDED<br/>
Last remaining invoice paid → ACTIVE<br/>
No identifier → 400 · Unknown invoice → 404

<div class="callout">NEXT → Decide which rules need fast tests and which need HTTP + database proof.</div>

<!--
Story beat: Show the concrete result, not an abstract “behavioral contract.” Trainer: Branch 03 adds one multi-invoice flow test plus tests for missing and unknown identifiers. The multi-invoice test proves that the first payment leaves the membership SUSPENDED and the final payment makes it ACTIVE. Existing tests still cover One invoice, retries, cancellation, an ended membership period, and another suspension reason. Invoice relevance, membership-only selection, and callback authentication remain explicit owner questions. Transition: We now know what must happen. Gate 4 decides which rules should be tested quickly in memory and which require the HTTP/JPA boundary. Timing: 2 minutes.

[Sources]
- Workshop branch 03: https://github.com/codeartify/ai-assisted-clean-code-workshop-java/tree/ai-day1-03-behaviour-specification
-->

---

<!-- _class: section -->

<p class="number">04</p>
<p class="kicker">GATE 4 · FEEDBACK</p>

# How much realism does each risk need?

Keep the rule loop fast; pay for frameworks and external processes where their failure matters.

<!--
Story beat: Turn behavior rows into an efficient feedback system. Trainer: Say that this is not a unit-vs-integration ideology discussion. Transition: Define fast, slow, and very slow pragmatically. Timing: 1 minute.
-->

---

<p class="kicker">FEEDBACK COST</p>

# Fast · slow · very slow

- **FAST:** Pure in-process rule · no Spring, DB, network, or uncontrolled clock
- **SLOW:** Framework/persistence boundary · JSON, JPA, transaction, configuration
- **VERY SLOW:** Real external process, deployed system, network, or security configuration

<div class="callout">The useful question is: which failure can this boundary expose?</div>

<!--
Story beat: Introduce Jimmy Bogard’s pragmatic labels. Trainer: Explain that the exact naming matters less than knowing cost and diagnostic value. Use the callback rule at all three scopes. Transition: Cost also depends on how we represent collaborators. Timing: 5 minutes.
-->

---

<p class="kicker">COLLABORATORS</p>

# Prefer the simplest truthful collaborator

1. Real
2. Fake
3. Stub
4. Mock / spy

<div class="callout">Move right only when the cheaper option cannot express the risk or is impractical.</div>

<!--
Story beat: Prevent default mocking. Trainer: Define each briefly: real implementation; working lightweight fake; predetermined stub; interaction-observing mock/spy. Explain that a mock is appropriate when the interaction itself is owned, but repository save counts are not owned here. Transition: Build the actual portfolio for this feature. Timing: 5 minutes.
-->

---

<p class="kicker">TEST PORTFOLIO</p>

# Two feedback loops earn their place

<div class="grid"><div class="card"><h3>Fast policy</h3>MembershipReactivationPolicyTest: cancelled, ended, wrong reason, unpaid invoices.</div><div class="card"><h3>Slow slice</h3>PaymentReceivedControllerTest: HTTP, JPA, transaction, persisted outcomes.</div><div class="card"><h3>Very slow</h3>Real provider/security delivery is named—but not fabricated as an always-on local test.</div><div class="card"><h3>Diagnostic intent</h3>Rule failure and wiring failure produce different, useful signals.</div></div>

<!--
Story beat: Show complementary scopes. Trainer: Run the policy test first and the HTTP/JPA test second. Explain what the fast test cannot prove and why the slower one earns its cost. Transition: Apply a brittleness check before practice. Timing: 6 minutes.
-->

---

<p class="kicker">FOUR-CHANGE CHECK</p>

# Would this test fail for the wrong reason?

<div class="grid"><div class="card"><h3>Collaborator</h3>Swap a real implementation for another valid one.</div><div class="card"><h3>Refactoring</h3>Move or rename internal code.</div><div class="card"><h3>Sibling feature</h3>Add a neighboring behavior.</div><div class="card"><h3>Unrelated wiring</h3>Change configuration outside the owned contract.</div></div>

<div class="callout">A behavior test should remain stable through behavior-preserving changes.</div>

<!--
Story beat: Give participants a concrete review tool from non-brittle-tests. Trainer: Use one mock-based test and ask which of the four harmless changes breaks it. Transition: Participants redesign their test portfolio and run both feedback loops. Timing: 4 minutes.
-->

---

<p class="kicker">CONCRETE PRACTICE 4</p>

# Choose the smallest sufficient proof for each risk

**Branch:** 03 → 04

1. Map behavior rows to failure risks
2. Choose fast / slow / very slow boundaries
3. Prefer real → fake → stub → mock
4. Control time and IDs; run focused then broad

<div class="callout">Debrief: Which realism did each slower test buy? Which test would diagnose a rule failure fastest?</div>

<!--
Participant work: 20 minutes. Trainer sample: Switch to ai-day1-04-test-design. Show MembershipReactivationPolicyTest and the retained public-surface tests; remove unowned interaction assertions. Run the focused policy command, then the HTTP/JPA command. Transition: With behavior protected by useful feedback, Gate 5 can refactor safely. Timing: 50-minute loop.
-->

---

<p class="kicker">GATE 4 RESOLVED</p>

# We have a safety net fast enough to guide the agent

## Now we have

Risk-to-boundary test portfolio + collaborator decisions + focused/broader commands

<div class="callout">NEXT → Diagnose design pressure and make one behavior-preserving improvement.</div>

<!--
Story beat: Establish permission to refactor. Trainer: Ask participants which test they expect to run after every small edit and which proves final wiring. Transition: Now smell vocabulary becomes useful because behavior is known and protected. Timing: 2 minutes.
-->

---

<!-- _class: section -->

<p class="number">05</p>
<p class="kicker">GATE 5 · REFACTOR</p>

# What makes the next change harder than it should be?

Use smells as design questions, then guide the smallest behavior-preserving sequence.

<!--
Story beat: This is the first gate explicitly centered on Clean Code/refactoring. Trainer: Explain why it comes after evidence, behavior, and feedback: otherwise teams can elegantly preserve the wrong rule. Transition: Define smell and refactoring separately. Timing: 1 minute.
-->

---

<p class="kicker">VOCABULARY</p>

# A smell diagnoses pressure. A refactoring changes structure safely.

<div class="grid"><div class="card"><h3>Code smell</h3>An observable signal that prompts a design question. It is not an automatic command.</div><div class="card"><h3>Refactoring</h3>A small behavior-preserving transformation, verified after meaningful steps.</div></div>

<div class="callout">Choose one primary pressure, one smallest useful move, and one stop condition.</div>

<!--
Story beat: Correct the earlier catalogue-like framing. Trainer: Explain that several smells may coexist, but a refactoring needs a primary diagnosis. Tests preserve behavior; smell diagnosis chooses direction. Transition: Teach the first four smells with code already visible in MembershipController. Timing: 4 minutes.
-->

---

<p class="kicker">SMELL LENS · 1/2</p>

# Signals inside the current controller

<div class="grid"><div class="card"><h3>Primitive obsession</h3>Business meaning lives in strings, booleans, numbers, IDs, or unrelated dates.</div><div class="card"><h3>Deep nesting</h3>Understanding the outcome requires tracking several conditional levels.</div><div class="card"><h3>Complicated boolean</h3>A condition combines facts without revealing the business sentence.</div><div class="card"><h3>Bad names</h3>Names expose mechanics or generic roles instead of purpose and domain meaning.</div></div>

<div class="callout">Ask: what concept, decision, or responsibility is trying to become explicit?</div>

<!--
Story beat: Give concise definitions plus examples. Trainer: Point to raw status/reason strings and date ranges for primitive obsession; the cancelled/suspended/date reactivation path for nesting; the three-identifier validation and combined reactivation condition for complicated booleans; generic handle/message versus recordMembershipPayment/allRelevantInvoicesArePaid for names. Transition: Add the responsibility and over-design smells. Timing: 7 minutes.
-->

---

<p class="kicker">SMELL LENS · 2/2</p>

# Signals about ownership and speculative design

<div class="grid"><div class="card"><h3>Feature envy</h3>Code interrogates another concept to make a decision that belongs nearer that concept/use case.</div><div class="card"><h3>Duplication</h3>The same business knowledge or reason to change exists in more than one place.</div><div class="card"><h3>Speculative generality</h3>Ports, hooks, or frameworks exist for imagined variation without pressure.</div><div class="card"><h3>Deep inheritance</h3>Effective behavior is scattered across superclass levels and overrides.</div></div>

<div class="callout">Similar syntax is not automatically duplication; an interface is not automatically a justified port.</div>

<!--
Story beat: Complete the smell vocabulary. Trainer: Use controller interrogation of invoices/membership as feature envy or mixed responsibility; repeated lifecycle/expiry knowledge across pause/resume/extend as duplication candidates; an invented callback-auth port or generic handler framework as speculation; BaseHandler → PaymentHandler → MembershipPaymentHandler as deep inheritance. Explain that cross-aggregate policy may belong in an application policy, not blindly on one entity. Transition: Correct Parallel Change before mapping smells to refactorings. Timing: 8 minutes.
-->

---

<p class="kicker">VOCABULARY CORRECTION</p>

# Parallel Change is a migration technique—not a smell

## Introduce the new path beside the old one → migrate callers → remove the old path.

<div class="callout">It is similar in spirit to Strangler Fig. It does not mean “things that change together.”</div>

<!--
Story beat: Remove the misleading prior definition. Trainer: Explain when Parallel Change is useful: incompatible signatures, many callers, or a migration that cannot happen atomically. The current extraction may not require it, but participants should know the correct option. State that shotgun surgery and ‘familiar pattern’ are not part of this workshop’s smell set. Transition: Annotate the actual method with the smells that matter here. Timing: 3 minutes.
-->

---

<p class="kicker">PRIMARY DIAGNOSIS</p>

# The method mixes adaptation, orchestration, and policy

<div class="code">if (membership.isCancelled()) { ... }
else if (membership.isSuspendedForNonPayment()
         && allBillingReferencesArePaid) {
    if (!paidDate.isAfter(membership.getEndDate())) {
        membership.reactivateAfterPayment();
        repository.save(membership);
    }
}</div>

## Pressure cluster

Nested flow · complicated predicate · feature envy · weak orchestration boundary. Method length is a symptom, not the diagnosis.

<!--
Story beat: Apply the vocabulary to the real hotspot. Trainer: Trace the cognitive work required to understand one outcome and distinguish secondary smells from the primary mixed-responsibility problem. Ask: Which business sentence is buried here? Transition: Map each smell to a concrete refactoring move—but choose only those needed. Timing: 5 minutes.
-->

---

<p class="kicker">REFACTORING MOVES</p>

# Use the move that answers the design question

| Pressure | Possible move | Guardrail |
| --- | --- | --- |
| Bad name / boolean | Rename · extract named predicate | Use business language |
| Nested control | Guard clauses · extract decision | Preserve outcomes |
| Feature envy | Move decision · extract use case/policy | Respect aggregate scope |
| Primitive / duplication | Value type · consolidate knowledge | DRY only proven knowledge |
| Speculation / hierarchy | Inline/delete · prefer composition | YAGNI and stop |

<!--
Story beat: Teach refactorings rather than only smell names. Trainer: Explain rename, extract method/predicate, guard clauses, move method/decision, introduce value object, consolidate duplication, inline speculative abstraction, and replace inheritance with composition. Emphasize that the exercise selects a subset. Transition: Show the sample’s baby-step sequence and explicit rejection list. Timing: 7 minutes.
-->

---

<p class="kicker">BABY STEPS</p>

# Understand → name → extract → verify → stop

1. Preserve behavior
2. Name the rule
3. Extract use case
4. Run focused tests
5. Review responsibility
6. Stop before speculation

<div class="callout">Sample destination: RecordMembershipPayment + MembershipReactivationPolicy—not a universal billing framework.</div>

<!--
Story beat: Make the transformation a sequence rather than a rewrite. Trainer: Replay the refactoring log: move orchestration without changing endpoint, name validation/lookup/reactivation, keep transaction on the application operation, run tests, and stop. Explain the rejected PaymentCallbackAuthPort, base command hierarchy, and port-per-repository designs. Transition: Participants write their own Full Format plan before authorizing edits. Timing: 5 minutes.
-->

---

<p class="kicker">CONCRETE PRACTICE 5</p>

# Guide one smell-driven refactoring with a stop condition

**Branch:** 04 → 05

1. Invoke clean-code-refactoring · Full Format
2. State behavior and primary pressure
3. Choose the smallest sequence
4. Run focused tests after meaningful moves

<div class="callout">Debrief: Which name reduces inference? Which duplication is real knowledge? Which abstraction did you reject?</div>

<!--
Participant work: 18–20 minutes after the expanded concept segment. Trainer sample: Switch to ai-day1-05-safe-refactoring and replay the small commits from workshop/refactoring-log.md. Show the controller delegating to RecordMembershipPayment while the pure policy remains. Stop before package architecture work—that belongs to Gate 6. Transition: Clear responsibilities reveal the natural boundary. Timing: 50-minute loop.
-->

---

<p class="kicker">GATE 5 RESOLVED</p>

# The code now says what the business operation means

## Now we have

Behavior-preserving commits + honest names + cohesive use case/policy + rejected speculation + stop condition

<div class="callout">NEXT → Place the use case so one future change has a visible, enforceable blast radius.</div>

<!--
Story beat: Connect Clean Code to AI context. Trainer: Point out that RecordMembershipPayment and allRelevantInvoicesArePaid give the next agent natural-language evidence that a repository expression could not. Transition: Architecture now has real responsibilities to organize. Timing: 2 minutes.
-->

---

<!-- _class: section -->

<p class="number">06</p>
<p class="kicker">GATE 6 · ARCHITECTURE</p>

# How far should one change be able to reach?

Choose boundaries that reduce context and verification cost—without generating ceremonial layers.

<!--
Story beat: Move from local design to scope. Trainer: Architecture is not ‘move files until the diagram looks right.’ It defines dependencies and review surfaces for future change. Transition: Define blast radius before naming patterns. Timing: 1 minute.
-->

---

<p class="kicker">BLAST RADIUS</p>

# Architecture should make the review question bounded

## For one payment-policy change: which code, tests, dependencies, and contracts could be affected?

<div class="callout">A good boundary lets a human or agent answer that question with a small, intentional context set.</div>

<!--
Story beat: Tie Jimmy Bogard’s framing to agent context windows. Trainer: Explain that authoring becomes cheap while verification and future change remain expensive. Ask participants to name everything the original controller method forced them to inspect. Transition: Compare what VSA, Hexagonal, and Clean Architecture actually protect. Timing: 5 minutes.
-->

---

<p class="kicker">THREE LENSES</p>

# Different patterns solve different boundary problems

| Pattern | Protects / organizes | Use when |
| --- | --- | --- |
| Vertical Slice | One request/use case and its local verification surface | The use case has coherent rules, transaction, and outcomes |
| Hexagonal | Core behavior from volatile or multiple external adapters | Technology varies, adapters multiply, or test isolation earns a port |
| Clean Architecture | Long-lived policy through inward dependency direction | Policy complexity/longevity and independent delivery justify separation |

<!--
Story beat: Correct the previous escalation ladder. Trainer: Explain that these are compatible lenses, not mutually exclusive maturity stages. A vertical slice can contain a hexagonal port and framework-independent policy. The question is which pressure exists. Transition: Start with VSA because the current change is one coherent use case. Timing: 8 minutes.
-->

---

<p class="kicker">VERTICAL SLICE</p>

# Keep request-to-result behavior local

- **HTTP adapter:** payment-received request / response
- **Application operation:** RecordMembershipPayment · transaction
- **Business decision:** MembershipReactivationPolicy
- **Explicit shared concepts:** Membership · billing references · repositories

<div class="callout">A slice may use shared domain concepts; it must not depend directly on another slice.</div>

<!--
Story beat: Show the selected structure. Trainer: Trace the request through the slice and explain what remains shared. Clarify that a slice is not a bounded context and that a bounded context contains multiple use cases. VSA does not remove DRY, YAGNI, or refactoring. Transition: Ask whether a port is justified at any current boundary. Timing: 6 minutes.
-->

---

<p class="kicker">HEXAGONAL GATE</p>

# A port is earned by boundary pressure

<div class="grid"><div class="card"><h3>Justified</h3>Multiple providers, volatile technology, or a core behavior that needs an explicit controllable adapter.</div><div class="card"><h3>Speculative</h3>Invent PaymentCallbackAuthPort while the authentication contract and owner are still unknown.</div></div>

<div class="callout">Unknown knowledge is a stop condition—not automatic permission to create an interface.</div>

<!--
Story beat: Connect architecture to speculative generality. Trainer: Explain Ports and Adapters with a concrete second-provider or independently testable callback-verifier scenario. Then show why the current missing auth requirement cannot define a truthful port. Transition: Apply the same pressure test to Clean Architecture. Timing: 6 minutes.
-->

---

<p class="kicker">CLEAN ARCHITECTURE GATE</p>

# Dependency direction protects policy—not folder ceremony

<div class="grid"><div class="card"><h3>Pressure present</h3>Rich, long-lived reactivation/delinquency policy used by several delivery mechanisms.</div><div class="card"><h3>Pressure absent</h3>Simple operation where extra use-case/entity/gateway layers add navigation without protection.</div></div>

<div class="callout">Keep policy framework-independent where it pays; do not force a template onto every slice.</div>

<!--
Story beat: Define Clean Architecture precisely enough to guide the agent. Trainer: Show the pure MembershipReactivationPolicy as useful inward policy isolation, while the HTTP/JPA adapter depends around it. Explain why this does not require a complete canonical layer set. Transition: Summarize the actual decision for this use case. Timing: 6 minutes.
-->

---

<p class="kicker">DECISION FOR THIS CHANGE</p>

# One slice, explicit sharing, no imagined boundaries

<div class="grid"><div class="card"><h3>Choose</h3>payment.record vertical slice with one transaction and focused tests.</div><div class="card"><h3>Keep shared</h3>Existing membership/billing entities, DTOs, and repositories at honest scope.</div><div class="card"><h3>Enforce</h3>VerticalSliceBoundaryTest blocks slice-to-slice dependencies.</div><div class="card"><h3>Reject for now</h3>Invented auth port, provider abstraction, event, CQRS split, or layer template.</div></div>

<!--
Story beat: Make the architecture resolution concrete. Trainer: Open workshop/architecture-decision.md. Trace a future change in invoice relevance and show the files/tests a reviewer needs. Explain shared code as a scope decision and revisit DRY/YAGNI. Transition: Participants make and defend their own architecture decision before moving files. Timing: 5 minutes.
-->

---

<p class="kicker">CONCRETE PRACTICE 6</p>

# Draw the blast radius before moving code

**Branch:** 05 → 06

1. Invoke modern-application-architecture
2. List protected rules and change axes
3. Select and reject patterns with evidence
4. Move the slice and add a boundary test

<div class="callout">Debrief: What must a reviewer inspect? Which port is earned? What does the architecture test enforce?</div>

<!--
Participant work: 20 minutes after the pattern explanation. Trainer sample: Switch to ai-day1-06-vsa-blast-radius. Show fitness.payment.record, shared concepts, and VerticalSliceBoundaryTest. Explain why no slice calls another and why no extra auth/provider port was created. Transition: The structure is now stable enough to encode repeatable workflow for the next agent. Timing: 50-minute loop.
-->

---

<p class="kicker">GATE 6 RESOLVED</p>

# The intended scope of change is visible and enforceable

## Now we have

Architecture decision + bounded slice + explicit shared code + selected/rejected patterns + dependency test

<div class="callout">NEXT → Preserve the evidence order and decision gates for future AI-assisted changes.</div>

<!--
Story beat: Close the architecture loop. Trainer: Ask a participant to answer ‘what could this change break?’ in one bounded sentence. Transition: Gate 7 decides where facts, workflows, behavior, and hard rules belong. Timing: 2 minutes.
-->

---

<!-- _class: section -->

<p class="number">07</p>
<p class="kicker">GATE 7 · GUARDRAILS</p>

# How should an agent implement the next feature?

Turn Gates 1–6 into a general workflow that adapts to the change.

<!--
Gate 7 outcome: create implement-feature, a general coordinating skill based on Gates 1–6. The course introduces the checks in a teaching order; real feature work can reorder them when evidence justifies it. Gate 1 reviews assumptions, plans, increments, and the final diff. The unguided baseline is a teaching comparison, not a required development step. Keep the concept block to 10 minutes; practice is 40 minutes.
-->

---

<p class="kicker">GUIDANCE FOR THE NEXT CHANGE</p>

# Your codebase is the prompt

| Home | Example |
| --- | --- |
| Code + names | RecordMembershipPayment shows where payment work belongs. |
| Behavior tests | Two invoices: one still unpaid means stay suspended. |
| Architecture tests | Fail on an import from another payment slice. |
| AGENTS.md | Where to start and which commands to run. |
| SKILL.md | implement-feature coordinates changes using the relevant local facts. |
| Humans | Own missing business, product, and security decisions. |

<!--
Use the payment rows as examples of local facts. Business meaning stays in code and names; behavior tests exercise known outcomes; architecture tests enforce specific dependency rules. AGENTS.md supplies grounded paths, commands, and examples. implement-feature discovers the affected feature and sequences decisions; it does not encode a fixed payment file list. Humans own unknown product and security rules. Ask which information becomes stale if copied into the skill.
-->

---

<p class="kicker">PUT KNOWLEDGE WHERE IT BELONGS</p>

# Where should each lesson live?

| Statement | Save it in | Why |
| --- | --- | --- |
| Cancelled never reactivates | Code + behavior test | Protect the result when code moves |
| No cross-slice imports | Architecture test | Catch forbidden dependencies |
| Review tests before refactoring | implement-feature / SKILL.md | Repeat useful steps |
| Callback authentication unknown | Owner decision + note | The agent must ask |

<!--
Ask participants where each lesson belongs. The callback examples remain facts about this repository; the repeatable decision procedure belongs in implement-feature. The current import check has known blind spots and cannot prove complete isolation through shared types. A missing authentication contract remains an owner question, not a reason to invent a port or a rule.
-->

---

<p class="kicker">A USEFUL SKILL</p>

# implement-feature: six gates, one workflow

| Check | Next decision |
| --- | --- |
| START | Understand request |
| GATE 2 | Discover + verify |
| GATE 3 | Choose behavior |
| GATE 4 | Choose feedback |
| GATE 6 | Decide placement |
| GATE 5 | Implement in steps |
| GATE 1 | Review + finish |

<div class="callout">Review throughout. Adapt the order to the change.</div>

<!--
Walk the default phases: understand observable intent, acceptance criteria, preserved behavior, and scope; discover contracts, installed APIs, dependencies, and tests with evidence (Gate 2); select a revealing ZOMBIES scenario (Gate 3); separately choose feedback boundaries (Gate 4); decide placement and shared dependencies (Gate 6); implement in small verified increments and refactor actual design pressure when justified (Gate 5); review and finish (Gate 1). Gate 1 also operates throughout. These are seven displayed decisions inside the six-phase coordinating workflow: scenario choice and test-boundary choice are shown separately. Use CLOSE/FIX/TEST/ESCALATE for evidence findings and ACCEPT/HOLD/REJECT for review. A plan-only request ends with a reviewed plan, not an implementation claim.
-->

---

<p class="kicker">CONDITIONAL SPECIALISTS</p>

# Choose the next useful step

| Decision | Guidance |
| --- | --- |
| Test design | Use non-brittle-tests for cases, boundaries, and assertions. |
| Refactoring | Use clean-code-refactoring for an evidenced design problem. |
| Placement | Use modern-application-architecture for boundary decisions. |
| Change the order | Check architecture early; protect behavior before preparatory refactoring. |
| Already passes | Keep useful coverage. Make no unnecessary production change. |
| Missing rule | Pause dependent work; ask its owner. Review decisions throughout. |

<!--
Every gate is considered; detailed specialist work is conditional. Use non-brittle-tests for behavior coverage and feedback design, clean-code-refactoring when a concrete smell or responsibility problem warrants it, and modern-application-architecture when boundaries or dependencies need a decision. Move architecture earlier when shared rules or cross-boundary changes demand it. Preparatory refactoring requires adequate behavior protection and a separately reviewable structural change. If a new test already passes, report the coverage improvement without inventing a production edit. Unknown required outcomes remain owner decisions. Honor existing authorization and plan-only scope; do not ask permission again for routine steps already authorized. Stop when the agreed outcome is supported by required verification; HOLD when required decisions or evidence are missing.
-->

---

<p class="kicker">CONCRETE PRACTICE 7</p>

# Build implement-feature; check its transfer

**Branch:** 06 → 07

1. Save request A's plan without the coordinator
2. Draft implement-feature from Gates 1–6
3. Repeat A with the skill; compare decisions
4. Use the unchanged skill for B: plan-title filter

<div class="callout">Did decisions improve? Did the workflow transfer beyond payments?</div>

<!--
Practice: 40 minutes on a participant branch from ai-day1-06-vsa-blast-radius. 5 min: fresh-session baseline plan for request A without the coordinator; do not feed that session the exercise or proposed workflow. 12 min: draft and review .agents/skills/implement-feature/SKILL.md, referencing the three unchanged specialist skills. 8 min: fresh-session guided A with identical request, application code, repository guidance, and model settings. 5 min: fresh-session request B using the unchanged skill. 10 min: compare decisions, debrief, improve where justified, and reveal branch 07. Save A baseline/guided plans and B transfer plan in workshop/gate7-plan-comparison.md with source commit and skill version. If the skill changes, repeat the affected check. Keep both requests plan-only; any justified AGENTS.md cleanup follows the comparison.

Request A: Plan support for looking up a callback invoice by a provider transaction reference. Do not change application code. Identify the existing code and contracts you would inspect, questions that must be answered, proposed test boundaries, likely file changes, verification commands, and a stop condition. Do not invent missing provider, identifier-precedence, or authentication rules.

Request B: Plan an optional titleContains query parameter for GET /api/plans. An omitted or blank parameter keeps the existing full list. A supplied value selects plans whose title contains that value, ignoring case; no matches returns an empty list. Preserve the response shape. Do not change application code. Inspect the plan feature, identify the next revealing scenario and its test boundary, likely file changes, verification commands, unresolved decisions, and a stop condition. Explain whether architecture or refactoring work is needed. Do not impose payment rules or invent a new ordering contract.

Use workshop/gate7-exercise.md for the exact prompts. A longer plan is not evidence of a better decision.
-->

---

<p class="kicker">SAMPLE SOLUTION 7</p>

# Branch 07: one workflow, different features

## General coordinator

Discover each feature’s contracts, tests, and change scope.
Choose specialists when useful. Verify, review, and stop.

<div class="callout">NEXT → Take the workflow to a feature in your own repository.</div>

<!--
Reveal .agents/skills/implement-feature/SKILL.md on ai-day1-07-workflow-skills and the concise AGENTS.md examples for different feature areas. Show the gate map, separation of behavior and feedback, conditional specialist routing, flexible ordering, plan-only handling, and stopping criteria. Compare actual participant plans. Request A should discover callback lookup/contracts and owner questions; request B should discover PlanController/PlanService and suitable plan tests. Existing placement and no substantive refactoring may be appropriate for B. The published skill and solution contain assessment criteria, not a recorded empirical comparison or a universal quality claim.
-->

---

<p class="kicker">CONCLUSIONS · 16:40–17:00</p>

# Same feature request. A radically different prompt surface.

<div class="grid"><div class="card"><h3>09:20</h3>One plausible diff. Hidden decisions. One narrow happy-path signal.</div><div class="card"><h3>16:40</h3>Evidence, owned behavior, useful feedback, honest names, bounded dependencies, and a workflow skill.</div></div>

<div class="callout">The codebase—not a magic prompt—does most of the durable guiding.</div>

<!--
Story beat: Compare baseline and final without pretending the final code shape is uniquely correct. Trainer: Ask pairs to name the seven artifacts from memory and the risk each removed. Then reveal the progression slide if needed. Transition: Convert retrieval into one action participants will take home. Timing: 8 minutes.
-->

---

<p class="kicker">YOUR NEXT CHANGE</p>

# One code action. One test action. One workflow action.

<div class="grid"><div class="card"><h3>Code</h3>Which name, concept, or responsibility should stop forcing inference?</div><div class="card"><h3>Test</h3>Which behavior needs faster or more realistic evidence?</div><div class="card"><h3>Workflow</h3>Which repeated review finding belongs in AGENTS.md, a skill, or a test?</div><div class="card"><h3>Proof</h3>What observable result will tell you the action helped?</div></div>

<!--
Story beat: Turn conclusion into transfer. Trainer: Give individuals four minutes to write one action and proof, then exchange with a partner. Invite two commitments. Collect unresolved multi-agent or broader modernization questions for Day 2 rather than reopening the Day 1 scope. Transition: Final questions. Timing: 8 minutes.
-->

---

<p class="kicker">QUESTIONS · DISCUSSION</p>

# What should your codebase teach the next agent?

codeartify.com

<!--
Story beat: End on the workshop thesis. Trainer: Use remaining time for questions, but relate answers back to evidence, behavior, feedback, meaning, boundary, or workflow. If a question asks for a universal pattern, ask which concrete pressure it solves. Timing: Remaining time.
-->
