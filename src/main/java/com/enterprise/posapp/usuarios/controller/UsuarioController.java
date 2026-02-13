package com.enterprise.posapp.usuarios.controller;

import com.enterprise.posapp.usuarios.dto.request.UsuarioRequest;
import com.enterprise.posapp.usuarios.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuarios", description = "Gestión de usuarios (editar, desactivar usuarios)")
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PatchMapping("/{usuarioId}")
    @Operation(summary = "Actualizar productos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario actualizado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public Map<String, String> editarUsuario(@PathVariable Long usuarioId, @RequestBody UsuarioRequest dto) {
        return usuarioService.editarUsuario(usuarioId, dto);
    }

    @PutMapping("/{usuarioId}")
    @Operation(summary = "Desactivar usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario desactivado"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    public Map<String, String> desactivarUsuario(@PathVariable Long usuarioId) {
        return usuarioService.desactivarUsuario(usuarioId);
    }
}
