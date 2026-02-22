package com.enterprise.posapp.productos.repository;

import com.enterprise.posapp.productos.dto.response.ProductoResponse;
import com.enterprise.posapp.productos.model.entity.Productos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface ProductoRepository {
    void save(Productos producto);

    Optional<Productos> findById(Long id);

    Productos findByNombre(String nombre);

    void desactivarProducto(Productos producto);

    Page<ProductoResponse> getProducts(Pageable pageable);

    Page<ProductoResponse> getProductsEnabled(Pageable pageable);
}
