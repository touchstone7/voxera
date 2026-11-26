# Voxera Project Status

> Manually maintained. Update this document after meaningful implementation
> milestones. Verify Git state and build/test results before recording a new
> checkpoint.

## Current checkpoint

- Last verified commit: `ca3c4a5`
- Branch: `main`
- Working tree: clean at the last verified checkpoint.
- Build/test status: not verified by the metadata generator; record results
  only after running the relevant commands.
- Current workstream: Notifications subsystem.
- Notifications design and implementation milestones N1-N8 are a workstream
  within the broader Voxera roadmap, not replacements for the official M1-M8
  milestones.

## Project direction

Voxera is an enterprise IT-help and ticket-management application consisting
of:

- Java/Spring Boot backend.
- React frontend.
- Python AI voice agent.

The current M1-M8 integration and completion work focuses on delivering a
real, backend-connected application with meaningful enterprise workflows.
The Python voice agent is outside the current M1-M8 integration scope.

The project prioritizes backend-enforced business rules, realistic workflows,
maintainable architecture, and meaningful testing over UI-only simulations.

## Key decisions

### Authorization

- EMPLOYEE users can access their own tickets.
- AGENT users have flat access to all tickets, regardless of assignment.
- ADMIN users have broad access and meaningful administrative authority.
- Assignment is a workflow responsibility, not an agent visibility boundary.
- Team-based authorization is deferred.

### Ticket assignment

- Assignment remains manual.
- Automatic assignment is not currently part of the agreed design.

### Administrative capabilities

- ADMIN should have meaningful backend-enforced administrative capabilities.
- Dynamic ticket categories are represented through a managed domain model,
  rather than being restricted to a fixed enum.
- Workflow settings and administrative operations must be enforced by the
  backend.

### Architecture and implementation

- Prefer sound domain modeling and clear separation of responsibilities.
- Evaluate design patterns based on actual design needs; do not introduce
  patterns merely for their names.
- Prioritize meaningful regression coverage and integration testing.
- Avoid expanding tests without purpose, but do not sacrifice important
  business-rule, security, or failure-path coverage.

### Notifications

- Notifications are being designed as a substantial application subsystem.
- The database is the source of truth for persisted notifications.
- Real-time delivery is a mechanism for delivering updates, not a replacement
  for persistence.
- The design must consider event generation, recipient resolution,
  preferences, delivery channels, read/unread state, reliability, and
  authorization.
- Architectural choices such as domain events, channel strategies,
  transactional outbox, idempotency, retries, and SSE must be evaluated
  against the actual requirements.
- Do not introduce a design pattern unless it solves a justified problem.

## Completed

### Backend and frontend integration

- Core backend ticket-management and authentication capabilities were
  implemented before the frontend integration work.
- Role-aware ticket listing was implemented.
- EMPLOYEE ticket listing is scoped to tickets created by that employee.
- AGENT and ADMIN ticket listing supports access to all tickets, subject to
  the applicable authorization policy.

### M5 ticket workflow integration

- Ticket lifecycle actions were integrated into the frontend.
- Start progress, mark pending, resolve, and close actions were implemented
  with status-aware visibility and loading/error handling.
- Agent ticket conversation/comment integration was implemented.
- Ticket details are refreshed from the backend when opening the detail view.
- Comments are retrieved and submitted through the ticket comment APIs.
- Creator and assignee UUIDs in the All Tickets interface are displayed in
  shortened form, with full UUID access and a copy action.
- Full UUID search behavior remains unchanged.

### Administrative capabilities

- Dynamic ticket categories and Workflow Settings were merged into `main`
  through PR #12.

## In progress

### Notifications subsystem

The Notifications subsystem is the current focused workstream.

Its working design and implementation milestones are:

1. N1 — Requirements, use cases, and architecture.
2. N2 — Domain model and database design.
3. N3 — Event generation and notification orchestration.
4. N4 — Recipient resolution and preferences.
5. N5 — Delivery channel abstraction and channel implementations.
6. N6 — APIs, real-time delivery, and frontend integration.
7. N7 — Reliability, security, and failure handling.
8. N8 — Integration testing and end-to-end verification.

These are working milestones for the Notifications subsystem. They do not
replace or redefine the official Voxera M1-M8 roadmap.

## Next

- Complete N1 requirements and architecture before introducing notification
  entities or implementation details.
- Review the current domain model and relationships before deciding how
  ticket events produce notifications.
- Define persistence, recipient resolution, channel delivery, and reliability
  responsibilities before implementation.
- Keep the metadata synchronized with verified implementation and Git state.
- Record focused and full test results after running them; do not infer
  successful tests from metadata generation.