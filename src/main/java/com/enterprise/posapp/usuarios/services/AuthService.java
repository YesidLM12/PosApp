package com.enterprise.posapp.usuarios.services;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.security.jwt.JwtProvider;
import com.enterprise.posapp.usuarios.dto.request.LoginRequest;
import com.enterprise.posapp.usuarios.dto.request.UsuarioRequest;
import com.enterprise.posapp.usuarios.dto.response.LoginResponse;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.RolRepositoryJpa;
import com.enterprise.posapp.usuarios.repository.UsuarioRepositoryJpa;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final RolRepositoryJpa rolRepositoryJpa;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public LoginResponse login(@NonNull LoginRequest dto) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.username(), dto.password())
        );

        UserDetails user = (UserDetails) auth.getPrincipal();
        String token = jwtProvider.generateToken(user);

        return new LoginResponse(token);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Map<String, String> registrarUsuario(@NonNull UsuarioRequest dto) {
        Optional<Usuarios> usuarioExistente = usuarioRepositoryJpa.findByUsernameEntity(dto.username());
        if (usuarioExistente.isPresent())
            throw new ConflicException("El usuario ya se encuentra registrado");

        try {
            Usuarios usuario = Usuarios.builder()
                    .username(dto.username())
                    .password(passwordEncoder.encode(dto.password()))
                    .rol(rolRepositoryJpa.findByRol(dto.rol()))
                    .activo(true)
                    .created_at(LocalDateTime.now())
                    .build();

            usuarioRepositoryJpa.save(usuario);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Map.of("mensaje", "Usuario registrado correctamente.");
    }
}
