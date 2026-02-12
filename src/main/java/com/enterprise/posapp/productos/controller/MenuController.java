package com.enterprise.posapp.productos.controller;

import com.enterprise.posapp.productos.dto.response.ProductoResponse;
import com.enterprise.posapp.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menu")
@RequiredArgsConstructor
@Tag(name = "Menú", description = "Muestra todos los producto y productos disponibles")
public class MenuController {
    private final ProductoService productoService;

    @GetMapping
    public Page<ProductoResponse> menu(Pageable pageable) {
        return productoService.obtenerTodosLosProductos(pageable);
    }
}
