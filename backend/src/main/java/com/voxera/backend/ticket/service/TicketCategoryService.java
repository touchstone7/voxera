
package com.voxera.backend.ticket.service;

import com.voxera.backend.exception.ResourceNotFoundException;
import com.voxera.backend.ticket.entity.TicketCategory;
import com.voxera.backend.ticket.repository.TicketCategoryRepository;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Service
public class TicketCategoryService {

    private static final String DUPLICATE_NAME_MESSAGE = "A ticket category with this name already exists";

    private final TicketCategoryRepository categoryRepository;

    public TicketCategoryService(
            TicketCategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public List<TicketCategory> getAllCategories() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public List<TicketCategory> getActiveCategories() {
        return categoryRepository.findAllByActiveTrueOrderByNameAsc();
    }

    @Transactional
    public TicketCategory createCategory(String name) {
        // Entity validates and trims the supplied name.
        LocalDateTime now = LocalDateTime.now();

        TicketCategory category = new TicketCategory(
                UUID.randomUUID(),
                name,
                true,
                now,
                now);

        ensureNameAvailable(category.getName(), null);

        try {
            return categoryRepository.saveAndFlush(category);
        } catch (DataIntegrityViolationException exception) {
            throw new IllegalStateException(
                    DUPLICATE_NAME_MESSAGE,
                    exception);
        }
    }

    @Transactional
    public TicketCategory renameCategory(UUID categoryId, String newName) {
        TicketCategory category = findCategory(categoryId);

        // Entity validates and trims the new name.
        category.rename(newName);

        ensureNameAvailable(category.getName(), categoryId);

        try {
            return categoryRepository.saveAndFlush(category);
        } catch (DataIntegrityViolationException exception) {
            throw new IllegalStateException(
                    DUPLICATE_NAME_MESSAGE,
                    exception);
        }
    }

    @Transactional
    public TicketCategory deactivateCategory(UUID categoryId) {
        TicketCategory category = findCategory(categoryId);

        category.deactivate();

        return category;
    }

    @Transactional
    public TicketCategory activateCategory(UUID categoryId) {
        TicketCategory category = findCategory(categoryId);

        category.activate();

        return category;
    }

    private TicketCategory findCategory(UUID categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Ticket category not found: " + categoryId));
    }

    private void ensureNameAvailable(String name, UUID currentCategoryId) {
        String nameKey = name.toLowerCase(Locale.ROOT);

        categoryRepository.findByNameKey(nameKey)
                .ifPresent(existingCategory -> {
                    if (!existingCategory.getCategoryId()
                            .equals(currentCategoryId)) {

                        throw new IllegalStateException(
                                DUPLICATE_NAME_MESSAGE);
                    }
                });
    }
}
