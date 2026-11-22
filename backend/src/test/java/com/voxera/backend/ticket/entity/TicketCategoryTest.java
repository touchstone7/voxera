
package com.voxera.backend.ticket.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TicketCategoryTest {

    private TicketCategory createCategory(String name) {
        LocalDateTime now = LocalDateTime.now();

        return new TicketCategory(
                UUID.randomUUID(),
                name,
                true,
                now,
                now);
    }

    @Test
    void categoryShouldPreserveDisplayName() {
        TicketCategory category = createCategory("Hardware");

        assertEquals("Hardware", category.getName());
    }

    @Test
    void categoryNameShouldBeTrimmed() {
        TicketCategory category = createCategory("  Hardware  ");

        assertEquals("Hardware", category.getName());
    }

    @Test
    void constructorShouldRejectNullName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createCategory(null));

        assertEquals("Category name cannot be null", exception.getMessage());
    }

    @Test
    void constructorShouldRejectBlankName() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createCategory("   "));

        assertEquals("Category name cannot be blank", exception.getMessage());
    }

    @Test
    void constructorShouldRejectNameLongerThan100Characters() {
        String name = "A".repeat(101);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> createCategory(name));

        assertEquals(
                "Category name cannot exceed 100 characters",
                exception.getMessage());
    }

    @Test
    void constructorShouldAcceptNameOfExactly100Characters() {
        String name = "A".repeat(100);

        TicketCategory category = createCategory(name);

        assertEquals(name, category.getName());
    }

    @Test
    void renameShouldTrimNewName() {
        TicketCategory category = createCategory("Hardware");

        category.rename("  Software  ");

        assertEquals("Software", category.getName());
    }

    @Test
    void renameShouldRejectNullName() {
        TicketCategory category = createCategory("Hardware");

        assertThrows(
                IllegalArgumentException.class,
                () -> category.rename(null));

        assertEquals("Hardware", category.getName());
    }

    @Test
    void renameShouldRejectBlankName() {
        TicketCategory category = createCategory("Hardware");

        assertThrows(
                IllegalArgumentException.class,
                () -> category.rename("   "));

        assertEquals("Hardware", category.getName());
    }

    @Test
    void renameShouldRejectNameLongerThan100Characters() {
        TicketCategory category = createCategory("Hardware");

        assertThrows(
                IllegalArgumentException.class,
                () -> category.rename("A".repeat(101)));

        assertEquals("Hardware", category.getName());
    }

    @Test
    void categoryShouldSupportDeactivationAndReactivation() {
        TicketCategory category = createCategory("Hardware");

        category.deactivate();

        assertFalse(category.isActive());

        category.activate();

        assertTrue(category.isActive());
    }
}
