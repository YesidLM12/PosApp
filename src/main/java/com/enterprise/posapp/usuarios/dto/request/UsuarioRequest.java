package com.enterprise.posapp.usuarios.dto.request;

public record UsuarioRequest (
				String username,
				String password,
				String rol
) {
}
