package com.enterprise.posapp.ordenes.model.entity;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.productos.model.entity.Productos;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<OrdenItem> items;

    public void agregarOActualizarProducto(Productos producto, int cantidad) {
        abrirOrden();

        Optional<OrdenItem> itemExistente = items.stream()
                .filter(i -> i.getProducto().equals(producto))
                .findFirst();

        if (itemExistente.isPresent()) {
            OrdenItem item = itemExistente.get();
            item.actualizarCantidad(cantidad);

            if (item.getCantidad() <= 0) {
                items.remove(item);
            }
        } else {
            if (cantidad > 0) {
                OrdenItem nuevoItem = new OrdenItem(this, producto, cantidad);
                agregarItem(nuevoItem);
            }
        }
    }

    public void validarOrden() {
        if (estado.equals("CANCELADA") || estado.equals("CERRADA")) {
            throw new ConflicException("No se puede modificar ni abrir la orden");
        }
    }

    public void agregarItem(OrdenItem item) {
        items.add(item);
        item.setOrden(this);
    }

    public void cancelarOrden(){
        setEstado("CANCELADA");
    }

    public void abrirOrden(){
        validarOrden();

        setEstado("ABIERTA");
    }
}
