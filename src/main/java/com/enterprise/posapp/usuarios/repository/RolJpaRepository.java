package com.enterprise.posapp.usuarios.repository;

import com.enterprise.posapp.usuarios.model.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolJpaRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByNombre(String rol);
}
