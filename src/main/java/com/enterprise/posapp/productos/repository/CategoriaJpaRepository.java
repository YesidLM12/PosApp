package com.enterprise.posapp.productos.repository;

import com.enterprise.posapp.productos.model.entity.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaJpaRepository extends JpaRepository<Categorias, Long> {
    Optional<Categorias> findByNombre(String nombre);
}
