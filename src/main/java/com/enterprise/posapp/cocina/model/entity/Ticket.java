package com.enterprise.posapp.cocina.model.entity;


import com.enterprise.posapp.cocina.dto.TicketResponse;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;

    @Enumerated(EnumType.STRING)
    private EstadoTicket estado;

    private LocalDateTime created_at;
}
