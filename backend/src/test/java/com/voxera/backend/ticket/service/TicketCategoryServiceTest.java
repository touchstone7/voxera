
package com.voxera.backend.ticket.service;

import com.voxera.backend.exception.ResourceNotFoundException;
import com.voxera.backend.ticket.entity.TicketCategory;
import com.voxera.backend.ticket.repository.TicketCategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketCategoryServiceTest {

    @Mock
    private TicketCategoryRepository categoryRepository;

    @InjectMocks
    private TicketCategoryService categoryService;

    private UUID categoryId;
    private UUID anotherCategoryId;
    private TicketCategory category;

    @BeforeEach
    void setUp() {
        categoryId = UUID.randomUUID();
        anotherCategoryId = UUID.randomUUID();

        LocalDateTime now = LocalDateTime.now();

        category = new TicketCategory(
                categoryId, "Hardware", true, now, now);
    }

    @Test
    void getAllCategoriesReturnsAllCategories() {
        when(categoryRepository.findAllByOrderByNameAsc())
                .thenReturn(List.of(category));

        List<TicketCategory> result = categoryService.getAllCategories();

        assertEquals(1, result.size());
        assertEquals("Hardware", result.get(0).getName());

        verify(categoryRepository).findAllByOrderByNameAsc();
    }

    @Test
    void getActiveCategoriesReturnsOnlyActiveCategories() {
        when(categoryRepository.findAllByActiveTrueOrderByNameAsc())
                .thenReturn(List.of(category));

        List<TicketCategory> result = categoryService.getActiveCategories();

        assertEquals(1, result.size());
        assertTrue(result.get(0).isActive());

        verify(categoryRepository).findAllByActiveTrueOrderByNameAsc();
    }

    @Test
    void createCategorySavesNewCategory() {
        when(categoryRepository.findByNameKey("hardware"))
                .thenReturn(Optional.empty());

        when(categoryRepository.saveAndFlush(any(TicketCategory.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TicketCategory result = categoryService.createCategory(" Hardware ");

        assertEquals("Hardware", result.getName());
        assertTrue(result.isActive());

        ArgumentCaptor<TicketCategory> captor = ArgumentCaptor.forClass(TicketCategory.class);

        verify(categoryRepository).saveAndFlush(captor.capture());

        assertEquals("Hardware", captor.getValue().getName());
        assertTrue(captor.getValue().isActive());
    }

    @Test
    void createCategoryRejectsDuplicateNameIgnoringCase() {
        when(categoryRepository.findByNameKey("hardware"))
                .thenReturn(Optional.of(category));

        assertThrows(
                IllegalStateException.class,
                () -> categoryService.createCategory("HARDWARE"));

        verify(categoryRepository, never())
                .saveAndFlush(any(TicketCategory.class));
    }

    @Test
    void renameCategoryUpdatesName() {
        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));

        when(categoryRepository.findByNameKey("computers"))
                .thenReturn(Optional.empty());

        when(categoryRepository.saveAndFlush(category))
                .thenReturn(category);

        TicketCategory result = categoryService.renameCategory(categoryId, "Computers");

        assertEquals("Computers", result.getName());

        verify(categoryRepository).saveAndFlush(category);
    }

    @Test
    void renameCategoryAllowsItsExistingName() {
        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));

        when(categoryRepository.findByNameKey("hardware"))
                .thenReturn(Optional.of(category));

        when(categoryRepository.saveAndFlush(category))
                .thenReturn(category);

        TicketCategory result = categoryService.renameCategory(categoryId, "Hardware");

        assertEquals("Hardware", result.getName());
        verify(categoryRepository).saveAndFlush(category);
    }

    @Test
    void renameCategoryRejectsNameUsedByAnotherCategory() {
        TicketCategory anotherCategory = new TicketCategory(
                anotherCategoryId,
                "Software",
                true,
                LocalDateTime.now(),
                LocalDateTime.now());

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));

        when(categoryRepository.findByNameKey("software"))
                .thenReturn(Optional.of(anotherCategory));

        assertThrows(
                IllegalStateException.class,
                () -> categoryService.renameCategory(categoryId, "Software"));

        verify(categoryRepository, never())
                .saveAndFlush(any(TicketCategory.class));
    }

    @Test
    void deactivateCategoryChangesActiveState() {
        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));

        TicketCategory result = categoryService.deactivateCategory(categoryId);

        assertFalse(result.isActive());
    }

    @Test
    void activateCategoryChangesActiveState() {
        category.deactivate();

        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.of(category));

        TicketCategory result = categoryService.activateCategory(categoryId);

        assertTrue(result.isActive());
    }

    @Test
    void operationsFailWhenCategoryDoesNotExist() {
        when(categoryRepository.findById(categoryId))
                .thenReturn(Optional.empty());

        assertAll(
                () -> assertThrows(ResourceNotFoundException.class,
                        () -> categoryService.renameCategory(categoryId, "New")),
                () -> assertThrows(ResourceNotFoundException.class,
                        () -> categoryService.activateCategory(categoryId)),
                () -> assertThrows(ResourceNotFoundException.class,
                        () -> categoryService.deactivateCategory(categoryId)));
    }
}
