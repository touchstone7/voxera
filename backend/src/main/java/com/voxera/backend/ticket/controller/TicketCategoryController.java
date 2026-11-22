
package com.voxera.backend.ticket.controller;

import com.voxera.backend.ticket.dto.CreateTicketCategoryRequest;
import com.voxera.backend.ticket.dto.RenameTicketCategoryRequest;
import com.voxera.backend.ticket.dto.TicketCategoryResponse;
import com.voxera.backend.ticket.entity.TicketCategory;
import com.voxera.backend.ticket.service.TicketCategoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/ticket-categories")
public class TicketCategoryController {

    private final TicketCategoryService categoryService;

    public TicketCategoryController(TicketCategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Returns all categories, including inactive categories.
     * Admin-only endpoint.
     */
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TicketCategoryResponse> getAllCategories() {
        return categoryService.getAllCategories()
                .stream()
                .map(TicketCategoryResponse::from)
                .toList();
    }

    /**
     * Returns only active categories.
     * Available to all authenticated users.
     */
    @GetMapping("/active")
    @PreAuthorize("isAuthenticated()")
    public List<TicketCategoryResponse> getActiveCategories() {
        return categoryService.getActiveCategories()
                .stream()
                .map(TicketCategoryResponse::from)
                .toList();
    }

    /**
     * Creates a new category.
     * Admin-only endpoint.
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TicketCategoryResponse> createCategory(
            @Valid @RequestBody CreateTicketCategoryRequest request) {

        TicketCategory category = categoryService.createCategory(request.name());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TicketCategoryResponse.from(category));
    }

    /**
     * Renames an existing category.
     * Admin-only endpoint.
     */
    @PatchMapping("/{categoryId}/name")
    @PreAuthorize("hasRole('ADMIN')")
    public TicketCategoryResponse renameCategory(
            @PathVariable UUID categoryId,
            @Valid @RequestBody RenameTicketCategoryRequest request) {

        return TicketCategoryResponse.from(
                categoryService.renameCategory(categoryId, request.name()));
    }

    /**
     * Deactivates a category.
     * Admin-only endpoint.
     */
    @PatchMapping("/{categoryId}/deactivate")
    @PreAuthorize("hasRole('ADMIN')")
    public TicketCategoryResponse deactivateCategory(
            @PathVariable UUID categoryId) {

        return TicketCategoryResponse.from(
                categoryService.deactivateCategory(categoryId));
    }

    /**
     * Reactivates a category.
     * Admin-only endpoint.
     */
    @PatchMapping("/{categoryId}/activate")
    @PreAuthorize("hasRole('ADMIN')")
    public TicketCategoryResponse activateCategory(
            @PathVariable UUID categoryId) {

        return TicketCategoryResponse.from(
                categoryService.activateCategory(categoryId));
    }
}
