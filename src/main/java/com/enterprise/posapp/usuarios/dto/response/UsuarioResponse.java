package com.enterprise.posapp.usuarios.dto.response;

import java.time.LocalDateTime;

public record UsuarioResponse(
				String username,
				boolean activo,
				LocalDateTime created_at
) {
}
