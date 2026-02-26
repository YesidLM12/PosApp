package com.enterprise.posapp.cocina.listener;

import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.repository.TicketRepositoryJpa;
import com.enterprise.posapp.ordenes.events.OrdenEnviadaACocinaEvent;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class CocinaListener {
    private final OrdenRepositoryJpa ordenRepositoryJpa;
    private final TicketRepositoryJpa ticketRepositoryJpa;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void manejarOrdenEnviada (OrdenEnviadaACocinaEvent event) {
        Orden orden = ordenRepositoryJpa.findById(event.ordenId());
        Ticket ticket = Ticket.fromOrden(orden);
        ticketRepositoryJpa.save(ticket);
        orden.setEstado(EstadoOrden.EN_PREPARACION);
    }
}
