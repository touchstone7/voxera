package com.voxera.backend.ticket.service;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.user.entity.User;
import com.voxera.backend.user.enums.UserRole;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

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
}
