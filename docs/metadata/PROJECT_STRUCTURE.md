# Project Structure and Java Inventory

> Generated: 2026-11-24T09:34:54.897085+00:00
> Git commit: `ca3c4a5`
> Generator: `scripts/generate_metadata.py`


## Source and test files

- `backend/src/main/java/com/voxera/backend/BackendApplication.java`
- `backend/src/main/java/com/voxera/backend/admin/controller/AdminSettingsController.java`
- `backend/src/main/java/com/voxera/backend/admin/dto/UpdateWorkspaceSettingsRequest.java`
- `backend/src/main/java/com/voxera/backend/admin/dto/WorkspaceSettingsResponse.java`
- `backend/src/main/java/com/voxera/backend/admin/entity/WorkspaceSettings.java`
- `backend/src/main/java/com/voxera/backend/admin/repository/WorkspaceSettingsRepository.java`
- `backend/src/main/java/com/voxera/backend/admin/service/WorkspaceSettingsService.java`
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
- `backend/src/main/java/com/voxera/backend/ticket/controller/TicketCategoryController.java`
- `backend/src/main/java/com/voxera/backend/ticket/controller/TicketCommentController.java`
- `backend/src/main/java/com/voxera/backend/ticket/controller/TicketController.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/AssignTicketRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCategoryRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCommentRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/RenameTicketCategoryRequest.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/TicketCategoryResponse.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/TicketCommentResponse.java`
- `backend/src/main/java/com/voxera/backend/ticket/dto/TicketResponse.java`
- `backend/src/main/java/com/voxera/backend/ticket/entity/Ticket.java`
- `backend/src/main/java/com/voxera/backend/ticket/entity/TicketCategory.java`
- `backend/src/main/java/com/voxera/backend/ticket/entity/TicketComment.java`
- `backend/src/main/java/com/voxera/backend/ticket/entity/TicketHistory.java`
- `backend/src/main/java/com/voxera/backend/ticket/enums/TicketCategory.java`
- `backend/src/main/java/com/voxera/backend/ticket/enums/TicketHistoryAction.java`
- `backend/src/main/java/com/voxera/backend/ticket/enums/TicketPriority.java`
- `backend/src/main/java/com/voxera/backend/ticket/enums/TicketStatus.java`
- `backend/src/main/java/com/voxera/backend/ticket/repository/TicketCategoryRepository.java`
- `backend/src/main/java/com/voxera/backend/ticket/repository/TicketCommentRepository.java`
- `backend/src/main/java/com/voxera/backend/ticket/repository/TicketHistoryRepository.java`
- `backend/src/main/java/com/voxera/backend/ticket/repository/TicketRepository.java`
- `backend/src/main/java/com/voxera/backend/ticket/service/TicketAuthorizationService.java`
- `backend/src/main/java/com/voxera/backend/ticket/service/TicketCategoryService.java`
- `backend/src/main/java/com/voxera/backend/ticket/service/TicketCommentService.java`
- `backend/src/main/java/com/voxera/backend/ticket/service/TicketHistoryService.java`
- `backend/src/main/java/com/voxera/backend/ticket/service/TicketService.java`
- `backend/src/main/java/com/voxera/backend/user/controller/AdminUserController.java`
- `backend/src/main/java/com/voxera/backend/user/dto/UpdateUserRoleRequest.java`
- `backend/src/main/java/com/voxera/backend/user/dto/UpdateUserStatusRequest.java`
- `backend/src/main/java/com/voxera/backend/user/dto/UserManagementResponse.java`
- `backend/src/main/java/com/voxera/backend/user/entity/User.java`
- `backend/src/main/java/com/voxera/backend/user/entity/UserCredential.java`
- `backend/src/main/java/com/voxera/backend/user/enums/UserRole.java`
- `backend/src/main/java/com/voxera/backend/user/enums/UserStatus.java`
- `backend/src/main/java/com/voxera/backend/user/repository/UserCredentialRepository.java`
- `backend/src/main/java/com/voxera/backend/user/repository/UserRepository.java`
- `backend/src/main/java/com/voxera/backend/user/service/UserService.java`
- `backend/src/test/java/com/voxera/backend/BackendApplicationTests.java`
- `backend/src/test/java/com/voxera/backend/admin/controller/AdminSettingsControllerIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/security/CurrentUserServiceTest.java`
- `backend/src/test/java/com/voxera/backend/security/authentication/AuthControllerIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/security/authentication/AuthenticationServiceIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/security/authentication/AuthenticationServiceTest.java`
- `backend/src/test/java/com/voxera/backend/security/authentication/BCryptPasswordServiceTest.java`
- `backend/src/test/java/com/voxera/backend/security/jwt/JwtServiceTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/AbstractTicketControllerIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAssignmentIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketAuthorizationIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCategoryControllerIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCommentIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCreationIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketLifecycleIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/controller/TicketRetrievalIntegrationTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/entity/TicketCategoryTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/entity/TicketTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/service/TicketCategoryServiceTest.java`
- `backend/src/test/java/com/voxera/backend/ticket/service/TicketServiceTest.java`
- `backend/src/test/java/com/voxera/backend/user/controller/AdminUserControllerIntegrationTest.java`

## Java classes and method signatures


### `BackendApplication`
- File: `backend/src/main/java/com/voxera/backend/BackendApplication.java`
- Package: `com.voxera.backend`
- `void main(String[] args)`

### `AdminSettingsController`
- File: `backend/src/main/java/com/voxera/backend/admin/controller/AdminSettingsController.java`
- Package: `com.voxera.backend.admin.controller`
- `AdminSettingsController(WorkspaceSettingsService settingsService)`
- `WorkspaceSettingsResponse getSettings()`
- `WorkspaceSettingsResponse updateSettings(@Valid @RequestBody UpdateWorkspaceSettingsRequest request)`

### `UpdateWorkspaceSettingsRequest`
- File: `backend/src/main/java/com/voxera/backend/admin/dto/UpdateWorkspaceSettingsRequest.java`
- Package: `com.voxera.backend.admin.dto`
- `boolean hasUpdates()`

### `WorkspaceSettingsResponse`
- File: `backend/src/main/java/com/voxera/backend/admin/dto/WorkspaceSettingsResponse.java`
- Package: `com.voxera.backend.admin.dto`
- `WorkspaceSettingsResponse(String workspaceName, String organization, String timezone, String ticketPrefix, TicketPriority defaultPriority, boolean requireAssignment, boolean allowReopening, LocalDateTime updatedAt)`
- `WorkspaceSettingsResponse from(WorkspaceSettings settings)`

### `WorkspaceSettings`
- File: `backend/src/main/java/com/voxera/backend/admin/entity/WorkspaceSettings.java`
- Package: `com.voxera.backend.admin.entity`
- `WorkspaceSettings()`
- `Integer getId()`
- `String getWorkspaceName()`
- `String getOrganization()`
- `String getTimezone()`
- `String getTicketPrefix()`
- `TicketPriority getDefaultPriority()`
- `boolean isRequireAssignment()`
- `boolean isAllowReopening()`
- `LocalDateTime getUpdatedAt()`
- `void update(String workspaceName, String organization, String timezone, String ticketPrefix, TicketPriority defaultPriority)`
- `void update(String workspaceName, String organization, String timezone, String ticketPrefix, TicketPriority defaultPriority, boolean requireAssignment, boolean allowReopening)`

### `WorkspaceSettingsRepository`
- File: `backend/src/main/java/com/voxera/backend/admin/repository/WorkspaceSettingsRepository.java`
- Package: `com.voxera.backend.admin.repository`
- No method signatures extracted.

### `WorkspaceSettingsService`
- File: `backend/src/main/java/com/voxera/backend/admin/service/WorkspaceSettingsService.java`
- Package: `com.voxera.backend.admin.service`
- `WorkspaceSettingsService(WorkspaceSettingsRepository settingsRepository)`
- `WorkspaceSettings getSettings()`
- `WorkspaceSettings updateSettings(UpdateWorkspaceSettingsRequest request)`
- ` if(!request.hasUpdates())`
- `String getTicketPrefix()`
- `TicketPriority getDefaultPriority()`
- `void validateTimezone(String timezone)`

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
- `CorsConfigurationSource corsConfigurationSource()`

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

### `TicketCategoryController`
- File: `backend/src/main/java/com/voxera/backend/ticket/controller/TicketCategoryController.java`
- Package: `com.voxera.backend.ticket.controller`
- `TicketCategoryController(TicketCategoryService categoryService)`
- `List<TicketCategoryResponse> getAllCategories()`
- `List<TicketCategoryResponse> getActiveCategories()`
- `ResponseEntity<TicketCategoryResponse> createCategory(@Valid @RequestBody CreateTicketCategoryRequest request)`
- `TicketCategoryResponse renameCategory(@PathVariable UUID categoryId, @Valid @RequestBody RenameTicketCategoryRequest request)`
- `TicketCategoryResponse deactivateCategory(@PathVariable UUID categoryId)`
- `TicketCategoryResponse activateCategory(@PathVariable UUID categoryId)`

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
- `List<TicketResponse> getTickets(@RequestParam(required = false) UUID createdBy)`
- `TicketResponse startProgress(@PathVariable UUID ticketId)`
- `TicketResponse markPending(@PathVariable UUID ticketId)`
- `TicketResponse resolveTicket(@PathVariable UUID ticketId)`
- `TicketResponse closeTicket(@PathVariable UUID ticketId)`
- `TicketResponse reopenTicket(@PathVariable UUID ticketId)`
- `TicketResponse cancelTicket(@PathVariable UUID ticketId)`
- `ResponseEntity<TicketResponse> assignTicket(@PathVariable UUID ticketId, @Valid @RequestBody AssignTicketRequest request)`

### `AssignTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/AssignTicketRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `AssignTicketRequest(@NotNull UUID assignedTo)`

### `CreateTicketCategoryRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCategoryRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `CreateTicketCategoryRequest(@NotBlank(message = "Category name cannot be blank") @Size(max = 100, message = "Category name cannot exceed 100 characters") String name)`

### `CreateTicketCommentRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCommentRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `CreateTicketCommentRequest(@NotBlank @Size(max = 5000) String content)`

### `CreateTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `CreateTicketRequest(@NotBlank String title, @NotBlank String description, TicketPriority priority, @NotNull UUID category)`

### `RenameTicketCategoryRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/RenameTicketCategoryRequest.java`
- Package: `com.voxera.backend.ticket.dto`
- `RenameTicketCategoryRequest(@NotBlank(message = "Category name cannot be blank") @Size(max = 100, message = "Category name cannot exceed 100 characters") String name)`

### `TicketCategoryResponse`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/TicketCategoryResponse.java`
- Package: `com.voxera.backend.ticket.dto`
- `TicketCategoryResponse(UUID categoryId, String name, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt)`
- `TicketCategoryResponse from(TicketCategory category)`

### `TicketCommentResponse`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/TicketCommentResponse.java`
- Package: `com.voxera.backend.ticket.dto`
- `TicketCommentResponse(UUID commentId, UUID ticketId, UUID authorId, String authorName, String content, LocalDateTime createdAt, LocalDateTime updatedAt)`
- `TicketCommentResponse from(TicketComment comment)`

### `TicketResponse`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/TicketResponse.java`
- Package: `com.voxera.backend.ticket.dto`
- `TicketResponse(UUID ticketId, String ticketNumber, String title, String description, TicketStatus status, TicketPriority priority, UUID categoryId, String category, UUID createdBy, UUID assignedTo, UUID assignedTeam, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime resolvedAt, LocalDateTime closedAt)`
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
- `void reopen()`
- ` if(status != TicketStatus.RESOLVED && status != TicketStatus.CLOSED)`
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

### `TicketCategory`
- File: `backend/src/main/java/com/voxera/backend/ticket/entity/TicketCategory.java`
- Package: `com.voxera.backend.ticket.entity`
- `TicketCategory()`
- `TicketCategory(UUID categoryId, String name, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt)`
- `void onCreate()`
- ` if(createdAt == null)`
- ` if(updatedAt == null)`
- `void onUpdate()`
- `String nameKeyFor(String name)`
- `String normalizeDisplayName(String name)`
- ` if(name == null)`
- ` if(normalizedName.isEmpty())`
- ` if(normalizedName.length() > MAX_NAME_LENGTH)`
- `String normalizeNameKey(String name)`
- `void rename(String newName)`
- `void deactivate()`
- `void activate()`
- `UUID getCategoryId()`
- `String getName()`
- `boolean isActive()`
- `LocalDateTime getCreatedAt()`
- `LocalDateTime getUpdatedAt()`

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
- `TicketHistory(Ticket ticket, User actor, String action, String oldValue, String newValue)`
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

### `TicketHistoryAction`
- File: `backend/src/main/java/com/voxera/backend/ticket/enums/TicketHistoryAction.java`
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

### `TicketCategoryRepository`
- File: `backend/src/main/java/com/voxera/backend/ticket/repository/TicketCategoryRepository.java`
- Package: `com.voxera.backend.ticket.repository`
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

### `TicketCategoryService`
- File: `backend/src/main/java/com/voxera/backend/ticket/service/TicketCategoryService.java`
- Package: `com.voxera.backend.ticket.service`
- `TicketCategoryService(TicketCategoryRepository categoryRepository)`
- `List<TicketCategory> getAllCategories()`
- `List<TicketCategory> getActiveCategories()`
- `TicketCategory createCategory(String name)`
- `TicketCategory renameCategory(UUID categoryId, String newName)`
- `TicketCategory deactivateCategory(UUID categoryId)`
- `TicketCategory activateCategory(UUID categoryId)`
- `TicketCategory findCategory(UUID categoryId)`
- `void ensureNameKeyAvailable(String nameKey, UUID currentCategoryId)`
- ` if(!existingCategory.getCategoryId().equals(currentCategoryId))`

### `TicketCommentService`
- File: `backend/src/main/java/com/voxera/backend/ticket/service/TicketCommentService.java`
- Package: `com.voxera.backend.ticket.service`
- `TicketCommentService(TicketCommentRepository ticketCommentRepository, TicketService ticketService, CurrentUserService currentUserService)`
- `TicketCommentResponse addComment(UUID ticketId, String content)`
- `List<TicketCommentResponse> getComments(UUID ticketId)`

### `TicketHistoryService`
- File: `backend/src/main/java/com/voxera/backend/ticket/service/TicketHistoryService.java`
- Package: `com.voxera.backend.ticket.service`
- `TicketHistoryService(TicketHistoryRepository ticketHistoryRepository)`
- `void record(Ticket ticket, User actor, TicketHistoryAction action, String oldValue, String newValue)`

### `TicketService`
- File: `backend/src/main/java/com/voxera/backend/ticket/service/TicketService.java`
- Package: `com.voxera.backend.ticket.service`
- `TicketService(TicketRepository ticketRepository, TicketCategoryRepository ticketCategoryRepository, TicketHistoryService ticketHistoryService, UserRepository userRepository, CurrentUserService currentUserService, TicketAuthorizationService ticketAuthorizationService, WorkspaceSettingsService workspaceSettingsService)`
- `Ticket createTicket(String title, String description, TicketPriority priority, UUID categoryId)`
- ` if(!category.isActive())`
- `Ticket getTicket(UUID ticketId)`
- `Ticket getTicket(UUID ticketId, User actor)`
- `List<Ticket> getTickets(UUID createdBy)`
- ` if(createdBy != null)`
- ` if(actor.getRole() == UserRole.EMPLOYEE)`
- `Ticket startProgress(UUID ticketId)`
- ` if(workspaceSettingsService.getSettings().isRequireAssignment() && ticket.getAssignedTo() == null)`
- `Ticket markPending(UUID ticketId)`
- `Ticket resolveTicket(UUID ticketId)`
- `Ticket closeTicket(UUID ticketId)`
- `Ticket reopenTicket(UUID ticketId)`
- ` if(!workspaceSettingsService.getSettings().isAllowReopening())`
- `Ticket cancelTicket(UUID ticketId)`
- `Ticket assignTicket(UUID ticketId, UUID userId)`
- `String generateTicketNumber()`

### `AdminUserController`
- File: `backend/src/main/java/com/voxera/backend/user/controller/AdminUserController.java`
- Package: `com.voxera.backend.user.controller`
- `AdminUserController(UserService userService)`
- `List<UserManagementResponse> getUsers(@RequestParam(required = false) String search, @RequestParam(required = false) UserRole role, @RequestParam(required = false) UserStatus status)`
- `ResponseEntity<UserManagementResponse> updateUserRole(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRoleRequest request)`
- `ResponseEntity<UserManagementResponse> updateUserStatus(@PathVariable UUID userId, @Valid @RequestBody UpdateUserStatusRequest request)`

### `UpdateUserRoleRequest`
- File: `backend/src/main/java/com/voxera/backend/user/dto/UpdateUserRoleRequest.java`
- Package: `com.voxera.backend.user.dto`
- `UpdateUserRoleRequest(@NotNull(message = "Role is required") UserRole role)`

### `UpdateUserStatusRequest`
- File: `backend/src/main/java/com/voxera/backend/user/dto/UpdateUserStatusRequest.java`
- Package: `com.voxera.backend.user.dto`
- `UpdateUserStatusRequest(@NotNull(message = "Status is required") UserStatus status)`

### `UserManagementResponse`
- File: `backend/src/main/java/com/voxera/backend/user/dto/UserManagementResponse.java`
- Package: `com.voxera.backend.user.dto`
- `UserManagementResponse(UUID id, String name, String email, String initials, String department, UserRole role, UserStatus status, String joined)`
- `UserManagementResponse from(User user)`
- `String getInitials(String name)`
- ` if(name == null || name.isBlank())`
- ` if(parts.length == 1)`

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
- `void changeRole(UserRole newRole)`
- `void changeStatus(UserStatus newStatus)`

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
- `UserService(UserRepository userRepository, CurrentUserService currentUserService)`
- `User getUser(UUID userId)`
- `User getUserByEmployeeId(String employeeId)`
- `List<User> getUsers(String search, UserRole role, UserStatus status)`
- `User changeUserRole(UUID userId, UserRole newRole)`
- ` if(target.getRole() == UserRole.ADMIN && target.getStatus() == UserStatus.ACTIVE && newRole != UserRole.ADMIN)`
- ` if(target.getRole() != newRole)`
- `User changeUserStatus(UUID userId, UserStatus newStatus)`
- ` if(target.getRole() == UserRole.ADMIN && target.getStatus() == UserStatus.ACTIVE && newStatus == UserStatus.INACTIVE)`
- ` if(target.getStatus() != newStatus)`
- `void lockActiveAdministrators()`
- `void validateAdministrator(User actor)`
- ` if(actor.getRole() != UserRole.ADMIN || actor.getStatus() != UserStatus.ACTIVE)`
- `void preventSelfModification(User actor, User target)`
- ` if(actor.getUserId().equals(target.getUserId()))`
- `void ensureAnotherActiveAdministratorExists(User target)`
- ` if(otherActiveAdministrators == 0)`
- `boolean contains(String value, String search)`

### `BackendApplicationTests`
- File: `backend/src/test/java/com/voxera/backend/BackendApplicationTests.java`
- Package: `com.voxera.backend`
- `void contextLoads()`

### `AdminSettingsControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/admin/controller/AdminSettingsControllerIntegrationTest.java`
- Package: `com.voxera.backend.admin.controller`
- `void setUp()`
- `void getSettings_adminShouldReturnPersistedDefaults()`
- `void getSettings_agentShouldReceiveForbidden()`
- `void getSettings_employeeShouldReceiveForbidden()`
- `void updateSettings_adminShouldUpdateOnlyProvidedFields()`
- `void updateSettings_shouldPersistChanges()`
- `void updateSettings_agentShouldReceiveForbidden()`
- `void updateSettings_employeeShouldReceiveForbidden()`
- `void updateSettings_shouldRejectInvalidTimezone()`
- `void updateSettings_shouldRejectInvalidPrefix()`
- `void updateSettings_shouldRejectEmptyRequest()`
- `void updatedTicketPrefixShouldBeUsedWhenCreatingTickets()`
- `void updateSettings_shouldUpdateDefaultPriority()`
- `void updateSettings_shouldRejectInvalidDefaultPriority()`
- `void updateSettings_shouldPreserveDefaultPriorityWhenOmitted()`
- `void adminCanUpdateWorkflowSettings()`
- `void partialWorkflowSettingsUpdateShouldPreserveOmittedValues()`
- `User createUser(String name, String email, UserRole role)`
- `String login(User user)`
- `String bearer(String token)`
- `MockHttpServletRequestBuilder authenticated(MockHttpServletRequestBuilder request, String token)`

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

### `TicketCategoryControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCategoryControllerIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `String createAdminToken()`
- `String uniqueCategoryName()`
- `String categoryRequest(String name)`
- `void unauthenticatedUserCannotRetrieveActiveCategories()`
- `void employeeCanRetrieveActiveCategories()`
- `void agentCanRetrieveActiveCategories()`
- `void adminCanRetrieveActiveCategories()`
- `void onlyAdminCanRetrieveAllCategories()`
- `void unauthenticatedUserCannotRetrieveAllCategories()`
- `void adminCanCreateCategory()`
- `void employeeCannotCreateCategory()`
- `void createCategoryRejectsBlankName()`
- `void createCategoryRejectsNameLongerThan100Characters()`
- `void createCategoryRejectsDuplicateNameIgnoringCase()`
- `void adminCanRenameCategory()`
- `void adminCanDeactivateAndReactivateCategory()`
- `void renamingCategoryRejectsDuplicateName()`
- `void operationsReturnNotFoundForUnknownCategory()`

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
- `void createTicket_shouldUseWorkspaceDefaultWhenPriorityIsOmitted()`
- `void createTicket_shouldUseExplicitPriorityInsteadOfWorkspaceDefault()`
- `void createTicket_shouldRejectMissingCategoryEvenWhenPriorityIsOmitted()`

### `TicketLifecycleIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketLifecycleIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void ticketLifecycle_shouldPersistStateTransitions()`
- `void employeeCanCancelOwnTicket()`
- `void employeeCannotCancelAnotherEmployeesTicket()`
- `void employeeCannotPerformAgentOnlyLifecycleActions()`
- ` for(String action : actions)`
- `void reopeningShouldBeRejectedWhenWorkspaceDisablesIt()`
- `void agentCanReopenResolvedTicketWhenWorkspaceAllowsIt()`

### `TicketRetrievalIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketRetrievalIntegrationTest.java`
- Package: `com.voxera.backend.ticket.controller`
- `void getTicket_shouldReturnExistingTicket()`
- `void getTicket_shouldReturn404ForUnknownTicket()`
- `void getTicketsForUser_shouldReturnTicketsForRequestedUser()`
- `void getTicketsWithoutFilter_employeeShouldReturnOwnTickets()`
- `void getTicketsWithoutFilter_agentShouldReturnAllTickets()`
- `void getTicketsWithoutFilter_adminShouldReturnAllTickets()`
- `void getTickets_employeeShouldNotAccessAnotherUsersTickets()`

### `TicketCategoryTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/entity/TicketCategoryTest.java`
- Package: `com.voxera.backend.ticket.entity`
- `TicketCategory createCategory(String name)`
- `void categoryShouldPreserveDisplayName()`
- `void categoryNameShouldBeTrimmed()`
- `void constructorShouldRejectNullName()`
- `void constructorShouldRejectBlankName()`
- `void constructorShouldRejectNameLongerThan100Characters()`
- `void constructorShouldAcceptNameOfExactly100Characters()`
- `void renameShouldTrimNewName()`
- `void renameShouldRejectNullName()`
- `void renameShouldRejectBlankName()`
- `void renameShouldRejectNameLongerThan100Characters()`
- `void categoryShouldSupportDeactivationAndReactivation()`

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
- `void resolvedTicketShouldBeReopened()`
- `void closedTicketShouldBeReopened()`
- `void cancelledTicketShouldNotBeReopened()`
- `void activeTicketStatesShouldNotBeReopened()`

### `TicketCategoryServiceTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/service/TicketCategoryServiceTest.java`
- Package: `com.voxera.backend.ticket.service`
- `void setUp()`
- `void getAllCategoriesReturnsAllCategories()`
- `void getActiveCategoriesReturnsOnlyActiveCategories()`
- `void createCategorySavesNewCategory()`
- `void createCategoryRejectsDuplicateNameIgnoringCase()`
- `void renameCategoryUpdatesName()`
- `void renameCategoryAllowsItsExistingName()`
- `void renameCategoryRejectsNameUsedByAnotherCategory()`
- `void deactivateCategoryChangesActiveState()`
- `void activateCategoryChangesActiveState()`
- `void operationsFailWhenCategoryDoesNotExist()`

### `TicketServiceTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/service/TicketServiceTest.java`
- Package: `com.voxera.backend.ticket.service`
- `void setUp()`
- `void createTicketShouldSaveTicketForExistingUser()`
- `void createTicketShouldGenerateTicketNumber()`
- `void getTicketShouldReturnExistingTicket()`
- `void getTicketShouldFailWhenTicketDoesNotExist()`
- `void startProgressShouldChangeTicketStatusWhenAssignmentIsNotRequired()`
- `void startProgressShouldFailWhenAssignmentIsRequiredAndTicketIsUnassigned()`
- `void markPendingShouldChangeTicketStatus()`
- `void resolveTicketShouldChangeTicketStatus()`
- `void closeTicketShouldChangeTicketStatus()`
- `void cancelTicketShouldChangeTicketStatus()`
- `void lifecycleOperationShouldFailWhenTicketDoesNotExist()`
- `void assignTicketShouldAssignExistingUser()`
- `void assignTicketShouldFailWhenAssigneeDoesNotExist()`
- `void assignTicketShouldFailWhenTicketDoesNotExist()`
- `void createTicketShouldUseWorkspaceDefaultPriorityWhenOmitted()`
- `void createTicketShouldFailWhenCategoryDoesNotExist()`
- `void createTicketShouldFailWhenCategoryIsInactive()`

### `AdminUserControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/user/controller/AdminUserControllerIntegrationTest.java`
- Package: `com.voxera.backend.user.controller`
- `void setUp()`
- `void getUsers_adminShouldReturnRealDatabaseUsers()`
- `void getUsers_agentShouldReceiveForbidden()`
- `void getUsers_employeeShouldReceiveForbidden()`
- `void getUsers_shouldSearchByName()`
- `void getUsers_shouldSearchByEmail()`
- `void getUsers_shouldSearchByEmployeeId()`
- `void getUsers_shouldSearchByDepartment()`
- `void getUsers_shouldFilterByRole()`
- `void getUsers_shouldFilterByStatus()`
- `void getUsers_shouldApplyCombinedFilters()`
- `void getUsers_shouldReturnExpectedResponseFields()`
- `void getUsers_invalidRoleShouldReturnBadRequest()`
- `void getUsers_invalidStatusShouldReturnBadRequest()`
- `void updateUserRole_shouldApplyNewRoleToExistingToken()`
- `void updateUserStatus_deactivationShouldRejectExistingToken()`
- `void updateUserStatus_adminShouldReactivateUser()`
- `void updateUserRole_adminCanDemoteAnotherAdmin()`
- `void updateUserRole_adminCannotChangeOwnRole()`
- `void updateUserStatus_adminCannotDeactivateOwnAccount()`
- `void updateUserRole_agentShouldReceiveForbidden()`
- `void updateUserStatus_employeeShouldReceiveForbidden()`
- `void updateUserRole_missingRoleShouldReturnBadRequest()`
- `void updateUserRole_invalidRoleShouldReturnBadRequest()`
- `void updateUserStatus_missingStatusShouldReturnBadRequest()`
- `void updateUserStatus_invalidStatusShouldReturnBadRequest()`
- `void updateUserRole_nonexistentUserShouldReturnNotFound()`
- `void assertSearchResult(String search, User expected)`
- `User createUser(String name, String email, String department, UserRole role, UserStatus status)`
- ` if(status == UserStatus.ACTIVE)`
- `String login(User user)`
- `String bearer(String token)`
