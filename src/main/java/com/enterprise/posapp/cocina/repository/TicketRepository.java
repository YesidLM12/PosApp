package com.enterprise.posapp.cocina.repository;

import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface TicketRepository {
    void save (Ticket ticket);

    Ticket findById(long ticketId);

    List<Ticket> findByEstado(EstadoTicket estado);

    Optional<Ticket> findByOrdenId(Long ordenId);
}
