package com.enterprise.posapp.usuarios.mapper;

import com.enterprise.posapp.usuarios.dto.request.UsuarioRequest;
import com.enterprise.posapp.usuarios.dto.response.UsuarioResponse;
import com.enterprise.posapp.usuarios.model.entity.Roles;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.RolRepositoryJpa;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
@RequiredArgsConstructor
public class UsuarioMapper {
	private static RolRepositoryJpa rolRepositoryJpa;

	public static Usuarios toEntity(UsuarioRequest usuario) {
		Roles rol = rolRepositoryJpa.findByRol(usuario.rol());
		return Usuarios.builder()
				.username(usuario.username())
				.password(usuario.password())
				.rol(rol)
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
