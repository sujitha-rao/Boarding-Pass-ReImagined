# Boarding Pass — Reimagined
### Three-Card Pass · Delta Air Lines · HCI Individual Project

> **Georgia Tech OMSCS — CS 6750 Human-Computer Interaction**
> Sujitha Suresh Rao · srao434@gatech.edu

---

## 🚀 Live Prototype

| Resource | Link |
|---|---|
| **Live interactive prototype** | [https://sujitha-rao.github.io/Boarding-Pass-ReImagined/](https://sujitha-rao.github.io/Boarding-Pass-ReImagined/) |
| **Video prototype (YouTube)** | [https://www.youtube.com/watch?v=l8Y02fyiZ0M](https://www.youtube.com/watch?v=l8Y02fyiZ0M) |

---

## 📋 Project Overview

Most airline mobile boarding passes present a wall of information with equal visual weight at all times. This project redesigns the boarding pass as a **context-aware, stage-adaptive interface** that surfaces the right information at the right moment — reducing cognitive load and stress at each stage of the airport journey.

The final design, **Three-Card Pass**, structures the boarding pass as three labeled cards:

| Stage | Hero element | Key info shown |
|---|---|---|
| 🔒 Security | QR code (full-screen) | Name, TSA Pre✓, gate hint |
| ✈ Gate | Gate number (large red) | Zone, boarding time, countdown bar, jetbridge QR |
| 🏛 In Flight | Seat number (large red) | Cabin, flight info, meal/assistance actions |

---

## ✨ Features

- **Three labeled tabs** — Security, Gate, In Flight — with time-based auto-selection
- **Live countdown bar** with opt-in "Boards in X min" label and hide/show toggle
- **Non-modal gate-change alert** — amber banner with updated gate number, no interruption
- **Airport simulation** — 8-phase walkthrough from arrival through departure with real-time clock and timeline
- **Responsive layout** — desktop 3-column grid; mobile single-column with fixed bottom control bar
- **Delta SVG intro animation** — plane flies in on page load
- **Fly-across animation** — triggered when boarding is called during simulation

---

## 📁 Repository Structure

```
Boarding-Pass-ReImagined/
└── index.html        # Complete single-file prototype (HTML + CSS + JS)
└── README.md         # This file
```

---

## 📊 Surveys

### Needfinding Survey (Check-in 1)
Collected user needs across three airport stages — security, gate, boarding.

| | |
|---|---|
| **Platform** | Georgia Tech PeerSurvey |
| **Link** | [http://peersurvey.cc.gatech.edu/gt/9ea79d259d9f4f0db4354ece6df52cc1](http://peersurvey.cc.gatech.edu/gt/9ea79d259d9f4f0db4354ece6df52cc1) |
| **Responses** | 38 |

**Key findings:**
- 66% look for the QR code first at security
- 87% need only their seat number after boarding
- Only 5% found gate-stage information immediately visible on current apps
- 87% selected a stage-aware simplified view as their top desired improvement

---

### Iteration-One Prototype Evaluation Survey (Check-in 3)
Compared three low-fidelity prototypes: Stage Spotlight, Three-Card Pass, Ambient Pass.

| | |
|---|---|
| **Platform** | Microsoft Forms |
| **Link** | [Airline Boarding Pass Reimagined — Prototype Survey](https://forms.office.com) |
| **Responses** | 16 |

**Key findings:**
- Three-Card Pass ranked first by 50% of participants
- Significant advantage over Stage Spotlight (p = .038) and Ambient Pass (p = .019)
- Zero qualitative complaints for Three-Card Pass
- Perfect QR locatability score (5.00) on the Security card

---

### Final Evaluation Survey (Check-in 4)
Evaluated the medium-fidelity interactive prototype against 7 specific design features.

| | |
|---|---|
| **Platform** | Microsoft Forms |
| **Responses** | 21 |
| **Collection period** | March 26–27, 2026 |

**Survey results summary:**

| Q | Feature | Mean | SD | % ≥ Agree |
|---|---|---|---|---|
| Q2 | Security card — QR dominance | 4.71 | 0.46 | 100% |
| Q3 | Gate card — red/navy hierarchy | 4.48 | 0.60 | 95% |
| Q4 | Gate card — countdown bar | 4.52 | 0.60 | 95% |
| Q5 | In-flight card — seat number | 4.48 | 0.60 | 95% |
| Q6 | Tab navigation | 4.62 | 0.59 | 95% |
| Q7 | Gate-change alert | 4.52 | 0.51 | 100% |
| Q8 | Auto-selection trust ★ | 4.57 | 0.51 | 100% |
| | **Grand mean** | **4.56** | **0.55** | **98%** |

★ Primary hypothesis — confirmed: all 21 participants rated auto-selection at Agree or Strongly Agree.

All seven Wilcoxon signed-rank tests significant at **p < .001** (W = 0 for all items).

---

## 🎬 Video Prototype

**YouTube:** [https://www.youtube.com/watch?v=l8Y02fyiZ0M](https://www.youtube.com/watch?v=l8Y02fyiZ0M)

The video (~5 minutes) presents a complete verbal walkthrough of the Three-Card Pass prototype covering:
- All three stage cards (Security, Gate, In Flight)
- Live countdown bar and opt-in label
- Gate-change alert simulation (amber banner + gate number update)
- Design rationale for each key decision

---

## 🎓 Course Context

This project was completed across four check-ins for **CS 6750 Human-Computer Interaction** at Georgia Tech OMSCS:

| Check-in | Phase | Key deliverable |
|---|---|---|
| 1 | Needfinding | User survey (n=38) + heuristic evaluation of Delta app |
| 2 | Brainstorming + low-fi prototyping | 3 low-fidelity alternatives + evaluation plan |
| 3 | Iteration-one evaluation | Comparative evaluation (n=16), Three-Card Pass selected |
| 4 | Final prototype + evaluation | Medium-fidelity interactive prototype + final survey (n=21) |

---

## 🔑 Key Design Decisions

| Decision | Principle | Rationale |
|---|---|---|
| Labeled tabs | Norman's Gulf of Execution | No learning required; available actions are named |
| Red gate / navy zone | Preattentive processing | Two-color system enables single-glance identification |
| Three-level hierarchy per card | Gestalt figure-ground | One hero field per card vs. 15+ equal-weight elements in current Delta app |
| Countdown bar + opt-in label | Error prevention | Urgency without forced anxiety; hidden on tap |
| Non-modal gate-change alert | Nielsen H1 + H3 | Informs without interrupting; all card info stays visible |
| Persistent route footer | Nielsen H6 — Recognition over Recall | Origin/destination visible on all three cards |

---

## 📬 Contact

**Sujitha Suresh Rao**
Georgia Tech OMSCS — CS 6750
srao434@gatech.edu
