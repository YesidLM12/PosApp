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
@Tag(name = "Ordenes" , description = "Gestión de ordenes y envío de tickets a cocina")
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
    public Map<String, String> crearOrden (@RequestBody OrdenRequest dto) {
        ordenService.crearOrden(dto);
        return Map.of("Mensaje", "Orden creada");
    }

    @PatchMapping("{ordenId}")
    @Operation(summary = "Modificar orden", description = "Modifica la cantidad de productos y/o elimina productos ")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Item modificado"),
            @ApiResponse(responseCode = "404", description = "Recurso no encontrado")
    })
    public void modificarOrden(@PathVariable Long ordenId ,@RequestBody List<OrdenItemRequest> dto) {
        ordenService.modificarOrden(ordenId,dto);
    }
}
