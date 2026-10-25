# Security and Business Rules

> Manually maintained decisions. Verify implementation before treating these as current behavior.

## Authentication
- Record the current login, token validation, and active-user policy here.

## Authorization policy
- EMPLOYEE: access tickets they created; may cancel their own tickets.
- AGENT: access and work only on tickets assigned to them.
- ADMIN: may access all tickets.
- Team-based authorization is deferred.
- Ticket assignment remains manual; no automatic assignment.

## Ticket lifecycle
- OPEN -> IN_PROGRESS -> PENDING / RESOLVED -> CLOSED, subject to implementation rules.
- IN_PROGRESS may also transition to RESOLVED.
- Cancellation and terminal-state rules must match the current entity implementation.

## Open questions
- Record unresolved policy decisions here.
