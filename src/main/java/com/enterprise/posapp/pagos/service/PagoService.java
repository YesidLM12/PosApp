package com.enterprise.posapp.pagos.service;

import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import com.enterprise.posapp.pagos.dto.request.PagoRequest;
import com.enterprise.posapp.pagos.model.entity.Pagos;
import com.enterprise.posapp.pagos.model.enums.Metodo;
import com.enterprise.posapp.pagos.repository.PagoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepositoryJpa pagoRepositoryJpa;
    private final OrdenRepositoryJpa ordenRepositoryJpa;

    @Transactional
    public void pagarOrden(PagoRequest dto) {
        Orden orden = ordenRepositoryJpa.findById(dto.ordenId());

        Pagos pago = Pagos.builder()
                .orden(orden)
                .metodo(dto.metodoPago() != null ? dto.metodoPago() : Metodo.EFECTIVO)
                .monto(dto.monto())
                .created_at(LocalDateTime.now())
                .build();

        BigDecimal restante = orden.getTotal().subtract(pago.getMonto());

        if ( restante.compareTo(BigDecimal.ZERO) <= 0) {
            orden.setEstado(EstadoOrden.CERRADA);
            ordenRepositoryJpa.save(orden);
        }

        pagoRepositoryJpa.save(pago);
    }
}
