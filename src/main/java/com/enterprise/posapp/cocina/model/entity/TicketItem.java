package com.enterprise.posapp.cocina.model.entity;

import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "ticketItem")
public class TicketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombreProducto;
    private int cantidad;
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public TicketItem(String nombre, int cantidad, String observacion) {
        this.nombreProducto = nombre;
        this.cantidad = cantidad;
        this.observacion = observacion;
    }

    public static TicketItem fromOrdenItem(OrdenItem item) {
        return TicketItem.builder()
                .nombreProducto(item.getProducto().getNombre())
                .cantidad(item.getCantidad())
                .observacion(item.getObservacion())
                .build();
    }

}
