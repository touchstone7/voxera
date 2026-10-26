package com.voxera.backend.ticket.entity;

import com.voxera.backend.ticket.enums.TicketCategory;
import com.voxera.backend.ticket.enums.TicketPriority;
import com.voxera.backend.ticket.enums.TicketStatus;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.enums.UserStatus;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TicketTest {

    private Ticket createTicket() {
        LocalDateTime now = LocalDateTime.now();

        User user = new User(
                UUID.randomUUID(),
                "EMP001",
                "Test User",
                "test@voxera.local",
                "IT",
                UserRole.EMPLOYEE,
                UserStatus.ACTIVE,
                now,
                now
        );

        return new Ticket(
                UUID.randomUUID(),
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
    void newTicketShouldStartAsOpen() {
        Ticket ticket = createTicket();

        assertEquals(TicketStatus.OPEN, ticket.getStatus());
    }

    @Test
    void openTicketCanStartProgress() {
        Ticket ticket = createTicket();

        ticket.startProgress();

        assertEquals(TicketStatus.IN_PROGRESS, ticket.getStatus());
    }

    @Test
    void inProgressTicketCanBeMarkedPending() {
        Ticket ticket = createTicket();

        ticket.startProgress();
        ticket.markPending();

        assertEquals(TicketStatus.PENDING, ticket.getStatus());
    }

    @Test
    void inProgressTicketCanBeResolved() {
        Ticket ticket = createTicket();

        ticket.startProgress();
        ticket.resolve();

        assertEquals(TicketStatus.RESOLVED, ticket.getStatus());
        assertNotNull(ticket.getResolvedAt());
    }

    @Test
    void pendingTicketCanBeResolved() {
        Ticket ticket = createTicket();

        ticket.startProgress();
        ticket.markPending();
        ticket.resolve();

        assertEquals(TicketStatus.RESOLVED, ticket.getStatus());
        assertNotNull(ticket.getResolvedAt());
    }

    @Test
    void resolvedTicketCanBeClosed() {
        Ticket ticket = createTicket();

        ticket.startProgress();
        ticket.resolve();
        ticket.close();

        assertEquals(TicketStatus.CLOSED, ticket.getStatus());
        assertNotNull(ticket.getResolvedAt());
        assertNotNull(ticket.getClosedAt());
    }

    @Test
    void closedTicketCannotBeCancelled() {
        Ticket ticket = createTicket();

        ticket.startProgress();
        ticket.resolve();
        ticket.close();

        LocalDateTime updatedAtBefore = ticket.getUpdatedAt();
        LocalDateTime resolvedAtBefore = ticket.getResolvedAt();
        LocalDateTime closedAtBefore = ticket.getClosedAt();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                ticket::cancel
        );

        assertEquals(
                "Closed or cancelled tickets cannot be cancelled",
                exception.getMessage()
        );
        assertEquals(TicketStatus.CLOSED, ticket.getStatus());
        assertEquals(updatedAtBefore, ticket.getUpdatedAt());
        assertEquals(resolvedAtBefore, ticket.getResolvedAt());
        assertEquals(closedAtBefore, ticket.getClosedAt());
    }

    @Test
    void openTicketCannotBeResolvedDirectlyWithoutChangingStateOrTimestamps() {
        Ticket ticket = createTicket();

        LocalDateTime updatedAtBefore = ticket.getUpdatedAt();
        LocalDateTime resolvedAtBefore = ticket.getResolvedAt();
        LocalDateTime closedAtBefore = ticket.getClosedAt();

        assertThrows(IllegalStateException.class, ticket::resolve);

        assertEquals(TicketStatus.OPEN, ticket.getStatus());
        assertEquals(updatedAtBefore, ticket.getUpdatedAt());
        assertEquals(resolvedAtBefore, ticket.getResolvedAt());
        assertEquals(closedAtBefore, ticket.getClosedAt());
    }

    @Test
    void openTicketCannotBeClosedWithoutChangingStateOrTimestamps() {
        Ticket ticket = createTicket();

        LocalDateTime updatedAtBefore = ticket.getUpdatedAt();
        LocalDateTime resolvedAtBefore = ticket.getResolvedAt();
        LocalDateTime closedAtBefore = ticket.getClosedAt();

        assertThrows(IllegalStateException.class, ticket::close);

        assertEquals(TicketStatus.OPEN, ticket.getStatus());
        assertEquals(updatedAtBefore, ticket.getUpdatedAt());
        assertEquals(resolvedAtBefore, ticket.getResolvedAt());
        assertEquals(closedAtBefore, ticket.getClosedAt());
    }

    @Test
    void cancelledTicketCannotBeCancelledAgain() {
        Ticket ticket = createTicket();
        ticket.cancel();

        LocalDateTime updatedAtBefore = ticket.getUpdatedAt();
        LocalDateTime resolvedAtBefore = ticket.getResolvedAt();
        LocalDateTime closedAtBefore = ticket.getClosedAt();

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                ticket::cancel
        );

        assertEquals(
                "Closed or cancelled tickets cannot be cancelled",
                exception.getMessage()
        );
        assertEquals(TicketStatus.CANCELLED, ticket.getStatus());
        assertEquals(updatedAtBefore, ticket.getUpdatedAt());
        assertEquals(resolvedAtBefore, ticket.getResolvedAt());
        assertEquals(closedAtBefore, ticket.getClosedAt());
    }

    @Test
    void resolvedTicketCanBeCancelledAndRetainsResolutionTimestamp() {
        Ticket ticket = createTicket();
        ticket.startProgress();
        ticket.resolve();

        LocalDateTime resolvedAtBefore = ticket.getResolvedAt();

        ticket.cancel();

        assertEquals(TicketStatus.CANCELLED, ticket.getStatus());
        assertEquals(resolvedAtBefore, ticket.getResolvedAt());
        assertNull(ticket.getClosedAt());
    }

    @Test
    void lifecycleTimestampsRemainConsistent() {
        Ticket ticket = createTicket();

        assertFalse(ticket.getUpdatedAt().isBefore(ticket.getCreatedAt()));

        ticket.startProgress();
        ticket.resolve();
        ticket.close();

        assertNotNull(ticket.getResolvedAt());
        assertNotNull(ticket.getClosedAt());
        assertFalse(ticket.getResolvedAt().isAfter(ticket.getClosedAt()));
        assertFalse(ticket.getUpdatedAt().isBefore(ticket.getCreatedAt()));
    }
}
