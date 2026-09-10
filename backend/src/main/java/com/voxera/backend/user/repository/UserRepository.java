package com.voxera.backend.user.repository;

import com.voxera.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmployeeId(String employeeId);

    Optional<User> findByEmail(String email);
}
