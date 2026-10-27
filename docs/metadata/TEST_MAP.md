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
