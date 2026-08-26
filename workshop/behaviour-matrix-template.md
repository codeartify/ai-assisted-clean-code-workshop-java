# Behavior matrix

| Given | When | Observable result | Why it matters | Smallest sufficient proof |
| --- | --- | --- | --- | --- |
| Active membership; one open invoice | Valid payment callback |  |  |  |
| Non-payment suspension; two open invoices | One invoice is paid |  |  |  |
| Non-payment suspension; last open invoice | Last invoice is paid |  |  |  |
| Previously paid invoice | Callback is retried |  |  |  |
| Cancelled membership | Invoice is paid |  |  |  |
| Unknown invoice identifier | Callback arrives |  |  |  |
| No invoice identifier | Callback arrives |  |  |  |
| Membership period ended | Last invoice is paid |  |  |  |
