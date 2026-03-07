package com.enterprise.posapp.ordenes.dto.request;

public record OrdenItemRequest(
        Long ordenId,
        int cantidad,
        Long productoId,
        String observacion
) {
}
