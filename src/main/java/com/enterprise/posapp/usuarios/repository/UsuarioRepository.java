package com.enterprise.posapp.usuarios.repository;

import com.enterprise.posapp.usuarios.dto.response.UsuarioResponse;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UsuarioRepository {
    void save(Usuarios usuario);

    Usuarios findById(long id);

    Page<UsuarioResponse> getUsers(Pageable pageable);

    Optional<Usuarios> findByUsernameEntity(String username);

    Optional<UsuarioResponse> findByUsername(String username);
}
