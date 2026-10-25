# Domain Model, DTOs, and Enums

> Generated: 2026-10-25T15:16:43.208559+00:00
> Git commit: `36ca442`
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

## `CreateTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
- Kind: DTO / request / response
- No fields/components extracted.

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
