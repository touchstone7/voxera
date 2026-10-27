# API Contracts

> Generated: 2026-10-27T11:38:46.703368+00:00
> Git commit: `0b363fd`
> Generator: `scripts/generate_metadata.py`


> Extracted from Spring mapping annotations. Verify request/response semantics and status codes against controller code and tests.


## `GlobalExceptionHandler`
- File: `backend/src/main/java/com/voxera/backend/exception/GlobalExceptionHandler.java`
- Class-level path: `(none detected)`

## `AuthController`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/AuthController.java`
- Class-level path: `/api/v1/auth`
- **POST `/api/v1/auth/login`** — `login(@Valid @RequestBody LoginRequest request)`
  - Mapping: `@PostMapping("/login")`

## `TicketCommentController`
- File: `backend/src/main/java/com/voxera/backend/ticket/controller/TicketCommentController.java`
- Class-level path: `/api/v1/tickets/{ticketId}/comments`
- **GET `/api/v1/tickets/{ticketId}/comments`** — `getComments(@PathVariable UUID ticketId)`
  - Mapping: `@GetMapping`

## `TicketController`
- File: `backend/src/main/java/com/voxera/backend/ticket/controller/TicketController.java`
- Class-level path: `/api/v1/tickets`
- **GET `/api/v1/tickets/{ticketId}`** — `getTicket(@PathVariable UUID ticketId)`
  - Mapping: `@GetMapping("/{ticketId}")`
- **GET `/api/v1/tickets`** — `getTickets(@RequestParam UUID createdBy)`
  - Mapping: `@GetMapping`
- **POST `/api/v1/tickets/{ticketId}/start`** — `startProgress(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/start")`
- **POST `/api/v1/tickets/{ticketId}/pending`** — `markPending(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/pending")`
- **POST `/api/v1/tickets/{ticketId}/resolve`** — `resolveTicket(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/resolve")`
- **POST `/api/v1/tickets/{ticketId}/close`** — `closeTicket(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/close")`
- **POST `/api/v1/tickets/{ticketId}/cancel`** — `cancelTicket(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/cancel")`
- **POST `/api/v1/tickets/{ticketId}/assign`** — `assignTicket(@PathVariable UUID ticketId, @Valid @RequestBody AssignTicketRequest request)`
  - Mapping: `@PostMapping("/{ticketId}/assign")`

## Contract verification checklist
- Confirm authentication requirements and role/resource permissions.
- Confirm request DTO fields and validation constraints.
- Confirm response DTO shape and HTTP status codes.
- Confirm error response format using the exception handler.
