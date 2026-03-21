package com.enterprise.posapp.pagos.model.entity;

import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.pagos.model.enums.Metodo;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pagos")
public class Pagos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Metodo metodo;

    private BigDecimal monto;
    private LocalDateTime created_at;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;
}
