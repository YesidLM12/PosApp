package com.enterprise.posapp.mesas.model.entity;

import com.enterprise.posapp.mesas.model.enums.Estado;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "mesas")
public class Mesas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int numero;

    @Column(nullable = false, length = 30)
    private Estado estado;

    @Column(nullable = false)
    private LocalDateTime created_at = LocalDateTime.now();

    @OneToMany(mappedBy = "mesa", cascade = CascadeType.ALL)
    private List<Orden> ordenes;
}
