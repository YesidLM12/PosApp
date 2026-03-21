package com.enterprise.posapp.cocina.listener;

import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.cocina.repository.TicketRepositoryJpa;
import com.enterprise.posapp.ordenes.events.OrdenEnviadaACocinaEvent;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CocinaListener {
    private final TicketRepositoryJpa ticketRepositoryJpa;

    @EventListener
    public void manejarOrdenEnviada(OrdenEnviadaACocinaEvent event) {

        Orden orden = event.orden();
 
        Ticket ticket = Ticket
                .builder()
                .orden(orden)
                .estado(EstadoTicket.PENDIENTE)
                .created_at(LocalDateTime.now())
                .build();

        ticketRepositoryJpa.save(ticket);
    }
}
