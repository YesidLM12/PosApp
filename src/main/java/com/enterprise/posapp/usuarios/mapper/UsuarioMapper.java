package com.enterprise.posapp.usuarios.mapper;

import com.enterprise.posapp.usuarios.dto.request.UsuarioRequest;
import com.enterprise.posapp.usuarios.dto.response.UsuarioResponse;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.RolRepositoryJpa;

import java.time.LocalDateTime;

public class UsuarioMapper {
	public static Usuarios toEntity(UsuarioRequest usuario) {
		return Usuarios.builder()
				.username(usuario.username())
				.password(usuario.password())
				.rol(usuario.rol())
				.activo(true)
				.created_at(LocalDateTime.now())
				.build();

	}

	public static UsuarioResponse toDTO(Usuarios entity) {
		return new UsuarioResponse(
				entity.getUsername(),
				entity.isActivo(),
				entity.getCreated_at());
	}
}
