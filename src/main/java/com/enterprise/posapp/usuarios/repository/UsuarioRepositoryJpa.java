package com.enterprise.posapp.usuarios.repository;

import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.usuarios.dto.response.UsuarioResponse;
import com.enterprise.posapp.usuarios.mapper.UsuarioMapper;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryJpa implements UsuarioRepository {
    private final UsuarioJpaRepository jpaRepository;

    @Override
    public void save(Usuarios usuario) {
        jpaRepository.save(usuario);
    }


    @Override
    public Optional<UsuarioResponse> findById(long id) {
        return Optional.of(
                jpaRepository.findById(id)
                        .map(UsuarioMapper::toDTO)
                        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado")));
    }

    @Override
    public Page<UsuarioResponse> getUsers(Pageable pageable) {
        return jpaRepository.findAll(pageable)
                .map(UsuarioMapper::toDTO);
    }

    @Override
    public Usuarios findByUsername(String username) {
        return jpaRepository.findByUsername(username);
    }
}
