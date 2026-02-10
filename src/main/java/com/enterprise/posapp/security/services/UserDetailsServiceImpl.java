package com.enterprise.posapp.security.services;

import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<Usuarios> usuario =Optional.of( usuarioRepository.findByUsernameEntity(username)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")));
        return new  UserDetailsImpl(usuario.orElse(null));
    }
}
