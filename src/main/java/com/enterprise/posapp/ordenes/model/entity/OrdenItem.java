package com.enterprise.posapp.ordenes.model.entity;

import com.enterprise.posapp.productos.model.entity.Productos;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "orden_items")
public class OrdenItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int cantidad;

    @Column(nullable = false)
    private BigDecimal precio_unitario;

    @ManyToOne
    @JoinColumn(name = "producto_Id")
    private Productos producto;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;
}
