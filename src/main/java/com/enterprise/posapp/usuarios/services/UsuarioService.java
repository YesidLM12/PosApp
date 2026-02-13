package com.enterprise.posapp.usuarios.services;

import com.enterprise.posapp.usuarios.dto.request.UsuarioRequest;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.RolRepositoryJpa;
import com.enterprise.posapp.usuarios.repository.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final RolRepositoryJpa rolRepositoryJpa;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Map<String, String> editarUsuario(Long usuarioId, UsuarioRequest dto) {
        Usuarios usuarioExistente = usuarioRepositoryJpa.findById(usuarioId);

        if (dto.rol() != null) {
            usuarioExistente.setRol(rolRepositoryJpa.findByRol(dto.rol()));
        }

        if (dto.username() != null) {
            usuarioRepositoryJpa.findByUsername(dto.username());
            usuarioExistente.setUsername(dto.username());
        }

        usuarioRepositoryJpa.save(usuarioExistente);

        return Map.of("Mensaje", "Usuario Actualizado");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Map<String, String> desactivarUsuario(Long usuarioId) {
        Usuarios usuarios = usuarioRepositoryJpa.findById(usuarioId);
        usuarios.setActivo(false);
        usuarioRepositoryJpa.save(usuarios);

        return Map.of("Mensaje", "Usuario desactivado");
    }
}
