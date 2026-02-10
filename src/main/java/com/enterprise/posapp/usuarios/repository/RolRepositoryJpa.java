package com.enterprise.posapp.usuarios.repository;

import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.usuarios.model.entity.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RolRepositoryJpa implements RolRepository {
    private final RolJpaRepository rolJpaRepository;

    @Override
    public void save(Roles rol) {
        rolJpaRepository.save(rol);
    }

    @Override
    public Roles findByRol(String rol) {
        return rolJpaRepository.findByNombre(rol)
                .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado"));
    }
}
