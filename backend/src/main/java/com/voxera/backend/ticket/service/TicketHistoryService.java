
package com.voxera.backend.ticket.service;

import com.voxera.backend.ticket.entity.Ticket;
import com.voxera.backend.ticket.entity.TicketHistory;
import com.voxera.backend.ticket.enums.TicketHistoryAction;
import com.voxera.backend.ticket.repository.TicketHistoryRepository;
import com.voxera.backend.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public class TicketHistoryService {

    private final TicketHistoryRepository ticketHistoryRepository;

    public TicketHistoryService(
            TicketHistoryRepository ticketHistoryRepository) {
        this.ticketHistoryRepository = ticketHistoryRepository;
    }

    public void record(
            Ticket ticket,
            User actor,
            TicketHistoryAction action,
            String oldValue,
            String newValue) {

        TicketHistory history = new TicketHistory(
                ticket,
                actor,
                action.name(),
                oldValue,
                newValue);

        ticketHistoryRepository.save(history);
    }
}
