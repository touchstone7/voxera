# Security and Business Rules

> Manually maintained decisions. This document records agreed security policies,
> business rules, and architectural decisions. Verify implementation before
> treating a policy as confirmed runtime behavior.

## Authentication

- Authentication uses the login API and JWT-based access tokens.
- Passwords are handled using BCrypt password hashing.
- User accounts have ACTIVE and INACTIVE statuses.
- The exact token-validation, token-expiration, and inactive-user enforcement
  behavior must remain consistent with the current security implementation.
- Do not assume that a policy is implemented merely because it is documented here.

## Authorization policy

The application uses role-based access control (RBAC) together with
resource-level authorization where required.

### EMPLOYEE

- May access tickets they created.
- May create tickets.
- May cancel their own tickets, subject to lifecycle rules.
- Must not access tickets created by other employees unless an explicitly
  authorized use case is introduced.

### AGENT

- Has flat access to all tickets.
- Can access tickets regardless of whether they are assigned to the agent,
  assigned to another agent, or currently unassigned.
- Assignment does not determine ticket visibility.
- Can perform ticket-workflow operations subject to the applicable lifecycle
  and authorization rules.

### ADMIN

- Has broad administrative access.
- Administrative authority must be enforced by the backend, not merely exposed
  through frontend navigation or UI controls.
- Admin capabilities should represent meaningful administrative operations,
  rather than making ADMIN only a variation of AGENT.

### Authorization design decisions

- Agent authorization is flat; there is no agent-to-agent visibility hierarchy.
- Team-based authorization and team-level access restrictions are deferred.
- Ticket assignment is a workflow responsibility, not an agent visibility boundary.
- EMPLOYEE resource access remains restricted to the employee's own tickets.
- Frontend role-based rendering is not a substitute for backend authorization.

## Ticket assignment

- Ticket assignment is manual.
- No automatic assignment scheduler or automatic assignment mechanism is
  currently part of the agreed design.
- Assignment does not restrict an agent's ability to view other tickets.
- Team-based assignment and authorization rules remain deferred unless
  explicitly introduced through a later design decision.

## Ticket lifecycle

The ticket status model includes:

- OPEN
- IN_PROGRESS
- PENDING
- RESOLVED
- CLOSED
- CANCELLED

Known lifecycle behavior:

- Tickets can be started, marked pending, resolved, closed, or cancelled
  through the applicable workflow operations.
- IN_PROGRESS may transition to PENDING or RESOLVED.
- The exact allowed transition matrix, including transitions from PENDING
  and restrictions on terminal states, must match the current entity and
  service implementation.
- Lifecycle validation must be enforced by the backend.
- Frontend action visibility should reflect the backend-supported lifecycle
  rules; it must not define those rules independently.

## Ticket categories and workflow settings

- Ticket categories are dynamically managed rather than restricted to a
  compile-time enum.
- Administrative changes to categories and workflow settings must be
  enforced and validated by the backend.
- Frontend category options and workflow behavior should be derived from the
  backend-managed configuration rather than duplicated as independent
  hardcoded policy.

## Deferred decisions and scope

- Team-based authorization hierarchy.
- Automatic ticket assignment.
- Any additional authorization hierarchy beyond the current EMPLOYEE,
  AGENT, and ADMIN policy.

## Open questions requiring implementation verification

- Exact inactive-user authentication and token-revocation behavior.
- Exact JWT validation and expiration behavior.
- Complete ticket lifecycle transition matrix.
- Any additional lifecycle restrictions on cancellation, resolution, and closure.