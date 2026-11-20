package com.voxera.backend.ticket.repository;

import com.voxera.backend.ticket.entity.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TicketCategoryRepository
        extends JpaRepository<TicketCategory, UUID> {

    Optional<TicketCategory> findByNameKey(String nameKey);

    boolean existsByNameKey(String nameKey);

    List<TicketCategory> findAllByActiveTrueOrderByNameAsc();
}