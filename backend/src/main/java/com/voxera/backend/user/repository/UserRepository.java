package com.voxera.backend.user.repository;

import com.voxera.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import com.voxera.backend.user.enums.UserRole;
import com.voxera.backend.user.enums.UserStatus;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmployeeId(String employeeId);

    Optional<User> findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
            SELECT u
            FROM User u
            WHERE u.role = :role
              AND u.status = :status
            ORDER BY u.userId
            """)
    List<User> findByRoleAndStatusForUpdate(
            @Param("role") UserRole role,
            @Param("status") UserStatus status);
}
