package com.enterprise.posapp.cocina.services;

import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.cocina.repository.TicketRepositoryJpa;
import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CocinaService {
    private final OrdenRepositoryJpa ordenRepositoryJpa;
    private final TicketRepositoryJpa ticketRepositoryJpa;

    public List<Ticket> obtenerPendientes() {
        return ticketRepositoryJpa.findByEstado(EstadoTicket.PENDIENTE);
    }

    @Transactional
    public void marcarComoListo(Long ticketId) {
        Ticket ticket = ticketRepositoryJpa.findById(ticketId);

        if (ticket.getEstado() == EstadoTicket.LISTO) {
            throw new ConflicException("El ticket ya está listo");
        }

        ticket.setEstado(EstadoTicket.LISTO);

        Orden orden = ordenRepositoryJpa.findById(ticket.getOrdenId());

        if (orden.getEstado() != EstadoOrden.EN_PREPARACION) {
            throw new ConflicException("La orden no está en preparación");
        }

        orden.setEstado(EstadoOrden.SERVIDA);

    }

}
