package com.enterprise.posapp.ordenes.dto.response;

import java.math.BigDecimal;

public record OrdenItemResponse(
        String producto,
        int cantidad,
        BigDecimal precio_unitario
) {
}
