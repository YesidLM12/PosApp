package com.enterprise.posapp.productos.controller;

import com.enterprise.posapp.productos.dto.request.ProductoRequest;
import com.enterprise.posapp.productos.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/producto")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "Gestión de productos (crear, editar, desactivar)")
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping
    @Operation(summary = "Agrega productos", description = "permite agregar productos al menú activos automáticamente")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "producto creado"),
            @ApiResponse(responseCode = "409", description = "Producto existente")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> agregarProducto(@RequestBody ProductoRequest p) {
        return productoService.crearProducto(p);
    }

    @PutMapping
    @Operation(summary = "Editar producto", description = "Permite editar productos de forma parcial o completa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "producto actualizado"),
            @ApiResponse(responseCode = "404", description = "producto no encontrado")
    })
    public Map<String, String> editarProducto(@PathVariable Long id, @RequestBody ProductoRequest p) {
        return productoService.editarProducto(id, p);
    }

    @PatchMapping
    @Operation(summary = "Desactivar productos", description = "Permite cambiar el estado de un producto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "producto desactivado"),
            @ApiResponse(responseCode = "404", description = "producto no encontrado")
    })
    public Map<String, String> desactivarProducto(Long productoId) {
        return productoService.desactivarProducto(productoId);
    }

}
