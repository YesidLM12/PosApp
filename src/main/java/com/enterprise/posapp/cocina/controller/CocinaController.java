package com.enterprise.posapp.cocina.controller;

import com.enterprise.posapp.cocina.dto.TicketResponse;
import com.enterprise.posapp.cocina.services.CocinaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cocina")
@RequiredArgsConstructor
@Tag(name = "Cocina", description = "Gestión de ordenes de cocina")
public class CocinaController {
    private final CocinaService cocinaService;

    @GetMapping
    @Operation(summary = "Ordenes pendientes", description = "Retorna los ordenes pendientes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ordenes mostradas")
    })
    public List<TicketResponse> obtenerOrdenesPendientes() {
        return cocinaService.obtenerPendientes();
    }

    @PutMapping("/{ticketId}")
    @Operation(summary = "Marcar producto como listo")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto listo"),
            @ApiResponse(responseCode = "404", description = "Ticket no encontrado")
    })
    public void productoListo(@PathVariable Long ticketId) {
        cocinaService.marcarComoListo(ticketId);
    }
}
