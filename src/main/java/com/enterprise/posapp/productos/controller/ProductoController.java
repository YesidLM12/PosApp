package com.enterprise.posapp.productos.controller;

import com.enterprise.posapp.productos.dto.request.ProductoRequest;
import com.enterprise.posapp.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/producto")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Gestión de productos (crear, editar, desactivar)")
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping
    public Map<String, String> agregarProducto(@RequestBody ProductoRequest p) {
        return productoService.crearProducto(p);
    }

    @PutMapping
    public Map<String, String> editarProducto(@PathVariable Long id, @RequestBody ProductoRequest p) {
        return productoService.editarProducto(id, p);
    }

    @PatchMapping
    public Map<String, String> desactivarProducto(Long productoId) {
        return productoService.desactivarProducto(productoId);
    }

}
