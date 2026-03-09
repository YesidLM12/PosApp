package com.enterprise.posapp.mesas.controller;

import com.enterprise.posapp.mesas.dto.response.MesaResponse;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.service.MesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mesas")
@RequiredArgsConstructor
@Tag(name = "Mesas", description = "Mostrar mesas")
public class MesaController {
    private final MesaService mesaService;

    @Operation(summary = "mostrar mesas")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "mesas mostradas")
    })
    @GetMapping
    public List<MesaResponse> getMesas() {
        return mesaService.getMesas();
    }

    @Operation(summary = "Mostrar mesa por número de mesa")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Mesa mostrada"),
            @ApiResponse(responseCode = "404", description = "Mesa no encontrada")
    })
    @GetMapping("/{nMesa}")
    public MesaResponse getMesaByNMesa(@PathVariable int nMesa){
        return mesaService.getMesaByNMesa(nMesa);
    }

}
