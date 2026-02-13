package com.enterprise.posapp.productos.repository;

import com.enterprise.posapp.productos.model.entity.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductoJpaRepository extends JpaRepository<Productos, Long> {
    Optional<Productos> findByNombre(String nombre);

    Page<Productos> findByActivoTrue(Pageable pageable);
}
