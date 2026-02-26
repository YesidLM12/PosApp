package com.enterprise.posapp.cocina.repository;

import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketJpaRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findTicketsByEstado(EstadoTicket estado);
}
