package com.enterprise.posapp.productos.repository;

import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.productos.dto.response.ProductoResponse;
import com.enterprise.posapp.productos.mapper.ProductoMapper;
import com.enterprise.posapp.productos.model.entity.Productos;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class ProductoRepositoryJpa implements ProductoRepository {
    private final ProductoJpaRepository productoJpaRepository;

    @Override
    public void save(Productos producto) {
        productoJpaRepository.save(producto);
    }

    @Override
    public Productos findById(Long id) {
        return productoJpaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));
    }

    @Override
    public Productos findByNombre(String nombre) {
        return productoJpaRepository.findByNombre(nombre);
    }

    @Override
    public Page<ProductoResponse> getProducts(Pageable pageable) {
        return productoJpaRepository.findAll(pageable)
                .map(ProductoMapper::toResponse);
    }

    @Override
    public Page<ProductoResponse> getProductsEnabled(Pageable pageable) {
        return productoJpaRepository.findByActivoTrue(pageable)
                .map(ProductoMapper::toResponse);
    }
}
