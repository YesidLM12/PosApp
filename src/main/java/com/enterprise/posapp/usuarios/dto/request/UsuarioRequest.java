package com.enterprise.posapp.usuarios.dto.request;

import com.enterprise.posapp.usuarios.model.entity.Roles;

import java.time.LocalDateTime;

public record UsuarioRequest (
				String username,
				String password,
				String rol
) {
}
