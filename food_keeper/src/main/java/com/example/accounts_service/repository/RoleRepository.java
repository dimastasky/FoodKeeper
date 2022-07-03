package com.example.accounts_service.repository;

import com.example.accounts_service.models.data.ERole;
import com.example.accounts_service.models.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
