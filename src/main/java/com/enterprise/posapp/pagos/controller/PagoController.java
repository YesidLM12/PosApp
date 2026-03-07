package com.enterprise.posapp.pagos.controller;

import com.enterprise.posapp.pagos.dto.request.PagoRequest;
import com.enterprise.posapp.pagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/pago")
@RequiredArgsConstructor
@Tag(name = "Pagos", description = "Gestión de pagos")
public class PagoController {
    private final PagoService pagoService;

    @PostMapping
    @Operation(summary = "Pago de orden", description = "Recibe el pago de la orden, y cierra la orden")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pago realizado correctamente"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    public Map<String, String> pagarOrden(@RequestBody PagoRequest dto) {
        pagoService.pagarOrden(dto);
        return Map.of("Mensaje", "Orden pagada y cerrada correctamente");
    }

}
