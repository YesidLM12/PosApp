package com.enterprise.posapp.usuarios.repository;

import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<Usuarios, Long> {
	Usuarios findByUsername(String nombre);
}
