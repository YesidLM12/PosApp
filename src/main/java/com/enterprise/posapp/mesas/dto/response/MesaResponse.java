package com.enterprise.posapp.mesas.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Dto para mostrar información de la mesa")
public record MesaResponse(

        @Schema(description = "Número de la mesa", example = "23")
        int numero,

        @Schema(description = "Estado de la mesa", example = "Disponible")
        com.enterprise.posapp.mesas.model.enums.Estado estado,

        @Schema(description = "fecha de creación de mesa", defaultValue = "timeStap")
        LocalDateTime created_at
) {
}
