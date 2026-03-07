package com.enterprise.posapp.cocina.repository;

import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class TicketRepositoryJpa implements TicketRepository {
    private final TicketJpaRepository ticketJpaRepository;

    @Override
    public void save(Ticket ticket) {
        ticketJpaRepository.save(ticket);
    }

    public Ticket findById(long ticketId) {
       return ticketJpaRepository.findById(ticketId)
               .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado"));
    }

    @Override
    public List<Ticket> findByEstado(EstadoTicket estado) {
        return ticketJpaRepository.findTicketsByEstado(estado);
    }

    @Override
    public Optional<Ticket> findByOrdenId(Long ordenId) {
        return Optional.of(ticketJpaRepository.findByOrdenId(ordenId)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado")));
    }
}
