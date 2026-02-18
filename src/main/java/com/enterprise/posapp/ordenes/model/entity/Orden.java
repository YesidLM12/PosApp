package com.enterprise.posapp.ordenes.model.entity;

import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ordenes")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String estado;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime closed_at;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    private Mesas mesa;

    @ManyToOne
    @JoinColumn(name = "mesero_id")
    private Usuarios usuario;

   @OneToMany(mappedBy = "orden")
    private List<OrdenItem> items;
}
