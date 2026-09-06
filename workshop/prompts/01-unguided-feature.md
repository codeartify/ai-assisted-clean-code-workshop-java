# Gate 1 — Feature request

Copy only the text below into the first agent session, with normal application
context and without invoking the supplied skills:

```text
Fix the payment callback. A membership suspended for non-payment must not be
reactivated until all of its invoices have been paid. Keep the existing API
working and update the implementation and tests as needed.
```

After the agent produces its diff, use [gate1-exercise.md](../gate1-exercise.md)
for the participant review. Do not supply the review checklist or worked
solutions to the agent before generating this baseline.
