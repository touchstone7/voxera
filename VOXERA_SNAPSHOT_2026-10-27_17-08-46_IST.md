# Voxera — Consolidated Project Snapshot

- Generated at: 2026-10-27 17:08:46 IST
- Git branch: main
- Git commit: 0b363fd2d87956bb5b6b32af45c062dd5c519654
- Working tree:  M VOXERA_METADATA.md
 M docs/metadata/API_CONTRACTS.md
 M docs/metadata/DOMAIN_MODEL.md
 M docs/metadata/PROJECT_STRUCTURE.md
 M docs/metadata/TEST_MAP.md
?? VOXERA_SNAPSHOT_2026-10-27_17-08-46_IST.md


---

## Document: VOXERA_METADATA.md

# Voxera Metadata

This is the entry point for understanding the current Voxera repository.

## Refresh

From the repository root, run:

```bash
python3 scripts/generate_metadata.py
```

Structural documents are regenerated from the checked-out Java source. The
security/rules and project-status documents are preserved for manual editing.

## Metadata documents

- [Project structure](docs/metadata/PROJECT_STRUCTURE.md)
- [API contracts](docs/metadata/API_CONTRACTS.md)
- [Domain model, DTOs, and enums](docs/metadata/DOMAIN_MODEL.md)
- [Security and business rules](docs/metadata/SECURITY_AND_RULES.md)
- [Test map](docs/metadata/TEST_MAP.md)
- [Project status](docs/metadata/PROJECT_STATUS.md)

## Repository checkpoint

- Generated at UTC: 2026-10-27T11:38:46.744549+00:00
- Git commit: `0b363fd`
- Git working tree status: `M VOXERA_METADATA.md
 M docs/metadata/API_CONTRACTS.md
 M docs/metadata/DOMAIN_MODEL.md
 M docs/metadata/PROJECT_STRUCTURE.md
 M docs/metadata/TEST_MAP.md`
- Main Java files found: 42
- Test Java files found: 16

## Important limitation

The generator extracts structural information using Python regular expressions;
it is not a Java compiler and cannot prove runtime behavior, authorization
correctness, or exact API semantics. Verify ambiguous entries against source.

---

## Document: docs/metadata/API_CONTRACTS.md

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

---

## Document: docs/metadata/DOMAIN_MODEL.md

# Domain Model, DTOs, and Enums

> Generated: 2026-10-27T11:38:46.715093+00:00
> Git commit: `0b363fd`
> Generator: `scripts/generate_metadata.py`



## `LoginRequest`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/dto/LoginRequest.java`
- Kind: DTO / request / response
- No fields/components extracted.

## `LoginResponse`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/dto/LoginResponse.java`
- Kind: DTO / request / response
- Fields/components:
  - `userId: UUID`
  - `employeeId: String`
  - `name: String`
  - `email: String`
  - `role: UserRole`
  - `accessToken: String`

## `Team`
- File: `backend/src/main/java/com/voxera/backend/team/entity/Team.java`
- Kind: JPA entity
- Persistence annotation: `@Table(name = "teams")`
- Persistence annotation: `@Id`
- Persistence annotation: `@Column(name = "team_id")`
- Persistence annotation: `@Column(nullable = false, unique = true, length = 150)`
- Persistence annotation: `@Column(length = 500)`
- Persistence annotation: `@Column(name = "created_at", nullable = false)`
- Persistence annotation: `@Column(name = "updated_at", nullable = false)`
- Fields/components:
  - `teamId: UUID`
    - Nearby annotations (verify scope): `@Table(name = "teams")`, `@Id`, `@Column(name = "team_id")`
  - `name: String`
    - Nearby annotations (verify scope): `@Id`, `@Column(name = "team_id")`, `@Column(nullable = false, unique = true, length = 150)`
  - `description: String`
    - Nearby annotations (verify scope): `@Column(name = "team_id")`, `@Column(nullable = false, unique = true, length = 150)`, `@Column(length = 500)`
  - `createdAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(nullable = false, unique = true, length = 150)`, `@Column(length = 500)`, `@Column(name = "created_at", nullable = false)`
  - `updatedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(length = 500)`, `@Column(name = "created_at", nullable = false)`, `@Column(name = "updated_at", nullable = false)`

## `AssignTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/AssignTicketRequest.java`
- Kind: DTO / request / response
- No fields/components extracted.

## `CreateTicketCommentRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCommentRequest.java`
- Kind: DTO / request / response
- No fields/components extracted.

## `CreateTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
- Kind: DTO / request / response
- No fields/components extracted.

## `TicketCommentResponse`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/TicketCommentResponse.java`
- Kind: DTO / request / response
- Fields/components:
  - `commentId: UUID`
  - `ticketId: UUID`
  - `authorId: UUID`
  - `authorName: String`
  - `content: String`
  - `createdAt: LocalDateTime`
  - `updatedAt: LocalDateTime`

## `TicketResponse`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/TicketResponse.java`
- Kind: DTO / request / response
- Fields/components:
  - `ticketId: UUID`
  - `ticketNumber: String`
  - `title: String`
  - `description: String`
  - `status: TicketStatus`
  - `priority: TicketPriority`
  - `category: TicketCategory`
  - `createdBy: UUID`
  - `assignedTo: UUID`
  - `assignedTeam: UUID`
  - `createdAt: LocalDateTime`
  - `updatedAt: LocalDateTime`
  - `resolvedAt: LocalDateTime`
  - `closedAt: LocalDateTime`

## `Ticket`
- File: `backend/src/main/java/com/voxera/backend/ticket/entity/Ticket.java`
- Kind: JPA entity
- Persistence annotation: `@Table(name = "tickets")`
- Persistence annotation: `@Id`
- Persistence annotation: `@Column(name = "ticket_id")`
- Persistence annotation: `@Column(name = "ticket_number", nullable = false, unique = true, length = 30)`
- Persistence annotation: `@Column(nullable = false, length = 255)`
- Persistence annotation: `@Column(nullable = false, columnDefinition = "TEXT")`
- Persistence annotation: `@Enumerated(EnumType.STRING)`
- Persistence annotation: `@Column(nullable = false, length = 30)`
- Persistence annotation: `@Enumerated(EnumType.STRING)`
- Persistence annotation: `@Column(nullable = false, length = 30)`
- Persistence annotation: `@Enumerated(EnumType.STRING)`
- Persistence annotation: `@Column(nullable = false, length = 50)`
- Persistence annotation: `@ManyToOne(fetch = FetchType.LAZY, optional = false)`
- Persistence annotation: `@JoinColumn(name = "created_by", nullable = false)`
- Persistence annotation: `@ManyToOne(fetch = FetchType.LAZY)`
- Persistence annotation: `@JoinColumn(name = "assigned_to")`
- Persistence annotation: `@ManyToOne(fetch = FetchType.LAZY)`
- Persistence annotation: `@JoinColumn(name = "assigned_team")`
- Persistence annotation: `@Column(name = "created_at", nullable = false)`
- Persistence annotation: `@Column(name = "updated_at", nullable = false)`
- Persistence annotation: `@Column(name = "resolved_at")`
- Persistence annotation: `@Column(name = "closed_at")`
- Persistence annotation: `@Version`
- Persistence annotation: `@Column(nullable = false)`
- Fields/components:
  - `ticketId: UUID`
    - Nearby annotations (verify scope): `@Table(name = "tickets")`, `@Id`, `@Column(name = "ticket_id")`
  - `ticketNumber: String`
    - Nearby annotations (verify scope): `@Id`, `@Column(name = "ticket_id")`, `@Column(name = "ticket_number", nullable = false, unique = true, length = 30)`
  - `title: String`
    - Nearby annotations (verify scope): `@Column(name = "ticket_id")`, `@Column(name = "ticket_number", nullable = false, unique = true, length = 30)`, `@Column(nullable = false, length = 255)`
  - `description: String`
    - Nearby annotations (verify scope): `@Column(name = "ticket_number", nullable = false, unique = true, length = 30)`, `@Column(nullable = false, length = 255)`, `@Column(nullable = false, columnDefinition = "TEXT")`
  - `status: TicketStatus`
    - Nearby annotations (verify scope): `@Column(nullable = false, columnDefinition = "TEXT")`, `@Enumerated(EnumType.STRING)`, `@Column(nullable = false, length = 30)`
  - `priority: TicketPriority`
    - Nearby annotations (verify scope): `@Column(nullable = false, length = 30)`, `@Enumerated(EnumType.STRING)`, `@Column(nullable = false, length = 30)`
  - `category: TicketCategory`
    - Nearby annotations (verify scope): `@Column(nullable = false, length = 30)`, `@Enumerated(EnumType.STRING)`, `@Column(nullable = false, length = 50)`
  - `createdBy: User`
    - Nearby annotations (verify scope): `@Column(nullable = false, length = 50)`, `@ManyToOne(fetch = FetchType.LAZY, optional = false)`, `@JoinColumn(name = "created_by", nullable = false)`
  - `assignedTo: User`
    - Nearby annotations (verify scope): `@JoinColumn(name = "created_by", nullable = false)`, `@ManyToOne(fetch = FetchType.LAZY)`, `@JoinColumn(name = "assigned_to")`
  - `assignedTeam: Team`
    - Nearby annotations (verify scope): `@JoinColumn(name = "assigned_to")`, `@ManyToOne(fetch = FetchType.LAZY)`, `@JoinColumn(name = "assigned_team")`
  - `createdAt: LocalDateTime`
    - Nearby annotations (verify scope): `@ManyToOne(fetch = FetchType.LAZY)`, `@JoinColumn(name = "assigned_team")`, `@Column(name = "created_at", nullable = false)`
  - `updatedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@JoinColumn(name = "assigned_team")`, `@Column(name = "created_at", nullable = false)`, `@Column(name = "updated_at", nullable = false)`
  - `resolvedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(name = "created_at", nullable = false)`, `@Column(name = "updated_at", nullable = false)`, `@Column(name = "resolved_at")`
  - `closedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(name = "updated_at", nullable = false)`, `@Column(name = "resolved_at")`, `@Column(name = "closed_at")`
  - `version: Long`
    - Nearby annotations (verify scope): `@Column(name = "closed_at")`, `@Version`, `@Column(nullable = false)`

## `TicketComment`
- File: `backend/src/main/java/com/voxera/backend/ticket/entity/TicketComment.java`
- Kind: JPA entity
- Persistence annotation: `@Table(name = "ticket_comments")`
- Persistence annotation: `@Id`
- Persistence annotation: `@Column(name = "comment_id")`
- Persistence annotation: `@ManyToOne(fetch = FetchType.LAZY, optional = false)`
- Persistence annotation: `@JoinColumn(name = "ticket_id", nullable = false)`
- Persistence annotation: `@ManyToOne(fetch = FetchType.LAZY, optional = false)`
- Persistence annotation: `@JoinColumn(name = "author_id", nullable = false)`
- Persistence annotation: `@Column(nullable = false, columnDefinition = "TEXT")`
- Persistence annotation: `@Column(name = "created_at", nullable = false)`
- Persistence annotation: `@Column(name = "updated_at", nullable = false)`
- Fields/components:
  - `commentId: UUID`
    - Nearby annotations (verify scope): `@Table(name = "ticket_comments")`, `@Id`, `@Column(name = "comment_id")`
  - `ticket: Ticket`
    - Nearby annotations (verify scope): `@Column(name = "comment_id")`, `@ManyToOne(fetch = FetchType.LAZY, optional = false)`, `@JoinColumn(name = "ticket_id", nullable = false)`
  - `author: User`
    - Nearby annotations (verify scope): `@JoinColumn(name = "ticket_id", nullable = false)`, `@ManyToOne(fetch = FetchType.LAZY, optional = false)`, `@JoinColumn(name = "author_id", nullable = false)`
  - `content: String`
    - Nearby annotations (verify scope): `@ManyToOne(fetch = FetchType.LAZY, optional = false)`, `@JoinColumn(name = "author_id", nullable = false)`, `@Column(nullable = false, columnDefinition = "TEXT")`
  - `createdAt: LocalDateTime`
    - Nearby annotations (verify scope): `@JoinColumn(name = "author_id", nullable = false)`, `@Column(nullable = false, columnDefinition = "TEXT")`, `@Column(name = "created_at", nullable = false)`
  - `updatedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(nullable = false, columnDefinition = "TEXT")`, `@Column(name = "created_at", nullable = false)`, `@Column(name = "updated_at", nullable = false)`

## `TicketHistory`
- File: `backend/src/main/java/com/voxera/backend/ticket/entity/TicketHistory.java`
- Kind: JPA entity
- Persistence annotation: `@Table(name = "ticket_history")`
- Persistence annotation: `@Id`
- Persistence annotation: `@Column(name = "history_id")`
- Persistence annotation: `@ManyToOne(fetch = FetchType.LAZY, optional = false)`
- Persistence annotation: `@JoinColumn(name = "ticket_id", nullable = false)`
- Persistence annotation: `@Column(name = "actor_type", nullable = false, length = 30)`
- Persistence annotation: `@Column(name = "actor_id")`
- Persistence annotation: `@Column(nullable = false, length = 100)`
- Persistence annotation: `@Column(name = "old_value", columnDefinition = "TEXT")`
- Persistence annotation: `@Column(name = "new_value", columnDefinition = "TEXT")`
- Persistence annotation: `@Column(name = "created_at", nullable = false)`
- Fields/components:
  - `historyId: UUID`
    - Nearby annotations (verify scope): `@Table(name = "ticket_history")`, `@Id`, `@Column(name = "history_id")`
  - `ticket: Ticket`
    - Nearby annotations (verify scope): `@Column(name = "history_id")`, `@ManyToOne(fetch = FetchType.LAZY, optional = false)`, `@JoinColumn(name = "ticket_id", nullable = false)`
  - `actorType: String`
    - Nearby annotations (verify scope): `@ManyToOne(fetch = FetchType.LAZY, optional = false)`, `@JoinColumn(name = "ticket_id", nullable = false)`, `@Column(name = "actor_type", nullable = false, length = 30)`
  - `actorId: UUID`
    - Nearby annotations (verify scope): `@JoinColumn(name = "ticket_id", nullable = false)`, `@Column(name = "actor_type", nullable = false, length = 30)`, `@Column(name = "actor_id")`
  - `action: String`
    - Nearby annotations (verify scope): `@Column(name = "actor_type", nullable = false, length = 30)`, `@Column(name = "actor_id")`, `@Column(nullable = false, length = 100)`
  - `oldValue: String`
    - Nearby annotations (verify scope): `@Column(name = "actor_id")`, `@Column(nullable = false, length = 100)`, `@Column(name = "old_value", columnDefinition = "TEXT")`
  - `newValue: String`
    - Nearby annotations (verify scope): `@Column(nullable = false, length = 100)`, `@Column(name = "old_value", columnDefinition = "TEXT")`, `@Column(name = "new_value", columnDefinition = "TEXT")`
  - `createdAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(name = "old_value", columnDefinition = "TEXT")`, `@Column(name = "new_value", columnDefinition = "TEXT")`, `@Column(name = "created_at", nullable = false)`

## `TicketCategory`
- File: `backend/src/main/java/com/voxera/backend/ticket/enums/TicketCategory.java`
- Kind: enum
- Values: `HARDWARE`, `SOFTWARE`, `NETWORK`, `ACCESS`, `SECURITY`, `ACCOUNT`, `OTHER`
- No fields/components extracted.

## `TicketPriority`
- File: `backend/src/main/java/com/voxera/backend/ticket/enums/TicketPriority.java`
- Kind: enum
- Values: `LOW`, `MEDIUM`, `HIGH`, `CRITICAL`
- No fields/components extracted.

## `TicketStatus`
- File: `backend/src/main/java/com/voxera/backend/ticket/enums/TicketStatus.java`
- Kind: enum
- Values: `OPEN`, `IN_PROGRESS`, `PENDING`, `RESOLVED`, `CLOSED`, `CANCELLED`
- No fields/components extracted.

## `User`
- File: `backend/src/main/java/com/voxera/backend/user/entity/User.java`
- Kind: JPA entity
- Persistence annotation: `@Table(name = "users")`
- Persistence annotation: `@Id`
- Persistence annotation: `@Column(name = "user_id")`
- Persistence annotation: `@Column(name = "employee_id", nullable = false, unique = true, length = 50)`
- Persistence annotation: `@Column(nullable = false, length = 150)`
- Persistence annotation: `@Column(nullable = false, unique = true, length = 255)`
- Persistence annotation: `@Column(length = 100)`
- Persistence annotation: `@Enumerated(EnumType.STRING)`
- Persistence annotation: `@Column(name = "role", nullable = false)`
- Persistence annotation: `@Enumerated(EnumType.STRING)`
- Persistence annotation: `@Column(name = "status", nullable = false)`
- Persistence annotation: `@Column(name = "created_at", nullable = false)`
- Persistence annotation: `@Column(name = "updated_at", nullable = false)`
- Fields/components:
  - `userId: UUID`
    - Nearby annotations (verify scope): `@Table(name = "users")`, `@Id`, `@Column(name = "user_id")`
  - `employeeId: String`
    - Nearby annotations (verify scope): `@Id`, `@Column(name = "user_id")`, `@Column(name = "employee_id", nullable = false, unique = true, length = 50)`
  - `name: String`
    - Nearby annotations (verify scope): `@Column(name = "user_id")`, `@Column(name = "employee_id", nullable = false, unique = true, length = 50)`, `@Column(nullable = false, length = 150)`
  - `email: String`
    - Nearby annotations (verify scope): `@Column(name = "employee_id", nullable = false, unique = true, length = 50)`, `@Column(nullable = false, length = 150)`, `@Column(nullable = false, unique = true, length = 255)`
  - `department: String`
    - Nearby annotations (verify scope): `@Column(nullable = false, length = 150)`, `@Column(nullable = false, unique = true, length = 255)`, `@Column(length = 100)`
  - `role: UserRole`
    - Nearby annotations (verify scope): `@Column(length = 100)`, `@Enumerated(EnumType.STRING)`, `@Column(name = "role", nullable = false)`
  - `status: UserStatus`
    - Nearby annotations (verify scope): `@Column(name = "role", nullable = false)`, `@Enumerated(EnumType.STRING)`, `@Column(name = "status", nullable = false)`
  - `createdAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Enumerated(EnumType.STRING)`, `@Column(name = "status", nullable = false)`, `@Column(name = "created_at", nullable = false)`
  - `updatedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(name = "status", nullable = false)`, `@Column(name = "created_at", nullable = false)`, `@Column(name = "updated_at", nullable = false)`

## `UserCredential`
- File: `backend/src/main/java/com/voxera/backend/user/entity/UserCredential.java`
- Kind: JPA entity
- Persistence annotation: `@Table(name = "user_credentials")`
- Persistence annotation: `@Id`
- Persistence annotation: `@Column(name = "user_id")`
- Persistence annotation: `@Column(name = "password_hash", nullable = false, length = 255)`
- Persistence annotation: `@Column(name = "created_at", nullable = false)`
- Persistence annotation: `@Column(name = "updated_at", nullable = false)`
- Fields/components:
  - `userId: UUID`
    - Nearby annotations (verify scope): `@Table(name = "user_credentials")`, `@Id`, `@Column(name = "user_id")`
  - `passwordHash: String`
    - Nearby annotations (verify scope): `@Id`, `@Column(name = "user_id")`, `@Column(name = "password_hash", nullable = false, length = 255)`
  - `createdAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(name = "user_id")`, `@Column(name = "password_hash", nullable = false, length = 255)`, `@Column(name = "created_at", nullable = false)`
  - `updatedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(name = "password_hash", nullable = false, length = 255)`, `@Column(name = "created_at", nullable = false)`, `@Column(name = "updated_at", nullable = false)`

## `UserRole`
- File: `backend/src/main/java/com/voxera/backend/user/enums/UserRole.java`
- Kind: enum
- Values: `EMPLOYEE`, `AGENT`, `ADMIN`
- No fields/components extracted.

## `UserStatus`
- File: `backend/src/main/java/com/voxera/backend/user/enums/UserStatus.java`
- Kind: enum
- Values: `ACTIVE`, `INACTIVE`
- No fields/components extracted.

> This is a structural inventory, not a guarantee of database constraints or runtime validation. Verify annotations in source.

---

## Document: docs/metadata/PROJECT_STATUS.md

# Voxera Project Status

> Manually maintained. Update after meaningful implementation milestones.

## Current checkpoint
- Last verified commit: update after checking Git.
- Build/test status: not verified by this metadata generator.
- Active milestone: resource-based ticket authorization.

## Decisions
- Assignment remains manual.
- Agent access is limited to individually assigned tickets.
- Team-based authorization is deferred.
- Avoid unnecessary expansion of tests; prioritize meaningful regression coverage.

## Completed
- Update from the current roadmap and verified repository state.

## In progress
- Resource-based ticket authorization and role-scoped ticket listing.

## Next
- Verify focused authorization tests, then run the full test suite.
- Refresh metadata and record the resulting checkpoint.

---

## Document: docs/metadata/PROJECT_STRUCTURE.md

# Project Structure and Java Inventory

> Generated: 2026-10-27T11:38:46.582846+00:00
> Git commit: `0b363fd`
> Generator: `scripts/generate_metadata.py`


## Source and test files

- `backend/src/main/java/com/voxera/backend/BackendApplication.java`
- `backend/src/main/java/com/voxera/backend/exception/GlobalExceptionHandler.java`
- `backend/src/main/java/com/voxera/backend/exception/ResourceNotFoundException.java`
- `backend/src/main/java/com/voxera/backend/security/CurrentUserService.java`
- `backend/src/main/java/com/voxera/backend/security/authentication/AuthController.java`
- `backend/src/main/java/com/voxera/backend/security/authentication/AuthenticationService.java`
- `backend/src/main/java/com/voxera/backend/security/authentication/BCryptPasswordService.java`
- `backend/src/main/java/com/voxera/backend/security/authentication/PasswordService.java`
- `backend/src/main/java/com/voxera/backend/security/authentication/dto/LoginRequest.java`
- `backend/src/main/java/com/voxera/backend/security/authentication/dto/LoginResponse.java`
- `backend/src/main/java/com/voxera/backend/security/config/SecurityConfig.java`
- `backend/src/main/java/com/voxera/backend/security/jwt/JwtAuthenticationFilter.java`
- `backend/src/main/java/com/voxera/backend/security/jwt/JwtService.java`
- `backend/src/main/java/com/voxera/backend/team/entity/Team.java`
- `backend/src/main/java/com/voxera/backend/team/repository/TeamRepository.java`
- `backend/src/main/java/com/voxera/backend/team/service/TeamService.java`
- `backend/src/main/java/com/voxera/backend/ticket/controller/TicketCommentController.java`
- `backend/src/main/java/com/voxera/backend/ticket/controller/TicketController.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/AssignTicketRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCommentRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/TicketCommentResponse.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/TicketResponse.java`
- `backend/src/main/java/com/voxera/backend/ticket/entity/Ticket.java`
- `backend/src/main/java/com/voxera/backend/ticket/entity/TicketComment.java`
- `backend/src/main/java/com/voxera/backend/ticket/entity/TicketHistory.java`
- `backend/src/main/java/com/voxera/backend/ticket/enums/TicketCategory.java`
- `backend/src/main/java/com/voxera/backend/ticket/enums/TicketPriority.java`
- `backend/src/main/java/com/voxera/backend/ticket/enums/TicketStatus.java`
- `backend/src/main/java/com/voxera/backend/ticket/repository/TicketCommentRepository.java`
- `backend/src/main/java/com/voxera/backend/ticket/repository/TicketHistoryRepository.java`
- `backend/src/main/java/com/voxera/backend/ticket/repository/TicketRepository.java`
- `backend/src/main/java/com/voxera/backend/ticket/service/TicketAuthorizationService.java`
- `backend/src/main/java/com/voxera/backend/ticket/service/TicketCommentService.java`
- `backend/src/main/java/com/voxera/backend/ticket/service/TicketService.java`
- `backend/src/main/java/com/voxera/backend/user/entity/User.java`
- `backend/src/main/java/com/voxera/backend/user/entity/UserCredential.java`
- `backend/src/main/java/com/voxera/backend/user/enums/UserRole.java`
- `backend/src/main/java/com/voxera/backend/user/enums/UserStatus.java`
- `backend/src/main/java/com/voxera/backend/user/repository/UserCredentialRepository.java`
- `backend/src/main/java/com/voxera/backend/user/repository/UserRepository.java`
- `backend/src/main/java/com/voxera/backend/user/service/UserService.java`
- `backend/src/test/java/com/voxera/backend/BackendApplicationTests.java`
- `backend/src/test/java/com/voxera/backend/security/CurrentUserServiceTest.java`
- `backend/src/test/java/com/voxera/backend/security/authentication/AuthControllerIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/security/authentication/AuthenticationServiceIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/security/authentication/AuthenticationServiceTest.java`
- `backend/src/test/java/com/voxera/backend/security/authentication/BCryptPasswordServiceTest.java`
- `backend/src/test/java/com/voxera/backend/security/jwt/JwtServiceTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/AbstractTicketControllerIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAssignmentIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAuthorizationIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCommentIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCreationIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketLifecycleIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketRetrievalIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/entity/TicketTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/service/TicketServiceTest.java`

## Java classes and method signatures


### `BackendApplication`
- File: `backend/src/main/java/com/voxera/backend/BackendApplication.java`
- Package: `com.voxera.backend`
- `void main(String[] args)`

### `GlobalExceptionHandler`
- File: `backend/src/main/java/com/voxera/backend/exception/GlobalExceptionHandler.java`
- Package: `com.voxera.backend.exception`
- `Map<String, Object> handleNotFound(ResourceNotFoundException exception)`
- `Map<String, Object> handleAccessDenied(AccessDeniedException exception)`
- `Map<String, Object> handleInvalidState(IllegalStateException exception)`
- `Map<String, Object> handleBadCredentials(BadCredentialsException exception)`

### `ResourceNotFoundException`
- File: `backend/src/main/java/com/voxera/backend/exception/ResourceNotFoundException.java`
- Package: `com.voxera.backend.exception`
- `ResourceNotFoundException(String message)`

### `CurrentUserService`
- File: `backend/src/main/java/com/voxera/backend/security/CurrentUserService.java`
- Package: `com.voxera.backend.security`
- `CurrentUserService(UserRepository userRepository)`
- `User getCurrentUser()`
- ` if(authentication == null || !authentication.isAuthenticated())`

### `AuthController`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/AuthController.java`
- Package: `com.voxera.backend.security.authentication`
- `AuthController(AuthenticationService authenticationService, JwtService jwtService)`
- `ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request)`

### `AuthenticationService`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/AuthenticationService.java`
- Package: `com.voxera.backend.security.authentication`
- `AuthenticationService(UserRepository userRepository, UserCredentialRepository userCredentialRepository, PasswordService passwordService)`
- `User authenticate(String email, String rawPassword)`
- ` if(user.getStatus() != UserStatus.ACTIVE)`
- ` if(!passwordService.matches(rawPassword, credential.getPasswordHash()))`

### `BCryptPasswordService`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/BCryptPasswordService.java`
- Package: `com.voxera.backend.security.authentication`
- `BCryptPasswordService()`
- `String hash(String rawPassword)`
- `boolean matches(String rawPassword, String passwordHash)`

### `PasswordService`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/PasswordService.java`
- Package: `com.voxera.backend.security.authentication`
- No method signatures extracted.

### `LoginRequest`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/dto/LoginRequest.java`
- Package: `com.voxera.backend.security.authentication.dto`
- `LoginRequest(@NotBlank @Email String email, @NotBlank String password)`

### `LoginResponse`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/dto/LoginResponse.java`
- Package: `com.voxera.backend.security.authentication.dto`
- `LoginResponse(UUID userId, String employeeId, String name, String email, UserRole role, String accessToken)`

### `SecurityConfig`
- File: `backend/src/main/java/com/voxera/backend/security/config/SecurityConfig.java`
- Package: `com.voxera.backend.security.config`
- `SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter)`
- `SecurityFilterChain securityFilterChain(HttpSecurity http)`

### `JwtAuthenticationFilter`
- File: `backend/src/main/java/com/voxera/backend/security/jwt/JwtAuthenticationFilter.java`
- Package: `com.voxera.backend.security.jwt`
- `JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository)`
- `void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)`
- ` if(authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))`
- ` if(user != null && user.getStatus() == UserStatus.ACTIVE && jwtService.isTokenValid(token, user))`

### `JwtService`
- File: `backend/src/main/java/com/voxera/backend/security/jwt/JwtService.java`
- Package: `com.voxera.backend.security.jwt`
- ` if(expirationMs <= 0)`
- `String generateToken(User user)`
- `Claims extractClaims(String token)`
- `UUID extractUserId(String token)`
- `boolean isTokenValid(String token, User user)`

### `Team`
- File: `backend/src/main/java/com/voxera/backend/team/entity/Team.java`
- Package: `com.voxera.backend.team.entity`
- `Team()`
- `Team(UUID teamId, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt)`
- `UUID getTeamId()`
- `String getName()`
- `String getDescription()`
- `LocalDateTime getCreatedAt()`
- `LocalDateTime getUpdatedAt()`

### `TeamRepository`
- File: `backend/src/main/java/com/voxera/backend/team/repository/TeamRepository.java`
- Package: `com.voxera.backend.team.repository`
- No method signatures extracted.

### `TeamService`
- File: `backend/src/main/java/com/voxera/backend/team/service/TeamService.java`
- Package: `com.voxera.backend.team.service`
- `TeamService(TeamRepository teamRepository)`
- `Team getTeam(UUID teamId)`

### `TicketCommentController`
- File: `backend/src/main/java/com/voxera/backend/ticket/controller/TicketCommentController.java`
- Package: `com.voxera.backend.ticket.controller`
- `TicketCommentController(TicketCommentService ticketCommentService)`
- `TicketCommentResponse addComment(@PathVariable UUID ticketId, @Valid @RequestBody CreateTicketCommentRequest request)`
- `List<TicketCommentResponse> getComments(@PathVariable UUID ticketId)`

### `TicketController`
- File: `backend/src/main/java/com/voxera/backend/ticket/controller/TicketController.java`
- Package: `com.voxera.backend.ticket.controller`
- `TicketController(TicketService ticketService)`
- `TicketResponse createTicket(@Valid @RequestBody CreateTicketRequest request)`
- `TicketResponse getTicket(@PathVariable UUID ticketId)`
- `List<TicketResponse> getTickets(@RequestParam UUID createdBy)`
- `TicketResponse startProgress(@PathVariable UUID ticketId)`
- `TicketResponse markPending(@PathVariable UUID ticketId)`
- `TicketResponse resolveTicket(@PathVariable UUID ticketId)`
- `TicketResponse closeTicket(@PathVariable UUID ticketId)`
- `TicketResponse cancelTicket(@PathVariable UUID ticketId)`
- `ResponseEntity<TicketResponse> assignTicket(@PathVariable UUID ticketId, @Valid @RequestBody AssignTicketRequest request)`

### `AssignTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/AssignTicketRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `AssignTicketRequest(@NotNull UUID assignedTo)`

### `CreateTicketCommentRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCommentRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `CreateTicketCommentRequest(@NotBlank @Size(max = 5000) String content)`

### `CreateTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `CreateTicketRequest(@NotBlank String title, @NotBlank String description, @NotNull TicketPriority priority, @NotNull TicketCategory category)`

### `TicketCommentResponse`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/TicketCommentResponse.java`
- Package: `com.voxera.backend.ticket.dto`
- `TicketCommentResponse(UUID commentId, UUID ticketId, UUID authorId, String authorName, String content, LocalDateTime createdAt, LocalDateTime updatedAt)`
- `TicketCommentResponse from(TicketComment comment)`

### `TicketResponse`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/TicketResponse.java`
- Package: `com.voxera.backend.ticket.dto`
- `TicketResponse(UUID ticketId, String ticketNumber, String title, String description, TicketStatus status, TicketPriority priority, TicketCategory category, UUID createdBy, UUID assignedTo, UUID assignedTeam, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime resolvedAt, LocalDateTime closedAt)`
- `TicketResponse from(Ticket ticket)`

### `Ticket`
- File: `backend/src/main/java/com/voxera/backend/ticket/entity/Ticket.java`
- Package: `com.voxera.backend.ticket.entity`
- `Ticket()`
- `Ticket(UUID ticketId, String ticketNumber, String title, String description, TicketPriority priority, TicketCategory category, User createdBy, LocalDateTime createdAt)`
- `void startProgress()`
- `void markPending()`
- `void resolve()`
- ` if(status != TicketStatus.IN_PROGRESS && status != TicketStatus.PENDING)`
- `void close()`
- `void cancel()`
- ` if(status == TicketStatus.CLOSED || status == TicketStatus.CANCELLED)`
- `void assignTo(User user)`
- `void assignToTeam(Team team)`
- `void requireStatus(TicketStatus expected)`
- ` if(status != expected)`
- `void touch()`
- `UUID getTicketId()`
- `String getTicketNumber()`
- `String getTitle()`
- `String getDescription()`
- `TicketStatus getStatus()`
- `TicketPriority getPriority()`
- `TicketCategory getCategory()`
- `User getCreatedBy()`
- `User getAssignedTo()`
- `Team getAssignedTeam()`
- `LocalDateTime getCreatedAt()`
- `LocalDateTime getUpdatedAt()`
- `LocalDateTime getResolvedAt()`
- `LocalDateTime getClosedAt()`
- `Long getVersion()`

### `TicketComment`
- File: `backend/src/main/java/com/voxera/backend/ticket/entity/TicketComment.java`
- Package: `com.voxera.backend.ticket.entity`
- `TicketComment()`
- `TicketComment(UUID commentId, Ticket ticket, User author, String content, LocalDateTime createdAt)`
- `UUID getCommentId()`
- `Ticket getTicket()`
- `User getAuthor()`
- `String getContent()`
- `LocalDateTime getCreatedAt()`
- `LocalDateTime getUpdatedAt()`

### `TicketHistory`
- File: `backend/src/main/java/com/voxera/backend/ticket/entity/TicketHistory.java`
- Package: `com.voxera.backend.ticket.entity`
- `TicketHistory()`
- `UUID getHistoryId()`
- `Ticket getTicket()`
- `String getActorType()`
- `UUID getActorId()`
- `String getAction()`
- `String getOldValue()`
- `String getNewValue()`
- `LocalDateTime getCreatedAt()`

### `TicketCategory`
- File: `backend/src/main/java/com/voxera/backend/ticket/enums/TicketCategory.java`
- Package: `com.voxera.backend.ticket.enums`
- No method signatures extracted.

### `TicketPriority`
- File: `backend/src/main/java/com/voxera/backend/ticket/enums/TicketPriority.java`
- Package: `com.voxera.backend.ticket.enums`
- No method signatures extracted.

### `TicketStatus`
- File: `backend/src/main/java/com/voxera/backend/ticket/enums/TicketStatus.java`
- Package: `com.voxera.backend.ticket.enums`
- No method signatures extracted.

### `TicketCommentRepository`
- File: `backend/src/main/java/com/voxera/backend/ticket/repository/TicketCommentRepository.java`
- Package: `com.voxera.backend.ticket.repository`
- No method signatures extracted.

### `TicketHistoryRepository`
- File: `backend/src/main/java/com/voxera/backend/ticket/repository/TicketHistoryRepository.java`
- Package: `com.voxera.backend.ticket.repository`
- No method signatures extracted.

### `TicketRepository`
- File: `backend/src/main/java/com/voxera/backend/ticket/repository/TicketRepository.java`
- Package: `com.voxera.backend.ticket.repository`
- No method signatures extracted.

### `TicketAuthorizationService`
- File: `backend/src/main/java/com/voxera/backend/ticket/service/TicketAuthorizationService.java`
- Package: `com.voxera.backend.ticket.service`
- `void requireResourceAccess(User actor, Ticket ticket)`
- ` if(actor.getRole() == UserRole.EMPLOYEE && !ticket.getCreatedBy().getUserId() .equals(actor.getUserId()))`
- `void requireAgentOrAdmin(User actor)`
- ` if(actor.getRole() != UserRole.AGENT && actor.getRole() != UserRole.ADMIN)`
- `void requireTicketListAccess(User actor, UUID requestedUserId)`
- ` if(actor.getRole() == UserRole.EMPLOYEE && !actor.getUserId().equals(requestedUserId))`

### `TicketCommentService`
- File: `backend/src/main/java/com/voxera/backend/ticket/service/TicketCommentService.java`
- Package: `com.voxera.backend.ticket.service`
- `TicketCommentService(TicketCommentRepository ticketCommentRepository, TicketService ticketService, CurrentUserService currentUserService)`
- `TicketCommentResponse addComment(UUID ticketId, String content)`
- `List<TicketCommentResponse> getComments(UUID ticketId)`

### `TicketService`
- File: `backend/src/main/java/com/voxera/backend/ticket/service/TicketService.java`
- Package: `com.voxera.backend.ticket.service`
- `TicketService(TicketRepository ticketRepository, UserRepository userRepository, CurrentUserService currentUserService, TicketAuthorizationService ticketAuthorizationService)`
- `Ticket createTicket(String title, String description, TicketPriority priority, TicketCategory category)`
- `Ticket getTicket(UUID ticketId)`
- `List<Ticket> getTicketsForUser(UUID userId)`
- `Ticket startProgress(UUID ticketId)`
- `Ticket markPending(UUID ticketId)`
- `Ticket resolveTicket(UUID ticketId)`
- `Ticket closeTicket(UUID ticketId)`
- `Ticket cancelTicket(UUID ticketId)`
- `Ticket assignTicket(UUID ticketId, UUID userId)`
- `String generateTicketNumber()`

### `User`
- File: `backend/src/main/java/com/voxera/backend/user/entity/User.java`
- Package: `com.voxera.backend.user.entity`
- `User()`
- `User(UUID userId, String employeeId, String name, String email, String department, UserRole role, UserStatus status, LocalDateTime createdAt, LocalDateTime updatedAt)`
- `UUID getUserId()`
- `String getEmployeeId()`
- `String getName()`
- `String getEmail()`
- `String getDepartment()`
- `UserRole getRole()`
- `UserStatus getStatus()`
- `LocalDateTime getCreatedAt()`
- `LocalDateTime getUpdatedAt()`

### `UserCredential`
- File: `backend/src/main/java/com/voxera/backend/user/entity/UserCredential.java`
- Package: `com.voxera.backend.user.entity`
- `UserCredential()`
- `UserCredential(UUID userId, String passwordHash, LocalDateTime createdAt, LocalDateTime updatedAt)`
- `UUID getUserId()`
- `String getPasswordHash()`
- `LocalDateTime getCreatedAt()`
- `LocalDateTime getUpdatedAt()`

### `UserRole`
- File: `backend/src/main/java/com/voxera/backend/user/enums/UserRole.java`
- Package: `com.voxera.backend.user.enums`
- `String getAuthority()`

### `UserStatus`
- File: `backend/src/main/java/com/voxera/backend/user/enums/UserStatus.java`
- Package: `com.voxera.backend.user.enums`
- No method signatures extracted.

### `UserCredentialRepository`
- File: `backend/src/main/java/com/voxera/backend/user/repository/UserCredentialRepository.java`
- Package: `com.voxera.backend.user.repository`
- No method signatures extracted.

### `UserRepository`
- File: `backend/src/main/java/com/voxera/backend/user/repository/UserRepository.java`
- Package: `com.voxera.backend.user.repository`
- No method signatures extracted.

### `UserService`
- File: `backend/src/main/java/com/voxera/backend/user/service/UserService.java`
- Package: `com.voxera.backend.user.service`
- `UserService(UserRepository userRepository)`
- `User getUser(UUID userId)`
- `User getUserByEmployeeId(String employeeId)`

### `BackendApplicationTests`
- File: `backend/src/test/java/com/voxera/backend/BackendApplicationTests.java`
- Package: `com.voxera.backend`
- `void contextLoads()`

### `CurrentUserServiceTest`
- File: `backend/src/test/java/com/voxera/backend/security/CurrentUserServiceTest.java`
- Package: `com.voxera.backend.security`
- `void tearDown()`
- `void getCurrentUser_shouldReturnAuthenticatedUser()`
- `void getCurrentUser_shouldRejectUnauthenticatedRequest()`
- `void getCurrentUser_shouldRejectUnknownUser()`

### `AuthControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/security/authentication/AuthControllerIntegrationTest.java`
- Package: `com.voxera.backend.security.authentication`
- `void login_shouldAuthenticateValidCredentials()`
- `void login_shouldRejectInvalidPassword()`
- `void login_shouldRejectUnknownEmail()`
- `void login_shouldRejectInvalidRequest()`

### `AuthenticationServiceIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/security/authentication/AuthenticationServiceIntegrationTest.java`
- Package: `com.voxera.backend.security.authentication`
- `void authenticate_shouldAuthenticateSeededDevelopmentUser()`
- `void authenticate_shouldRejectInvalidPassword()`

### `AuthenticationServiceTest`
- File: `backend/src/test/java/com/voxera/backend/security/authentication/AuthenticationServiceTest.java`
- Package: `com.voxera.backend.security.authentication`
- `User createUser(UserStatus status)`
- `UserCredential createCredential()`
- `void authenticate_shouldReturnUserForValidCredentials()`
- `void authenticate_shouldRejectUnknownEmail()`
- `void authenticate_shouldRejectWrongPassword()`
- `void authenticate_shouldRejectInactiveUser()`
- `void authenticate_shouldRejectMissingCredential()`

### `BCryptPasswordServiceTest`
- File: `backend/src/test/java/com/voxera/backend/security/authentication/BCryptPasswordServiceTest.java`
- Package: `com.voxera.backend.security.authentication`
- `void hash_shouldNotReturnRawPassword()`
- `void matches_shouldReturnTrueForCorrectPassword()`
- `void matches_shouldReturnFalseForIncorrectPassword()`
- `void hash_shouldProduceDifferentHashesForSamePassword()`

### `JwtServiceTest`
- File: `backend/src/test/java/com/voxera/backend/security/jwt/JwtServiceTest.java`
- Package: `com.voxera.backend.security.jwt`
- `void setUp()`
- `void generateToken_shouldContainExpectedClaims()`
- `void isTokenValid_shouldReturnTrueForCorrectUser()`
- `void isTokenValid_shouldReturnFalseForDifferentUser()`
- `void extractClaims_shouldRejectMalformedToken()`
- `void extractClaims_shouldRejectTokenSignedWithDifferentKey()`

### `AbstractTicketControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/AbstractTicketControllerIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void setUp()`
- `MockHttpServletRequestBuilder authenticated(MockHttpServletRequestBuilder request, String token)`
- `MockHttpServletRequestBuilder authenticated(MockHttpServletRequestBuilder request)`
- `void createTicket(String title, String token)`
- `void createTicket(String title)`
- `String createTicketRequest(String title, String description, String priority, String category)`
- `User createTestUser(UserRole role)`
- `User createTestUser()`
- `void createTestUserCredentials(UUID userId, String rawPassword)`
- `String createAgentToken()`
- `String loginAndGetAccessToken(String email,String password)`

### `TicketAssignmentIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAssignmentIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void assignTicket_shouldAssignUserAndPersistAssignment()`
- `void assignTicket_shouldReturn404ForUnknownUser()`
- `void assignTicket_shouldRejectMissingAssignedUser()`
- `void assignTicket_shouldForbidEmployeeAndLeaveTicketUnassigned()`
- `void reassignTicket_shouldChangeAssigneeWithoutChangingLifecycleStatus()`
- `void agentCanAccessTicketAssignedToAnotherAgent()`

### `TicketAuthorizationIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAuthorizationIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void ticketEndpoint_shouldRejectUnauthenticatedRequest()`
- `void employeeCannotReadAnotherEmployeesTicket()`

### `TicketCommentIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCommentIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void employeeCanCreateAndRetrieveCommentsOnOwnTicket()`
- `void agentCanReadCommentsOnTicketRegardlessOfAssignment()`
- `void employeeCannotReadCommentsOnAnotherUsersTicket()`
- `void blankCommentShouldBeRejected()`
- `void commentOnUnknownTicketShouldReturnNotFound()`

### `TicketCreationIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCreationIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void createTicket_shouldReturnCreatedTicketAndPersistIt()`
- `void createTicket_shouldRejectInvalidRequest()`

### `TicketLifecycleIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketLifecycleIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void ticketLifecycle_shouldPersistStateTransitions()`
- `void employeeCanCancelOwnTicket()`
- `void employeeCannotCancelAnotherEmployeesTicket()`
- `void employeeCannotPerformAgentOnlyLifecycleActions()`
- ` for(String action : actions)`

### `TicketRetrievalIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketRetrievalIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void getTicket_shouldReturnExistingTicket()`
- `void getTicket_shouldReturn404ForUnknownTicket()`
- `void getTicketsForUser_shouldReturnTicketsForRequestedUser()`

### `TicketTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/entity/TicketTest.java`
- Package: `com.voxera.backend.ticket.entity`
- `Ticket createTicket()`
- `void newTicketShouldStartAsOpen()`
- `void openTicketCanStartProgress()`
- `void inProgressTicketCanBeMarkedPending()`
- `void inProgressTicketCanBeResolved()`
- `void pendingTicketCanBeResolved()`
- `void resolvedTicketCanBeClosed()`
- `void closedTicketCannotBeCancelled()`
- `void openTicketCannotBeResolvedDirectlyWithoutChangingStateOrTimestamps()`
- `void openTicketCannotBeClosedWithoutChangingStateOrTimestamps()`
- `void cancelledTicketCannotBeCancelledAgain()`
- `void resolvedTicketCanBeCancelledAndRetainsResolutionTimestamp()`
- `void lifecycleTimestampsRemainConsistent()`

### `TicketServiceTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/service/TicketServiceTest.java`
- Package: `com.voxera.backend.ticket.service`
- `void setUp()`
- `void createTicketShouldSaveTicketForExistingUser()`
- `void createTicketShouldGenerateTicketNumber()`
- `void getTicketShouldReturnExistingTicket()`
- `void getTicketShouldFailWhenTicketDoesNotExist()`
- `void startProgressShouldChangeTicketStatus()`
- `void markPendingShouldChangeTicketStatus()`
- `void resolveTicketShouldChangeTicketStatus()`
- `void closeTicketShouldChangeTicketStatus()`
- `void cancelTicketShouldChangeTicketStatus()`
- `void lifecycleOperationShouldFailWhenTicketDoesNotExist()`
- `void assignTicketShouldAssignExistingUser()`
- `void assignTicketShouldFailWhenAssigneeDoesNotExist()`
- `void assignTicketShouldFailWhenTicketDoesNotExist()`

---

## Document: docs/metadata/SECURITY_AND_RULES.md

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

---

## Document: docs/metadata/TEST_MAP.md

# Test Map

> Generated: 2026-10-27T11:38:46.732495+00:00
> Git commit: `0b363fd`
> Generator: `scripts/generate_metadata.py`



## `BackendApplicationTests`
- File: `backend/src/test/java/com/voxera/backend/BackendApplicationTests.java`
- Test methods detected: 1
  - `contextLoads()`

## `CurrentUserServiceTest`
- File: `backend/src/test/java/com/voxera/backend/security/CurrentUserServiceTest.java`
- Test methods detected: 3
  - `getCurrentUser_shouldReturnAuthenticatedUser()`
  - `getCurrentUser_shouldRejectUnauthenticatedRequest()`
  - `getCurrentUser_shouldRejectUnknownUser()`

## `AuthControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/security/authentication/AuthControllerIntegrationTest.java`
- Test methods detected: 4
  - `login_shouldAuthenticateValidCredentials()`
  - `login_shouldRejectInvalidPassword()`
  - `login_shouldRejectUnknownEmail()`
  - `login_shouldRejectInvalidRequest()`

## `AuthenticationServiceIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/security/authentication/AuthenticationServiceIntegrationTest.java`
- Test methods detected: 2
  - `authenticate_shouldAuthenticateSeededDevelopmentUser()`
  - `authenticate_shouldRejectInvalidPassword()`

## `AuthenticationServiceTest`
- File: `backend/src/test/java/com/voxera/backend/security/authentication/AuthenticationServiceTest.java`
- Test methods detected: 5
  - `authenticate_shouldReturnUserForValidCredentials()`
  - `authenticate_shouldRejectUnknownEmail()`
  - `authenticate_shouldRejectWrongPassword()`
  - `authenticate_shouldRejectInactiveUser()`
  - `authenticate_shouldRejectMissingCredential()`

## `BCryptPasswordServiceTest`
- File: `backend/src/test/java/com/voxera/backend/security/authentication/BCryptPasswordServiceTest.java`
- Test methods detected: 4
  - `hash_shouldNotReturnRawPassword()`
  - `matches_shouldReturnTrueForCorrectPassword()`
  - `matches_shouldReturnFalseForIncorrectPassword()`
  - `hash_shouldProduceDifferentHashesForSamePassword()`

## `JwtServiceTest`
- File: `backend/src/test/java/com/voxera/backend/security/jwt/JwtServiceTest.java`
- Test methods detected: 5
  - `generateToken_shouldContainExpectedClaims()`
  - `isTokenValid_shouldReturnTrueForCorrectUser()`
  - `isTokenValid_shouldReturnFalseForDifferentUser()`
  - `extractClaims_shouldRejectMalformedToken()`
  - `extractClaims_shouldRejectTokenSignedWithDifferentKey()`

## `AbstractTicketControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/AbstractTicketControllerIntegrationTest.java`
- Test methods detected: 0

## `TicketAssignmentIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAssignmentIntegrationTest.java`
- Test methods detected: 6
  - `assignTicket_shouldAssignUserAndPersistAssignment()`
  - `assignTicket_shouldReturn404ForUnknownUser()`
  - `assignTicket_shouldRejectMissingAssignedUser()`
  - `assignTicket_shouldForbidEmployeeAndLeaveTicketUnassigned()`
  - `reassignTicket_shouldChangeAssigneeWithoutChangingLifecycleStatus()`
  - `agentCanAccessTicketAssignedToAnotherAgent()`

## `TicketAuthorizationIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAuthorizationIntegrationTest.java`
- Test methods detected: 2
  - `ticketEndpoint_shouldRejectUnauthenticatedRequest()`
  - `employeeCannotReadAnotherEmployeesTicket()`

## `TicketCommentIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCommentIntegrationTest.java`
- Test methods detected: 5
  - `employeeCanCreateAndRetrieveCommentsOnOwnTicket()`
  - `agentCanReadCommentsOnTicketRegardlessOfAssignment()`
  - `employeeCannotReadCommentsOnAnotherUsersTicket()`
  - `blankCommentShouldBeRejected()`
  - `commentOnUnknownTicketShouldReturnNotFound()`

## `TicketCreationIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCreationIntegrationTest.java`
- Test methods detected: 2
  - `createTicket_shouldReturnCreatedTicketAndPersistIt()`
  - `createTicket_shouldRejectInvalidRequest()`

## `TicketLifecycleIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketLifecycleIntegrationTest.java`
- Test methods detected: 4
  - `ticketLifecycle_shouldPersistStateTransitions()`
  - `employeeCanCancelOwnTicket()`
  - `employeeCannotCancelAnotherEmployeesTicket()`
  - `employeeCannotPerformAgentOnlyLifecycleActions()`

## `TicketRetrievalIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketRetrievalIntegrationTest.java`
- Test methods detected: 3
  - `getTicket_shouldReturnExistingTicket()`
  - `getTicket_shouldReturn404ForUnknownTicket()`
  - `getTicketsForUser_shouldReturnTicketsForRequestedUser()`

## `TicketTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/entity/TicketTest.java`
- Test methods detected: 12
  - `newTicketShouldStartAsOpen()`
  - `openTicketCanStartProgress()`
  - `inProgressTicketCanBeMarkedPending()`
  - `inProgressTicketCanBeResolved()`
  - `pendingTicketCanBeResolved()`
  - `resolvedTicketCanBeClosed()`
  - `closedTicketCannotBeCancelled()`
  - `openTicketCannotBeResolvedDirectlyWithoutChangingStateOrTimestamps()`
  - `openTicketCannotBeClosedWithoutChangingStateOrTimestamps()`
  - `cancelledTicketCannotBeCancelledAgain()`
  - `resolvedTicketCanBeCancelledAndRetainsResolutionTimestamp()`
  - `lifecycleTimestampsRemainConsistent()`

## `TicketServiceTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/service/TicketServiceTest.java`
- Test methods detected: 13
  - `createTicketShouldSaveTicketForExistingUser()`
  - `createTicketShouldGenerateTicketNumber()`
  - `getTicketShouldReturnExistingTicket()`
  - `getTicketShouldFailWhenTicketDoesNotExist()`
  - `startProgressShouldChangeTicketStatus()`
  - `markPendingShouldChangeTicketStatus()`
  - `resolveTicketShouldChangeTicketStatus()`
  - `closeTicketShouldChangeTicketStatus()`
  - `cancelTicketShouldChangeTicketStatus()`
  - `lifecycleOperationShouldFailWhenTicketDoesNotExist()`
  - `assignTicketShouldAssignExistingUser()`
  - `assignTicketShouldFailWhenAssigneeDoesNotExist()`
  - `assignTicketShouldFailWhenTicketDoesNotExist()`

> Test names indicate intended coverage, not proof that a behavior is correctly tested. Use test source and test results for verification.

---

## Document: docs/voice-agent-integration-contract.md

# Voxera Voice-Agent Integration Contract

**Status:** Initial contract — Milestone 11  
**Version:** 1.0  
**Scope:** Backend API integration for the future React frontend and Python voice agent

## 1. Purpose and responsibilities

The voice agent interprets user speech, gathers information, and translates user intent into calls to the Voxera REST API.

The Voxera backend remains responsible for:
- Authentication and user identity.
- Role-based and resource-based authorization.
- Input validation and ticket-number generation.
- Ticket creation, retrieval, assignment, lifecycle transitions, and comments.
- Returning authoritative operation results.

The initial integration reuses existing APIs. No separate voice-agent user, authentication system, or voice-specific ticket controller is introduced.

## 2. Authentication and identity

### Login

`POST /api/v1/auth/login`

The user authenticates through Voxera and receives a `LoginResponse` containing:
- `userId`
- `employeeId`
- `name`
- `email`
- `role`
- `accessToken`

The exact login request fields are defined by the existing `LoginRequest` DTO.

### Authenticated API calls

Send the access token in the HTTP Authorization header:

`Authorization: Bearer <accessToken>`

The initial design is user-session based:
1. The user logs into Voxera.
2. The frontend makes the authenticated session available to the voice-agent integration.
3. The agent calls backend APIs using that user's authorization.
4. The backend validates the token and resolves the current user.
5. The backend applies its existing authorization rules to each operation.

The agent must not accept a caller-supplied user ID as proof of identity or modify the identity represented by the token.

### Token lifetime and handling

- The configured default access-token lifetime is 15 minutes (`900000` milliseconds).
- The existing configuration supports changing this duration through `VOXERA_JWT_EXPIRATION_MS`.
- This milestone does not change the token lifetime or introduce refresh tokens.
- Do not expose tokens in logs, transcripts, error messages, or ordinary application output.
- If a token expires, the integration must handle the authentication failure without pretending the operation succeeded.

The secure mechanism for transferring the token between the frontend and the voice-agent runtime must be finalized during implementation.

## 3. Ticket API

Base path: `/api/v1/tickets`

All requests require authentication under the current security configuration.

### 3.1 Create a ticket

`POST /api/v1/tickets`

Request body:

`CreateTicketRequest`

- `title` — required, non-blank string
- `description` — required, non-blank string
- `priority` — required `TicketPriority` enum value
- `category` — required `TicketCategory` enum value

Example shape:

    {
      "title": "Unable to connect to VPN",
      "description": "The VPN client fails to connect after login.",
      "priority": "<supported TicketPriority value>",
      "category": "<supported TicketCategory value>"
    }

The placeholders for priority and category must be replaced with actual enum values supported by the backend.

Success: HTTP `201 Created`, returning a `TicketResponse`.

The backend determines `createdBy` from the authenticated user. The request must not supply or override ticket ownership.

### 3.2 Retrieve a ticket

`GET /api/v1/tickets/{ticketId}`

Returns a `TicketResponse` if the ticket exists and the authenticated user is authorized to access it.

### 3.3 List tickets for a user

`GET /api/v1/tickets?createdBy={userId}`

Returns a list of `TicketResponse` objects.

- Employees may list only their own tickets.
- Agents and admins may list tickets for any user, subject to existing backend rules.
- The user ID in the query parameter is a filter, not proof of identity.

The current API requires `createdBy`. A dedicated current-user listing endpoint is not part of this milestone.

### 3.4 Ticket response

The existing `TicketResponse` contains:

- `ticketId`
- `ticketNumber`
- `title`
- `description`
- `status`
- `priority`
- `category`
- `createdBy`
- `assignedTo`
- `assignedTeam`
- `createdAt`
- `updatedAt`
- `resolvedAt`
- `closedAt`

Nullable assignment and lifecycle timestamp fields may be absent in value or represented as `null`, depending on JSON serialization.

The voice agent should report ticket identifiers and statuses returned by the backend, not invent them.

## 4. Ticket lifecycle and assignment

Existing endpoints:

| Method | Endpoint | Intended operation |
|---|---|---|
| POST | `/api/v1/tickets/{ticketId}/start` | Start work |
| POST | `/api/v1/tickets/{ticketId}/pending` | Mark pending |
| POST | `/api/v1/tickets/{ticketId}/resolve` | Resolve |
| POST | `/api/v1/tickets/{ticketId}/close` | Close |
| POST | `/api/v1/tickets/{ticketId}/cancel` | Cancel |
| POST | `/api/v1/tickets/{ticketId}/assign` | Assign to an agent |

Assignment request body:

    {
      "assignedTo": "<agent UUID>"
    }

Authorization policy:

- Employees may cancel their own tickets but cannot perform agent-only lifecycle or assignment operations.
- Agents may access and operate on permitted tickets regardless of assignee.
- Admins have broad ticket access and permitted agent operations.
- Assignment represents workflow responsibility; it is not an access-control boundary.

The backend remains the final authority on allowed status transitions, assignment targets, ownership, and permissions. The agent must not assume an operation succeeded until it receives a successful API response.

## 5. Comments and conversations

Base path: `/api/v1/tickets/{ticketId}/comments`

### Retrieve comments

`GET /api/v1/tickets/{ticketId}/comments`

Returns comments in chronological order.

### Add a comment

`POST /api/v1/tickets/{ticketId}/comments`

Request body:

    {
      "content": "The user reports that the VPN still fails after restarting the client."
    }

`content` must be non-blank and at most 5000 characters.

The backend determines the comment author from the authenticated user. The agent must not supply an arbitrary author ID.

Comments belong to a ticket and follow that ticket's existing resource-access rules.

## 6. Authorization principles

- Employees can access and cancel only their own tickets.
- Agents can access all tickets regardless of assignment, subject to permitted operations.
- Admins have broad access and permitted agent operations.
- Role checks and resource-access checks are complementary.
- A successful login does not grant unrestricted access to every endpoint.
- The voice agent must not bypass backend authorization or make authorization decisions based only on spoken claims.
- The backend's current authorization policy takes precedence over any assumptions made by the agent.

## 7. Error handling

The integration should handle these HTTP outcomes where applicable:

| Status | Meaning |
|---|---|
| `400 Bad Request` | Invalid request or validation failure |
| `401 Unauthorized` | Missing, invalid, or expired authentication |
| `403 Forbidden` | Authenticated user lacks permission |
| `404 Not Found` | Requested resource does not exist |
| `409 Conflict` | Operation conflicts with the current state |

The agent should explain failures clearly, avoid claiming success after a failed request, and avoid exposing internal exception details or credentials.

Exact error response bodies must follow the backend's existing exception-handling implementation.

## 8. Initial voice-agent workflows

### Create a ticket
1. Collect the issue description and required ticket fields.
2. Clarify missing or ambiguous information.
3. Confirm the details with the user when appropriate.
4. Call the ticket creation endpoint with the authenticated user's token.
5. Report the returned ticket number and status.

### Check a ticket
1. Identify the requested ticket.
2. Call the ticket retrieval endpoint using the authenticated token.
3. Report only the information returned by the backend and permitted for that user.

### Continue a ticket conversation
1. Identify the ticket.
2. Retrieve comments if needed.
3. Submit a comment through the comments endpoint.
4. Confirm success only after the backend responds successfully.

### Perform a ticket action
1. Identify the requested action and ticket.
2. Call the existing endpoint.
3. Let backend authorization and lifecycle validation determine whether it is allowed.
4. Report the actual result.

## 9. Explicitly out of scope

This milestone does not introduce:
- A separate voice-agent user or service account.
- A new voice-specific ticket API.
- A new authentication or refresh-token system.
- Automatic ticket assignment.
- Changes to existing role or resource-access policy.
- The Python speech-recognition, language-model, or text-to-speech implementation.

## 10. Implementation follow-ups

Before the frontend and Python agent integration is considered complete:

- Verify the supported `TicketPriority` and `TicketCategory` enum values.
- Verify the exact login request and error response schemas.
- Decide how the frontend securely provides the user's token to the voice-agent runtime.
- Define behavior when the token expires during a conversation.
- Add integration tests for authenticated API calls and authorization failures.
- Keep this document aligned with the actual backend contract as APIs evolve.

---

## Document: README.md

# Voxera

Voxera is an AI-assisted IT helpdesk and ticket management system.

The backend is being built with Java and Spring Boot, with the longer-term goal of integrating a voice agent that can interact with the ticketing system through well-defined backend APIs.

## Current Stack

- Java 21
- Spring Boot 4.1.1
- Maven
- Spring Data JPA / Hibernate
- H2 for development/testing
- PostgreSQL for production
- Flyway for database migrations
- Spring Security
- JWT authentication
- BCrypt password hashing

## Current Backend Progress

The core ticketing backend is implemented:

- Project configuration and Maven setup
- Database schema and Flyway migrations
- User and ticket domain models
- Repositories and service layer
- REST APIs for tickets
- Global exception handling
- Domain and service-layer tests
- Integration tests
- User authentication
- BCrypt password verification
- JWT generation and validation
- Stateless Spring Security
- JWT authentication filter
- Authenticated access to protected ticket APIs

## Current API Areas

### Authentication

`POST /api/v1/auth/login`

Authenticates a user and returns an access token.

### Tickets

`/api/v1/tickets`

Supports the current ticket operations including:

- Create ticket
- Retrieve ticket
- Retrieve tickets for a user
- Assign ticket
- Start ticket
- Move ticket to pending
- Resolve ticket
- Close ticket

Protected ticket endpoints require a valid JWT.

## Development Status

The backend is currently completing its authentication and authorization layer.

Next major areas:

1. Role-based authorization
2. Ticket ownership/access rules
3. Authorization integration tests
4. Ticket lifecycle and domain rules
5. Assignment and agent workflow
6. Comments and conversations
7. Voice-agent abstraction and integration

The frontend and voice-agent components will be developed after the backend foundations are sufficiently mature.