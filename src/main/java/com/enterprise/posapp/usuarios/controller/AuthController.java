package com.enterprise.posapp.usuarios.controller;

import com.enterprise.posapp.usuarios.dto.request.LoginRequest;
import com.enterprise.posapp.usuarios.dto.request.UsuarioRequest;
import com.enterprise.posapp.usuarios.dto.response.LoginResponse;
import com.enterprise.posapp.usuarios.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticación", description = "Gestión de registro e ingreso al sistema")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/registro")
    @Operation(summary = "Registrar usuarios", description = "Retorna un mensaje si el registro es correcto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuario creado"),
            @ApiResponse(responseCode = "400", description = "Datos incorrectos"),
            @ApiResponse(responseCode = "409", description = "El usuario ya existe"),
            @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> registro(@RequestBody UsuarioRequest dto) {
        return authService.registrarUsuario(dto);
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ingreso al sistema"),
            @ApiResponse(responseCode = "400", description = "Credenciales incorrectas"),
            @ApiResponse(responseCode = "404", description = "usuario no existe en el sistema"),
    })
    public LoginResponse login(@RequestBody LoginRequest dto) {
        return authService.login(dto);
    }
}
