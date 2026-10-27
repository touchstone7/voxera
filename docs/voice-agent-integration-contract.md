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
