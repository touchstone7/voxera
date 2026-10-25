# Project Structure and Java Inventory

> Generated: 2026-10-25T15:16:43.072428+00:00
> Git commit: `36ca442`
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
- `backend/src/main/java/com/voxera/backend/ticket/controller/TicketController.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/AssignTicketRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
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

### `CreateTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `CreateTicketRequest(@NotBlank String title, @NotBlank String description, @NotNull TicketPriority priority, @NotNull TicketCategory category)`

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

### `TicketAuthorizationIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAuthorizationIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void ticketEndpoint_shouldRejectUnauthenticatedRequest()`
- `void employeeCannotReadAnotherEmployeesTicket()`

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
- `void openTicketCannotBeResolvedDirectly()`
- `void openTicketCannotBeClosed()`

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
