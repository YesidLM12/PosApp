package com.enterprise.posapp.pagos.service;

import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.cocina.repository.TicketRepositoryJpa;
import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.model.enums.Estado;
import com.enterprise.posapp.mesas.repository.MesaRepositoryJpa;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import com.enterprise.posapp.pagos.dto.request.PagoRequest;
import com.enterprise.posapp.pagos.dto.response.PagoResponse;
import com.enterprise.posapp.pagos.model.entity.Pagos;
import com.enterprise.posapp.pagos.model.enums.Metodo;
import com.enterprise.posapp.pagos.repository.PagoRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepositoryJpa pagoRepositoryJpa;
    private final OrdenRepositoryJpa ordenRepositoryJpa;
    private final MesaRepositoryJpa mesaRepositoryJpa;
    private final TicketRepositoryJpa ticketRepositoryJpa;

    @PreAuthorize("hashRole('CAJERO')")
    @Transactional
    public PagoResponse pagarOrden(PagoRequest dto) {
        Orden orden = ordenRepositoryJpa.findById(dto.ordenId());

        Pagos pago = Pagos.builder()
                .orden(orden)
                .metodo(dto.metodoPago() != null ? dto.metodoPago() : Metodo.EFECTIVO)
                .monto(dto.monto())
                .created_at(LocalDateTime.now())
                .build();

        pagoRepositoryJpa.save(pago);

        BigDecimal totalPagado = pagoRepositoryJpa.sumByOrdenId(orden.getId());

        BigDecimal restante = totalPagado.subtract(orden.getTotal());

        if (restante.compareTo(BigDecimal.ZERO) >= 0) {
            orden.setEstado(EstadoOrden.CERRADA);
            orden.setClosed_at(LocalDateTime.now());
            ordenRepositoryJpa.save(orden);

            Mesas mesa = orden.getMesa();
            mesa.setEstado(Estado.DISPONIBLE);
            mesaRepositoryJpa.save(mesa);

            Ticket ticket = ticketRepositoryJpa.findByOrdenId(orden.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado"));
            ticket.setEstado(EstadoTicket.LISTO);
            ticketRepositoryJpa.save(ticket);
        }

        return new PagoResponse(
                restante.compareTo(BigDecimal.ZERO) > 0 ? restante : BigDecimal.ZERO, // cambio
                restante.compareTo(BigDecimal.ZERO) < 0 ? restante.abs() : BigDecimal.ZERO // falta
        );
    }
}
