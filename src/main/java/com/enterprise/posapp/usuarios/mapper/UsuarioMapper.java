package com.enterprise.posapp.usuarios.mapper;

import com.enterprise.posapp.usuarios.dto.response.UsuarioResponse;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.RolRepositoryJpa;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UsuarioMapper {
    private static RolRepositoryJpa rolRepositoryJpa;

    public static UsuarioResponse toDTO(Usuarios entity) {
        return new UsuarioResponse(
                entity.getUsername(),
                entity.isActivo(),
                entity.getCreated_at());
    }
}
