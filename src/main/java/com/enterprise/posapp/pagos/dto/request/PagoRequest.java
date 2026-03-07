package com.enterprise.posapp.pagos.dto.request;

import com.enterprise.posapp.pagos.model.enums.Metodo;

import java.math.BigDecimal;

public record PagoRequest(
        Long ordenId,
        Metodo metodoPago,
        BigDecimal monto
) {
}
