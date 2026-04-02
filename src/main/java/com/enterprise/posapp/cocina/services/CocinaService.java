package com.enterprise.posapp.cocina.services;

import com.enterprise.posapp.cocina.dto.ItemTicketResponse;
import com.enterprise.posapp.cocina.dto.TicketResponse;
import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.cocina.repository.TicketRepositoryJpa;
import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CocinaService {
    private final OrdenRepositoryJpa ordenRepositoryJpa;
    private final TicketRepositoryJpa ticketRepositoryJpa;

    @PreAuthorize("hasRole('COCINA')")
    public List<TicketResponse> obtenerPendientes() {
        return ticketRepositoryJpa.findByEstado(EstadoTicket.PENDIENTE).stream()
                .map(t -> new TicketResponse(
                        t.getOrden().getCreated_at(),
                        t.getOrden().getMesa().getNumero(),
                        t.getOrden().getUsuario().getUsername(),
                        t.getOrden().getItems().stream()
                                .map(it -> new ItemTicketResponse(
                                        it.getProducto().getNombre(),
                                        it.getCantidad(),
                                        it.getObservacion()
                                )).toList()
                )).toList();
    }

    @Transactional
    public void marcarComoListo(Long ticketId) {
        Ticket ticket = ticketRepositoryJpa.findById(ticketId);

        if (ticket.getEstado() == EstadoTicket.LISTO) {
            throw new ConflicException("El ticket ya está listo");
        }

        ticket.setEstado(EstadoTicket.LISTO);

        Orden orden = ordenRepositoryJpa.findById(ticket.getOrden().getId());

        if (orden.getEstado() != EstadoOrden.EN_PREPARACION) {
            throw new ConflicException("La orden no está en preparación");
        }

        orden.setEstado(EstadoOrden.SERVIDA);

    }

}
