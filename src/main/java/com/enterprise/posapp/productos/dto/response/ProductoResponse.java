package com.enterprise.posapp.productos.dto.response;

import java.math.BigDecimal;

public record ProductoResponse(
        String nombre,
        BigDecimal precio,
        String categoria,
        Boolean estado
) {
}
