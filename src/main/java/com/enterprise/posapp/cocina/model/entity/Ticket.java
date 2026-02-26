package com.enterprise.posapp.cocina.model.entity;


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
    private Long ordenId;
    private List<TicketItem> items;
    private EstadoTicket estado;
    private LocalDateTime created_at;

    public static Ticket fromOrden(Orden orden) {
        List<TicketItem> items = orden.getItems().stream()
                .map(TicketItem::fromOrdenItem)
                .toList();

        return Ticket.builder()
                .ordenId(orden.getId())
                .estado(EstadoTicket.PENDIENTE)
                .created_at(LocalDateTime.now())
                .items(items)
                .build();
    }

    public void actualizarDesdeOrden(Orden orden) {
        this.items.clear();

        for (OrdenItem item : orden.getItems()) {
            TicketItem ticketItem = new TicketItem(
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.getObservacion()
            );

            this.items.add(ticketItem);
        }

        this.estado = EstadoTicket.PENDIENTE;
    }
}
