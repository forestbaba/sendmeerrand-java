package com.forestsoftware.sendmeerrandserverjava.repository;

import com.forestsoftware.sendmeerrandserverjava.models.Role;
import com.forestsoftware.sendmeerrandserverjava.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName role);
}
