package com.voxera.backend.admin.repository;

import com.voxera.backend.admin.entity.WorkspaceSettings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkspaceSettingsRepository
        extends JpaRepository<WorkspaceSettings, Integer> {
}