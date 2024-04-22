package org.technous.validation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.technous.validation.model.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String role);
    boolean existsByName(String role);
}
