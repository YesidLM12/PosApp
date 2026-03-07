package com.enterprise.posapp.ordenes.model.entity;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.pagos.model.entity.Pagos;
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
    @Enumerated(EnumType.STRING)
    private EstadoOrden estado;

    @Column(nullable = false)
    private BigDecimal total;

    @Column(nullable = false)
    private LocalDateTime created_at;

    @Column(nullable = false)
    private LocalDateTime closed_at;

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesas mesa;


    @ManyToOne
    @JoinColumn(name = "mesero_id", nullable = false)
    private Usuarios usuario;

    @OneToMany(mappedBy = "orden", cascade = CascadeType.ALL)
    private List<OrdenItem> items;

    public void setTotal(BigDecimal total) {
        if (total.compareTo(BigDecimal.ZERO) < 0) {
            throw new ConflicException("El total no puede ser negativo");
        }
        this.total = total;
    }

    @OneToMany(mappedBy = "orden")
    private List<Pagos> pagos;

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
        if (estado.equals(EstadoOrden.CERRADA) || estado.equals(EstadoOrden.CANCELADA)) {
            throw new ConflicException("No se puede modificar ni abrir la orden en estado " + estado);
        }

        if (items.isEmpty())
            throw new ConflicException("La orden debe tener al menos un producto");

    }

    public void agregarItem(OrdenItem item) {
        items.add(item);
        item.setOrden(this);
    }

    public void cancelarOrden() {
        setEstado(EstadoOrden.CANCELADA);
        setClosed_at(LocalDateTime.now());
    }

    public void abrirOrden() {
        validarOrden();
        setEstado(EstadoOrden.ABIERTA);
    }

    public void validarCerrarOrden() {
        if (getItems().isEmpty())
            throw new ConflicException("No se puede cerrar una orden sin productos");

        if (estado.equals(EstadoOrden.CERRADA) || estado.equals(EstadoOrden.CANCELADA) || estado.equals(EstadoOrden.EN_PREPARACION)) {
            throw new ConflicException("No se puede cerrar la orden. Estado de la orden: " + estado);
        }

        setEstado(EstadoOrden.CERRADA);
        setClosed_at(LocalDateTime.now());
    }
}
