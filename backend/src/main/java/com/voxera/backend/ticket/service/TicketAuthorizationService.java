package com.voxera.backend.ticket.service;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TicketAuthorizationService {

    public void requireResourceAccess(User actor, Ticket ticket) {
        if (actor.getRole() == UserRole.EMPLOYEE
                && !ticket.getCreatedBy().getUserId()
                        .equals(actor.getUserId())) {
            throw new AccessDeniedException(
                    "You are not authorized to access this ticket");
        }
    }

    public void requireAgentOrAdmin(User actor) {
        if (actor.getRole() != UserRole.AGENT
                && actor.getRole() != UserRole.ADMIN) {
            throw new AccessDeniedException(
                    "Only agents and administrators can perform this operation");
        }
    }

    public void requireTicketListAccess(User actor, UUID requestedUserId) {
        if (actor.getRole() == UserRole.EMPLOYEE
                && !actor.getUserId().equals(requestedUserId)) {
            throw new AccessDeniedException(
                    "Employees can only view their own tickets");
        }
    }
}
