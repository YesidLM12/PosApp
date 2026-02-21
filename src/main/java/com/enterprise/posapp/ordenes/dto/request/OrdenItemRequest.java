package com.enterprise.posapp.ordenes.dto.request;

import com.enterprise.posapp.productos.model.entity.Productos;

import java.math.BigDecimal;

public record OrdenItemRequest(
        Long ordenId,
        int cantidad,
        Productos productos
) {
}
