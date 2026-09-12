package com.voxera.backend.ticket.service;

import com.voxera.backend.exception.ResourceNotFoundException;
import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.enums.TicketCategory;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.ticket.enums.TicketStatus;
import com.voxera.backend.ticket.repository.TicketRepository;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserRepository userRepository;

    private TicketService ticketService;

    private UUID userId;
    private UUID ticketId;
    private User user;
    private Ticket ticket;

    @BeforeEach
    void setUp() {
        ticketService = new TicketService(
                ticketRepository,
                userRepository
        );

        userId = UUID.randomUUID();
        ticketId = UUID.randomUUID();

        LocalDateTime now = LocalDateTime.now();

        user = new User(
                userId,
                "EMP001",
                "Test User",
                "test@voxera.local",
                "IT",
                "EMPLOYEE",
                "ACTIVE",
                now,
                now
        );

        ticket = new Ticket(
                ticketId,
                "INC-2026-TEST001",
                "Test ticket",
                "Test description",
                TicketPriority.HIGH,
                TicketCategory.NETWORK,
                user,
                now
        );
    }

    @Test
    void createTicketShouldSaveTicketForExistingUser() {
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));

        when(ticketRepository.save(any(Ticket.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Ticket result = ticketService.createTicket(
                "Laptop not connecting",
                "Laptop cannot connect to Wi-Fi",
                TicketPriority.HIGH,
                TicketCategory.NETWORK,
                userId
        );

        assertNotNull(result);
        assertEquals("Laptop not connecting", result.getTitle());
        assertEquals(
                "Laptop cannot connect to Wi-Fi",
                result.getDescription()
        );
        assertEquals(TicketStatus.OPEN, result.getStatus());
        assertEquals(TicketPriority.HIGH, result.getPriority());
        assertEquals(TicketCategory.NETWORK, result.getCategory());
        assertEquals(user, result.getCreatedBy());

        verify(userRepository).findById(userId);
        verify(ticketRepository).save(any(Ticket.class));
    }

    @Test
    void createTicketShouldFailWhenUserDoesNotExist() {
        when(userRepository.findById(userId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> ticketService.createTicket(
                        "Laptop not connecting",
                        "Laptop cannot connect to Wi-Fi",
                        TicketPriority.HIGH,
                        TicketCategory.NETWORK,
                        userId
                )
        );

        verify(userRepository).findById(userId);
        verify(ticketRepository, never()).save(any(Ticket.class));
    }

    @Test
    void createTicketShouldGenerateTicketNumber() {
        when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));

        ArgumentCaptor<Ticket> ticketCaptor =
                ArgumentCaptor.forClass(Ticket.class);

        when(ticketRepository.save(any(Ticket.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ticketService.createTicket(
                "Laptop not connecting",
                "Laptop cannot connect to Wi-Fi",
                TicketPriority.HIGH,
                TicketCategory.NETWORK,
                userId
        );

        verify(ticketRepository).save(ticketCaptor.capture());

        Ticket savedTicket = ticketCaptor.getValue();

        assertNotNull(savedTicket.getTicketId());
        assertNotNull(savedTicket.getTicketNumber());
        assertTrue(savedTicket.getTicketNumber().startsWith("INC-"));
    }

    @Test
    void getTicketShouldReturnExistingTicket() {
        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        Ticket result = ticketService.getTicket(ticketId);

        assertSame(ticket, result);

        verify(ticketRepository).findById(ticketId);
    }

    @Test
    void getTicketShouldFailWhenTicketDoesNotExist() {
        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> ticketService.getTicket(ticketId)
        );

        verify(ticketRepository).findById(ticketId);
    }

    @Test
    void startProgressShouldChangeTicketStatus() {
        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        Ticket result = ticketService.startProgress(ticketId);

        assertEquals(TicketStatus.IN_PROGRESS, result.getStatus());

        verify(ticketRepository).findById(ticketId);
    }

    @Test
    void markPendingShouldChangeTicketStatus() {
        ticket.startProgress();

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        Ticket result = ticketService.markPending(ticketId);

        assertEquals(TicketStatus.PENDING, result.getStatus());

        verify(ticketRepository).findById(ticketId);
    }

    @Test
    void resolveTicketShouldChangeTicketStatus() {
        ticket.startProgress();

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        Ticket result = ticketService.resolveTicket(ticketId);

        assertEquals(TicketStatus.RESOLVED, result.getStatus());
        assertNotNull(result.getResolvedAt());

        verify(ticketRepository).findById(ticketId);
    }

    @Test
    void closeTicketShouldChangeTicketStatus() {
        ticket.startProgress();
        ticket.resolve();

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        Ticket result = ticketService.closeTicket(ticketId);

        assertEquals(TicketStatus.CLOSED, result.getStatus());
        assertNotNull(result.getClosedAt());

        verify(ticketRepository).findById(ticketId);
    }

    @Test
    void cancelTicketShouldChangeTicketStatus() {
        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        Ticket result = ticketService.cancelTicket(ticketId);

        assertEquals(TicketStatus.CANCELLED, result.getStatus());

        verify(ticketRepository).findById(ticketId);
    }

    @Test
    void lifecycleOperationShouldFailWhenTicketDoesNotExist() {
        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> ticketService.startProgress(ticketId)
        );

        verify(ticketRepository).findById(ticketId);
    }

    @Test
        void assignTicketShouldAssignExistingUser() {
        UUID assigneeId = UUID.randomUUID();

        LocalDateTime now = LocalDateTime.now();

        User assignee = new User(
                assigneeId,
                "EMP002",
                "Assignee User",
                "assignee@voxera.local",
                "IT",
                "EMPLOYEE",
                "ACTIVE",
                now,
                now
        );

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        when(userRepository.findById(assigneeId))
                .thenReturn(Optional.of(assignee));

        when(ticketRepository.save(ticket))
                .thenReturn(ticket);

        Ticket result = ticketService.assignTicket(
                ticketId,
                assigneeId
        );

        assertSame(assignee, result.getAssignedTo());

        verify(ticketRepository).findById(ticketId);
        verify(userRepository).findById(assigneeId);
        verify(ticketRepository).save(ticket);
        }

        @Test
        void assignTicketShouldFailWhenAssigneeDoesNotExist() {
        UUID assigneeId = UUID.randomUUID();

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        when(userRepository.findById(assigneeId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> ticketService.assignTicket(
                        ticketId,
                        assigneeId
                )
        );

        verify(ticketRepository).findById(ticketId);
        verify(userRepository).findById(assigneeId);
        verify(ticketRepository, never()).save(any(Ticket.class));
        }

        @Test
        void assignTicketShouldFailWhenTicketDoesNotExist() {
        UUID assigneeId = UUID.randomUUID();

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> ticketService.assignTicket(
                        ticketId,
                        assigneeId
                )
        );

        verify(ticketRepository).findById(ticketId);
        verify(userRepository, never()).findById(any(UUID.class));
        verify(ticketRepository, never()).save(any(Ticket.class));
        }
}
