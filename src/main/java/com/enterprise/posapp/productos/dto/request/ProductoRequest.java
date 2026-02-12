package com.enterprise.posapp.productos.dto.request;

import java.math.BigDecimal;

public record ProductoRequest(
        String nombre,
        String categoria,
        BigDecimal precio
) {
}
