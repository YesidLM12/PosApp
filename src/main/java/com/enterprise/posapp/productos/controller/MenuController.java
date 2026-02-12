package com.enterprise.posapp.productos.controller;

import com.enterprise.posapp.productos.dto.response.ProductoResponse;
import com.enterprise.posapp.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Mostrar el menú", description = "Retorna todos los productos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "muestra los productos")
    })
    public Page<ProductoResponse> menu(Pageable pageable) {
        return productoService.obtenerTodosLosProductos(pageable);
    }
}
