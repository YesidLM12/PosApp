package com.enterprise.posapp.pagos.dto.response;

import java.math.BigDecimal;

public record PagoResponse(
        BigDecimal cambio,
        BigDecimal faltante
) {
}
