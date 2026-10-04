package com.voxera.backend.user.repository;

import com.voxera.backend.user.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserCredentialRepository
        extends JpaRepository<UserCredential, UUID> {
}