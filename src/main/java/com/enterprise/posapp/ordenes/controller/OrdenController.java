package com.enterprise.posapp.ordenes.controller;

import com.enterprise.posapp.ordenes.dto.request.OrdenItemRequest;
import com.enterprise.posapp.ordenes.dto.request.OrdenRequest;
import com.enterprise.posapp.ordenes.service.OrdenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orden")
@RequiredArgsConstructor
@Tag(name = "Ordenes", description = "Gestión de ordenes y envío de tickets a cocina")
public class OrdenController {
    private final OrdenService ordenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear orden", description = "Crea la orden y envía el ticket a cocina")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Orden creada"),
            @ApiResponse(responseCode = "404", description = "producto no encontrado"),
            @ApiResponse(responseCode = "409", description = "Mesa no disponible")
    })
    public Map<String, String> crearOrden(@RequestBody OrdenRequest dto) {
        try {
            ordenService.crearOrden(dto);
        } catch (Exception e) {
            System.out.println("Error al crear orden: " + e.getMessage());
        }
        return Map.of("Mensaje", "Orden creada");
    }

    @PatchMapping("/{ordenId}")
    @Operation(summary = "Modificar orden", description = "Modifica la cantidad de productos y/o elimina productos ")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Item modificado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    public void modificarOrden(@PathVariable Long ordenId, @RequestBody List<OrdenItemRequest> dto) {
        try {
            ordenService.modificarOrden(ordenId, dto);
        } catch (Exception e) {
            System.out.println("Error al modificar orden: " + e.getMessage());
        }
    }

    @PutMapping("/{ordenId}")
    @Operation(summary = "Cancelar orden", description = "Cancela la orden y mantiene el historial")
    @ApiResponses({
            @ApiResponse(responseCode = "404", description = "Orden no encontrada"),
            @ApiResponse(responseCode = "200", description = "Orden cancelada")
    })
    public Map<String, String> cancelarOrden(@PathVariable Long ordenId) {
        try {
            ordenService.cancelarOrden(ordenId);
        } catch (Exception e) {
            System.out.println("Error al cancelar orden: " + e.getMessage());
        }
        return Map.of("Mensaje", "Orden cancelada");
    }

}
