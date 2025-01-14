package com.denys.travel_agency.repository;

import com.denys.travel_agency.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);
    Optional<User> findUserByUsername(String username);
    Page<User> findByUsernameStartingWith(String username, Pageable pageable);
    Page<User> findByUsernameStartingWithAndRole(String username, Pageable pageable, String role);
    void deleteByUsername(String username);
}
