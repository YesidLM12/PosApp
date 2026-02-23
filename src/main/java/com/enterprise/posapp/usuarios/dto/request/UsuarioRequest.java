package com.enterprise.posapp.usuarios.dto.request;

import com.enterprise.posapp.usuarios.model.enums.Rol;

public record UsuarioRequest (
				String username,
				String password,
				Rol rol
) {
}
