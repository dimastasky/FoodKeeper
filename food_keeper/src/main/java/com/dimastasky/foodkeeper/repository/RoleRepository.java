package com.dimastasky.foodkeeper.repository;

import com.dimastasky.foodkeeper.models.data.ERole;
import com.dimastasky.foodkeeper.models.account.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
