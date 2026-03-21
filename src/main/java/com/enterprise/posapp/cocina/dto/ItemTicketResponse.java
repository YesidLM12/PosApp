package com.enterprise.posapp.cocina.dto;

public record ItemTicketResponse(
        String producto,
        int cantidad,
        String observacion
) {
}
