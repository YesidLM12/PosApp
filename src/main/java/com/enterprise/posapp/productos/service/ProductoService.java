package com.enterprise.posapp.productos.service;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.productos.dto.request.ProductoRequest;
import com.enterprise.posapp.productos.dto.response.ProductoResponse;
import com.enterprise.posapp.productos.model.entity.CategoriaRepositoryJpa;
import com.enterprise.posapp.productos.model.entity.Categorias;
import com.enterprise.posapp.productos.model.entity.Productos;
import com.enterprise.posapp.productos.repository.ProductoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductoService {
    private final ProductoRepositoryJpa productoRepositoryJpa;
    private final CategoriaRepositoryJpa categoriaRepositoryJpa;

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Map<String, String> crearProducto(ProductoRequest dto) {
        Productos productoExiste = productoRepositoryJpa.findByNombre(dto.nombre());
        if (productoExiste != null)
            throw new ConflicException("El producto ya se encuentra registrado");

        try {
            Categorias categoria = categoriaRepositoryJpa.findByNombre(dto.categoria());

            Productos nuevoProducto = Productos
                    .builder()
                    .nombre(dto.nombre())
                    .precio(dto.precio())
                    .activo(true)
                    .categoria(categoria)
                    .build();

            productoRepositoryJpa.save(nuevoProducto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Map.of("Mensaje", "Producto creado correctamente");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Map<String, String> editarProducto(Long id, ProductoRequest dto) {
        Productos productoExistente = productoRepositoryJpa.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado"));

        try {
            if (dto.nombre() != null) {
                productoExistente.setNombre(dto.nombre());
            }

            if (dto.categoria() != null) {
                Categorias categoria = categoriaRepositoryJpa.findByNombre(dto.categoria());
                productoExistente.setCategoria(categoria);
            }

            if (dto.precio() != null) {
                productoExistente.setPrecio(dto.precio());
            }

            productoRepositoryJpa.save(productoExistente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Map.of("Mensaje", "Producto actualizado correctamente");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public Map<String, String> desactivarProducto(Long productoId) {
        try {
            Productos producto = productoRepositoryJpa.findById(productoId)
                    .orElseThrow(() ->  new ResourceNotFoundException("Producto no encontrado"));

            productoRepositoryJpa.desactivarProducto(producto);
            productoRepositoryJpa.save(producto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return Map.of("Mensaje", "Producto desactivado");
    }

    @PreAuthorize("hasAnyRole('MESERO', 'CAJERO')")
    public Page<ProductoResponse> obtenerProductosActivos(Pageable pageable) {
        return productoRepositoryJpa.getProductsEnabled(pageable);
    }

    @PreAuthorize("hasAnyRole('MESERO', 'CAJERO')")
    public Page<ProductoResponse> obtenerTodosLosProductos(Pageable pageable) {
       return productoRepositoryJpa.getProducts(pageable);
    }

}
