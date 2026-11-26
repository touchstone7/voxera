# Voxera — Consolidated Project Snapshot

> Generated: 2026-11-24 15:04:55 IST
> Git commit: `ca3c4a5`
> Git branch: `main`
> Git working tree: `M VOXERA_METADATA.md
 M docs/metadata/API_CONTRACTS.md
 M docs/metadata/DOMAIN_MODEL.md
 M docs/metadata/PROJECT_STATUS.md
 M docs/metadata/PROJECT_STRUCTURE.md
 M docs/metadata/SECURITY_AND_RULES.md
 M docs/metadata/TEST_MAP.md
?? VOXERA_SNAPSHOT_2026-11-24_14-55-40_IST.md`
> Source: Six individual documents in `docs/metadata/`

---

## Table of Contents

1. [Project Structure](#project-structure)
2. [API Contracts](#api-contracts)
3. [Domain Model](#domain-model)
4. [Security and Business Rules](#security-and-business-rules)
5. [Test Map](#test-map)
6. [Project Status](#project-status)

---

# Project Structure

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

---

# API Contracts

# API Contracts

> Generated: 2026-11-24T09:34:55.197016+00:00
> Git commit: `ca3c4a5`
> Generator: `scripts/generate_metadata.py`


> Extracted from Spring mapping annotations. Verify request/response semantics and status codes against controller code and tests.


## `AdminSettingsController`
- File: `backend/src/main/java/com/voxera/backend/admin/controller/AdminSettingsController.java`
- Class-level path: `/api/v1/admin/settings`
- **GET `/api/v1/admin/settings`** — `getSettings()`
  - Mapping: `@GetMapping`
- **PATCH `/api/v1/admin/settings`** — `updateSettings(@Valid @RequestBody UpdateWorkspaceSettingsRequest request)`
  - Mapping: `@PatchMapping`

## `GlobalExceptionHandler`
- File: `backend/src/main/java/com/voxera/backend/exception/GlobalExceptionHandler.java`
- Class-level path: `(none detected)`

## `AuthController`
- File: `backend/src/main/java/com/voxera/backend/security/authentication/AuthController.java`
- Class-level path: `/api/v1/auth`
- **POST `/api/v1/auth/login`** — `login(@Valid @RequestBody LoginRequest request)`
  - Mapping: `@PostMapping("/login")`

## `TicketCategoryController`
- File: `backend/src/main/java/com/voxera/backend/ticket/controller/TicketCategoryController.java`
- Class-level path: `/api/v1/ticket-categories`

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
- **GET `/api/v1/tickets`** — `getTickets(@RequestParam(required = false)`
  - Mapping: `@GetMapping`
- **POST `/api/v1/tickets/{ticketId}/start`** — `startProgress(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/start")`
- **POST `/api/v1/tickets/{ticketId}/pending`** — `markPending(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/pending")`
- **POST `/api/v1/tickets/{ticketId}/resolve`** — `resolveTicket(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/resolve")`
- **POST `/api/v1/tickets/{ticketId}/close`** — `closeTicket(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/close")`
- **POST `/api/v1/tickets/{ticketId}/reopen`** — `reopenTicket(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/reopen")`
- **POST `/api/v1/tickets/{ticketId}/cancel`** — `cancelTicket(@PathVariable UUID ticketId)`
  - Mapping: `@PostMapping("/{ticketId}/cancel")`
- **POST `/api/v1/tickets/{ticketId}/assign`** — `assignTicket(@PathVariable UUID ticketId, @Valid @RequestBody AssignTicketRequest request)`
  - Mapping: `@PostMapping("/{ticketId}/assign")`

## `AdminUserController`
- File: `backend/src/main/java/com/voxera/backend/user/controller/AdminUserController.java`
- Class-level path: `/api/v1/admin/users`
- **GET `/api/v1/admin/users`** — `getUsers(@RequestParam(required = false)`
  - Mapping: `@GetMapping`
- **PATCH `/api/v1/admin/users/{userId}/role`** — `updateUserRole(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRoleRequest request)`
  - Mapping: `@PatchMapping("/{userId}/role")`
- **PATCH `/api/v1/admin/users/{userId}/status`** — `updateUserStatus(@PathVariable UUID userId, @Valid @RequestBody UpdateUserStatusRequest request)`
  - Mapping: `@PatchMapping("/{userId}/status")`

## Contract verification checklist
- Confirm authentication requirements and role/resource permissions.
- Confirm request DTO fields and validation constraints.
- Confirm response DTO shape and HTTP status codes.
- Confirm error response format using the exception handler.

---

# Domain Model

# Domain Model, DTOs, and Enums

> Generated: 2026-11-24T09:34:55.210872+00:00
> Git commit: `ca3c4a5`
> Generator: `scripts/generate_metadata.py`



## `UpdateWorkspaceSettingsRequest`
- File: `backend/src/main/java/com/voxera/backend/admin/dto/UpdateWorkspaceSettingsRequest.java`
- Kind: DTO / request / response
- Fields/components:
  - `workspaceName: message = "Workspace name must not be blank") String`
  - `organization: message = "Organization must not be blank") String`
  - `timezone: message = "Timezone must not be blank") String`
  - `ticketPrefix: message = "Ticket prefix must contain 2–10 alphanumeric characters") String`
  - `defaultPriority: TicketPriority`
  - `requireAssignment: Boolean`
  - `allowReopening: Boolean`

## `WorkspaceSettingsResponse`
- File: `backend/src/main/java/com/voxera/backend/admin/dto/WorkspaceSettingsResponse.java`
- Kind: DTO / request / response
- Fields/components:
  - `workspaceName: String`
  - `organization: String`
  - `timezone: String`
  - `ticketPrefix: String`
  - `defaultPriority: TicketPriority`
  - `requireAssignment: boolean`
  - `allowReopening: boolean`
  - `updatedAt: LocalDateTime`

## `WorkspaceSettings`
- File: `backend/src/main/java/com/voxera/backend/admin/entity/WorkspaceSettings.java`
- Kind: JPA entity
- Persistence annotation: `@Table(name = "workspace_settings")`
- Persistence annotation: `@Id`
- Persistence annotation: `@Column(name = "settings_id")`
- Persistence annotation: `@Column(name = "workspace_name", nullable = false, length = 150)`
- Persistence annotation: `@Column(nullable = false, length = 150)`
- Persistence annotation: `@Column(nullable = false, length = 100)`
- Persistence annotation: `@Column(name = "ticket_prefix", nullable = false, length = 10)`
- Persistence annotation: `@Enumerated(EnumType.STRING)`
- Persistence annotation: `@Column(name = "default_priority", nullable = false, length = 20)`
- Persistence annotation: `@Column(name = "require_assignment", nullable = false)`
- Persistence annotation: `@Column(name = "allow_reopening", nullable = false)`
- Persistence annotation: `@Column(name = "updated_at", nullable = false)`
- Persistence annotation: `@Version`
- Persistence annotation: `@Column(nullable = false)`
- Fields/components:
  - `id: Integer`
    - Nearby annotations (verify scope): `@Table(name = "workspace_settings")`, `@Id`, `@Column(name = "settings_id")`
  - `workspaceName: String`
    - Nearby annotations (verify scope): `@Id`, `@Column(name = "settings_id")`, `@Column(name = "workspace_name", nullable = false, length = 150)`
  - `organization: String`
    - Nearby annotations (verify scope): `@Column(name = "settings_id")`, `@Column(name = "workspace_name", nullable = false, length = 150)`, `@Column(nullable = false, length = 150)`
  - `timezone: String`
    - Nearby annotations (verify scope): `@Column(name = "workspace_name", nullable = false, length = 150)`, `@Column(nullable = false, length = 150)`, `@Column(nullable = false, length = 100)`
  - `ticketPrefix: String`
    - Nearby annotations (verify scope): `@Column(nullable = false, length = 150)`, `@Column(nullable = false, length = 100)`, `@Column(name = "ticket_prefix", nullable = false, length = 10)`
  - `defaultPriority: TicketPriority`
    - Nearby annotations (verify scope): `@Column(name = "ticket_prefix", nullable = false, length = 10)`, `@Enumerated(EnumType.STRING)`, `@Column(name = "default_priority", nullable = false, length = 20)`
  - `requireAssignment: boolean`
    - Nearby annotations (verify scope): `@Enumerated(EnumType.STRING)`, `@Column(name = "default_priority", nullable = false, length = 20)`, `@Column(name = "require_assignment", nullable = false)`
  - `allowReopening: boolean`
    - Nearby annotations (verify scope): `@Column(name = "default_priority", nullable = false, length = 20)`, `@Column(name = "require_assignment", nullable = false)`, `@Column(name = "allow_reopening", nullable = false)`
  - `updatedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(name = "require_assignment", nullable = false)`, `@Column(name = "allow_reopening", nullable = false)`, `@Column(name = "updated_at", nullable = false)`
  - `version: Long`
    - Nearby annotations (verify scope): `@Column(name = "updated_at", nullable = false)`, `@Version`, `@Column(nullable = false)`

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

## `CreateTicketCategoryRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCategoryRequest.java`
- Kind: DTO / request / response
- Fields/components:
  - `name: message = "Category name cannot exceed 100 characters") String`

## `CreateTicketCommentRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketCommentRequest.java`
- Kind: DTO / request / response
- No fields/components extracted.

## `CreateTicketRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/CreateTicketRequest.java`
- Kind: DTO / request / response
- Fields/components:
  - `priority: TicketPriority`

## `RenameTicketCategoryRequest`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/RenameTicketCategoryRequest.java`
- Kind: DTO / request / response
- Fields/components:
  - `name: message = "Category name cannot exceed 100 characters") String`

## `TicketCategoryResponse`
- File: `backend/src/main/java/com/voxera/backend/ticket/dto/TicketCategoryResponse.java`
- Kind: DTO / request / response
- Fields/components:
  - `categoryId: UUID`
  - `name: String`
  - `active: boolean`
  - `createdAt: LocalDateTime`
  - `updatedAt: LocalDateTime`

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
  - `categoryId: UUID`
  - `category: String`
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
- Persistence annotation: `@ManyToOne(fetch = FetchType.LAZY, optional = false)`
- Persistence annotation: `@JoinColumn(name = "category_id", nullable = false)`
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
    - Nearby annotations (verify scope): `@Column(nullable = false, length = 30)`, `@ManyToOne(fetch = FetchType.LAZY, optional = false)`, `@JoinColumn(name = "category_id", nullable = false)`
  - `createdBy: User`
    - Nearby annotations (verify scope): `@JoinColumn(name = "category_id", nullable = false)`, `@ManyToOne(fetch = FetchType.LAZY, optional = false)`, `@JoinColumn(name = "created_by", nullable = false)`
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

## `TicketCategory`
- File: `backend/src/main/java/com/voxera/backend/ticket/entity/TicketCategory.java`
- Kind: JPA entity
- Persistence annotation: `@Table(name = "ticket_categories", uniqueConstraints = {`
- Persistence annotation: `@Id`
- Persistence annotation: `@Column(name = "category_id")`
- Persistence annotation: `@Column(nullable = false, length = MAX_NAME_LENGTH)`
- Persistence annotation: `@Column(name = "name_key", nullable = false, length = MAX_NAME_LENGTH)`
- Persistence annotation: `@Column(nullable = false)`
- Persistence annotation: `@Column(name = "created_at", nullable = false)`
- Persistence annotation: `@Column(name = "updated_at", nullable = false)`
- Fields/components:
  - `MAX_NAME_LENGTH: int`
    - Nearby annotations (verify scope): `@Entity`, `@Table(name = "ticket_categories", uniqueConstraints = {
        @UniqueConstraint(name = "uk_ticket_categories_name_key", columnNames = "name_key")`
  - `categoryId: UUID`
    - Nearby annotations (verify scope): `@Table(name = "ticket_categories", uniqueConstraints = {
        @UniqueConstraint(name = "uk_ticket_categories_name_key", columnNames = "name_key")`, `@Id`, `@Column(name = "category_id")`
  - `name: String`
    - Nearby annotations (verify scope): `@Id`, `@Column(name = "category_id")`, `@Column(nullable = false, length = MAX_NAME_LENGTH)`
  - `nameKey: String`
    - Nearby annotations (verify scope): `@Column(name = "category_id")`, `@Column(nullable = false, length = MAX_NAME_LENGTH)`, `@Column(name = "name_key", nullable = false, length = MAX_NAME_LENGTH)`
  - `active: boolean`
    - Nearby annotations (verify scope): `@Column(nullable = false, length = MAX_NAME_LENGTH)`, `@Column(name = "name_key", nullable = false, length = MAX_NAME_LENGTH)`, `@Column(nullable = false)`
  - `createdAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(name = "name_key", nullable = false, length = MAX_NAME_LENGTH)`, `@Column(nullable = false)`, `@Column(name = "created_at", nullable = false)`
  - `updatedAt: LocalDateTime`
    - Nearby annotations (verify scope): `@Column(nullable = false)`, `@Column(name = "created_at", nullable = false)`, `@Column(name = "updated_at", nullable = false)`

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

## `TicketHistoryAction`
- File: `backend/src/main/java/com/voxera/backend/ticket/enums/TicketHistoryAction.java`
- Kind: enum
- Values: `TICKET_CREATED`, `STATUS_CHANGED`, `TICKET_REOPENED`, `ASSIGNED`
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

## `UpdateUserRoleRequest`
- File: `backend/src/main/java/com/voxera/backend/user/dto/UpdateUserRoleRequest.java`
- Kind: DTO / request / response
- No fields/components extracted.

## `UpdateUserStatusRequest`
- File: `backend/src/main/java/com/voxera/backend/user/dto/UpdateUserStatusRequest.java`
- Kind: DTO / request / response
- No fields/components extracted.

## `UserManagementResponse`
- File: `backend/src/main/java/com/voxera/backend/user/dto/UserManagementResponse.java`
- Kind: DTO / request / response
- Fields/components:
  - `JOINED_FORMAT: DateTimeFormatter`
  - `id: UUID`
  - `name: String`
  - `email: String`
  - `initials: String`
  - `department: String`
  - `role: UserRole`
  - `status: UserStatus`
  - `joined: String`

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

# Security and Business Rules

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

---

# Test Map

# Test Map

> Generated: 2026-11-24T09:34:55.233526+00:00
> Git commit: `ca3c4a5`
> Generator: `scripts/generate_metadata.py`



## `BackendApplicationTests`
- File: `backend/src/test/java/com/voxera/backend/BackendApplicationTests.java`
- Test methods detected: 1
  - `contextLoads()`

## `AdminSettingsControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/admin/controller/AdminSettingsControllerIntegrationTest.java`
- Test methods detected: 16
  - `getSettings_adminShouldReturnPersistedDefaults()`
  - `getSettings_agentShouldReceiveForbidden()`
  - `getSettings_employeeShouldReceiveForbidden()`
  - `updateSettings_adminShouldUpdateOnlyProvidedFields()`
  - `updateSettings_shouldPersistChanges()`
  - `updateSettings_agentShouldReceiveForbidden()`
  - `updateSettings_employeeShouldReceiveForbidden()`
  - `updateSettings_shouldRejectInvalidTimezone()`
  - `updateSettings_shouldRejectInvalidPrefix()`
  - `updateSettings_shouldRejectEmptyRequest()`
  - `updatedTicketPrefixShouldBeUsedWhenCreatingTickets()`
  - `updateSettings_shouldUpdateDefaultPriority()`
  - `updateSettings_shouldRejectInvalidDefaultPriority()`
  - `updateSettings_shouldPreserveDefaultPriorityWhenOmitted()`
  - `adminCanUpdateWorkflowSettings()`
  - `partialWorkflowSettingsUpdateShouldPreserveOmittedValues()`

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

## `TicketCategoryControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketCategoryControllerIntegrationTest.java`
- Test methods detected: 15
  - `unauthenticatedUserCannotRetrieveActiveCategories()`
  - `employeeCanRetrieveActiveCategories()`
  - `agentCanRetrieveActiveCategories()`
  - `adminCanRetrieveActiveCategories()`
  - `onlyAdminCanRetrieveAllCategories()`
  - `unauthenticatedUserCannotRetrieveAllCategories()`
  - `adminCanCreateCategory()`
  - `employeeCannotCreateCategory()`
  - `createCategoryRejectsBlankName()`
  - `createCategoryRejectsNameLongerThan100Characters()`
  - `createCategoryRejectsDuplicateNameIgnoringCase()`
  - `adminCanRenameCategory()`
  - `adminCanDeactivateAndReactivateCategory()`
  - `renamingCategoryRejectsDuplicateName()`
  - `operationsReturnNotFoundForUnknownCategory()`

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
- Test methods detected: 5
  - `createTicket_shouldReturnCreatedTicketAndPersistIt()`
  - `createTicket_shouldRejectInvalidRequest()`
  - `createTicket_shouldUseWorkspaceDefaultWhenPriorityIsOmitted()`
  - `createTicket_shouldUseExplicitPriorityInsteadOfWorkspaceDefault()`
  - `createTicket_shouldRejectMissingCategoryEvenWhenPriorityIsOmitted()`

## `TicketLifecycleIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketLifecycleIntegrationTest.java`
- Test methods detected: 6
  - `ticketLifecycle_shouldPersistStateTransitions()`
  - `employeeCanCancelOwnTicket()`
  - `employeeCannotCancelAnotherEmployeesTicket()`
  - `employeeCannotPerformAgentOnlyLifecycleActions()`
  - `reopeningShouldBeRejectedWhenWorkspaceDisablesIt()`
  - `agentCanReopenResolvedTicketWhenWorkspaceAllowsIt()`

## `TicketRetrievalIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/controller/TicketRetrievalIntegrationTest.java`
- Test methods detected: 7
  - `getTicket_shouldReturnExistingTicket()`
  - `getTicket_shouldReturn404ForUnknownTicket()`
  - `getTicketsForUser_shouldReturnTicketsForRequestedUser()`
  - `getTicketsWithoutFilter_employeeShouldReturnOwnTickets()`
  - `getTicketsWithoutFilter_agentShouldReturnAllTickets()`
  - `getTicketsWithoutFilter_adminShouldReturnAllTickets()`
  - `getTickets_employeeShouldNotAccessAnotherUsersTickets()`

## `TicketCategoryTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/entity/TicketCategoryTest.java`
- Test methods detected: 11
  - `categoryShouldPreserveDisplayName()`
  - `categoryNameShouldBeTrimmed()`
  - `constructorShouldRejectNullName()`
  - `constructorShouldRejectBlankName()`
  - `constructorShouldRejectNameLongerThan100Characters()`
  - `constructorShouldAcceptNameOfExactly100Characters()`
  - `renameShouldTrimNewName()`
  - `renameShouldRejectNullName()`
  - `renameShouldRejectBlankName()`
  - `renameShouldRejectNameLongerThan100Characters()`
  - `categoryShouldSupportDeactivationAndReactivation()`

## `TicketTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/entity/TicketTest.java`
- Test methods detected: 16
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
  - `resolvedTicketShouldBeReopened()`
  - `closedTicketShouldBeReopened()`
  - `cancelledTicketShouldNotBeReopened()`
  - `activeTicketStatesShouldNotBeReopened()`

## `TicketCategoryServiceTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/service/TicketCategoryServiceTest.java`
- Test methods detected: 10
  - `getAllCategoriesReturnsAllCategories()`
  - `getActiveCategoriesReturnsOnlyActiveCategories()`
  - `createCategorySavesNewCategory()`
  - `createCategoryRejectsDuplicateNameIgnoringCase()`
  - `renameCategoryUpdatesName()`
  - `renameCategoryAllowsItsExistingName()`
  - `renameCategoryRejectsNameUsedByAnotherCategory()`
  - `deactivateCategoryChangesActiveState()`
  - `activateCategoryChangesActiveState()`
  - `operationsFailWhenCategoryDoesNotExist()`

## `TicketServiceTest`
- File: `backend/src/test/java/com/voxera/backend/ticket/service/TicketServiceTest.java`
- Test methods detected: 17
  - `createTicketShouldSaveTicketForExistingUser()`
  - `createTicketShouldGenerateTicketNumber()`
  - `getTicketShouldReturnExistingTicket()`
  - `getTicketShouldFailWhenTicketDoesNotExist()`
  - `startProgressShouldChangeTicketStatusWhenAssignmentIsNotRequired()`
  - `startProgressShouldFailWhenAssignmentIsRequiredAndTicketIsUnassigned()`
  - `markPendingShouldChangeTicketStatus()`
  - `resolveTicketShouldChangeTicketStatus()`
  - `closeTicketShouldChangeTicketStatus()`
  - `cancelTicketShouldChangeTicketStatus()`
  - `lifecycleOperationShouldFailWhenTicketDoesNotExist()`
  - `assignTicketShouldAssignExistingUser()`
  - `assignTicketShouldFailWhenAssigneeDoesNotExist()`
  - `assignTicketShouldFailWhenTicketDoesNotExist()`
  - `createTicketShouldUseWorkspaceDefaultPriorityWhenOmitted()`
  - `createTicketShouldFailWhenCategoryDoesNotExist()`
  - `createTicketShouldFailWhenCategoryIsInactive()`

## `AdminUserControllerIntegrationTest`
- File: `backend/src/test/java/com/voxera/backend/user/controller/AdminUserControllerIntegrationTest.java`
- Test methods detected: 26
  - `getUsers_adminShouldReturnRealDatabaseUsers()`
  - `getUsers_agentShouldReceiveForbidden()`
  - `getUsers_employeeShouldReceiveForbidden()`
  - `getUsers_shouldSearchByName()`
  - `getUsers_shouldSearchByEmail()`
  - `getUsers_shouldSearchByEmployeeId()`
  - `getUsers_shouldSearchByDepartment()`
  - `getUsers_shouldFilterByRole()`
  - `getUsers_shouldFilterByStatus()`
  - `getUsers_shouldApplyCombinedFilters()`
  - `getUsers_shouldReturnExpectedResponseFields()`
  - `getUsers_invalidRoleShouldReturnBadRequest()`
  - `getUsers_invalidStatusShouldReturnBadRequest()`
  - `updateUserRole_shouldApplyNewRoleToExistingToken()`
  - `updateUserStatus_deactivationShouldRejectExistingToken()`
  - `updateUserStatus_adminShouldReactivateUser()`
  - `updateUserRole_adminCanDemoteAnotherAdmin()`
  - `updateUserRole_adminCannotChangeOwnRole()`
  - `updateUserStatus_adminCannotDeactivateOwnAccount()`
  - `updateUserRole_agentShouldReceiveForbidden()`
  - `updateUserStatus_employeeShouldReceiveForbidden()`
  - `updateUserRole_missingRoleShouldReturnBadRequest()`
  - `updateUserRole_invalidRoleShouldReturnBadRequest()`
  - `updateUserStatus_missingStatusShouldReturnBadRequest()`
  - `updateUserStatus_invalidStatusShouldReturnBadRequest()`
  - `updateUserRole_nonexistentUserShouldReturnNotFound()`

> Test names indicate intended coverage, not proof that a behavior is correctly tested. Use test source and test results for verification.

---

# Project Status

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

---
