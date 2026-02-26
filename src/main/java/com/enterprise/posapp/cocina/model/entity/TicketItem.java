package com.enterprise.posapp.cocina.model.entity;

import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TicketItem {
    private String nombreProducto;
    private int cantidad;
    private String observacion;

    public static TicketItem fromOrdenItem(OrdenItem item) {
        return TicketItem.builder()
                .nombreProducto(item.getProducto().getNombre())
                .cantidad(item.getCantidad())
                .observacion(item.getObservacion())
                .build();
    }

}
