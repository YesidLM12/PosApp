package com.enterprise.posapp.productos.repository;

import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.productos.model.entity.Categorias;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoriaRepositoryJpa implements CategoriaRepository {
    private final CategoriaJpaRepository categoriaJpaRepository;

    @Override
    public void save(Categorias categoria) {
        categoriaJpaRepository.save(categoria);
    }

    @Override
    public Categorias findByNombre(String nombre) {
        return categoriaJpaRepository.findByNombre(nombre)
                        .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));
    }
}
