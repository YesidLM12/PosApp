package com.enterprise.posapp.ordenes.service;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.model.enums.Estado;
import com.enterprise.posapp.mesas.repository.MesaRepositoryJpa;
import com.enterprise.posapp.ordenes.dto.request.OrdenItemRequest;
import com.enterprise.posapp.ordenes.dto.request.OrdenRequest;
import com.enterprise.posapp.ordenes.events.OrdenEnviadaACocinaEvent;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import com.enterprise.posapp.productos.model.entity.Productos;
import com.enterprise.posapp.productos.repository.ProductoRepositoryJpa;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {
    private final OrdenRepositoryJpa ordenRepositoryJpa;
    private final MesaRepositoryJpa mesaRepositoryJpa;
    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final ProductoRepositoryJpa productoRepositoryJpa;
    private final ApplicationEventPublisher eventPublisher;

    @PreAuthorize("hashAnyRole('MESERO', 'CAJERO')")
    @Transactional
    public Orden crearOrden(OrdenRequest dto) {
        List<OrdenItem> items = new ArrayList<>();

        Usuarios mesero = usuarioRepositoryJpa.findByUsername(dto.mesero());
        Mesas mesa = mesaRepositoryJpa.findByNumberOfMesa(dto.mesa());

        if (mesero == null)
            throw new ConflicException("Mesero no existe");

        if (mesa == null) {
            throw new ConflicException("Mesa no existe");
        }

        if (mesa.getEstado() != Estado.DISPONIBLE) {
            throw new ConflicException("Mesa no disponible");
        }

        Orden orden = Orden
                .builder()
                .created_at(LocalDateTime.now())
                .usuario(mesero)
                .mesa(mesa)
                .estado(EstadoOrden.ABIERTA)
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (OrdenItemRequest it : dto.items()) {
            Productos producto = productoRepositoryJpa.findById(it.productoId());

            if (!producto.isActivo()) {
                throw new ConflicException("Producto inactivo: " + producto.getNombre());
            }

            OrdenItem item = OrdenItem.builder()
                    .orden(orden)
                    .producto(producto)
                    .observacion(it.observacion())
                    .cantidad(it.cantidad())
                    .build();

            total = total.add(item.calcularSubtotal());
            items.add(item);
        }

        orden.setItems(items);
        orden.setTotal(total);
        mesa.setEstado(Estado.OCUPADA);

        ordenRepositoryJpa.save(orden);

        eventPublisher.publishEvent(
                new OrdenEnviadaACocinaEvent(orden)
        );
        return orden;
    }

    @PreAuthorize("hashAnyRole('MESERO', 'CAJERO')")
    @Transactional
    public void modificarOrden(Long ordenId, List<OrdenItemRequest> items) {
        Orden orden = ordenRepositoryJpa.findById(ordenId);

        for (OrdenItemRequest o : items) {
            Productos producto = productoRepositoryJpa.findById(o.productoId());
            orden.agregarOActualizarProducto(producto, o.cantidad());
        }

        orden.setEstado(EstadoOrden.EN_PREPARACION);
        ordenRepositoryJpa.save(orden);
    }

    @PreAuthorize("hashAnyRole('MESERO', 'CAJERO')")
    @Transactional
    public void cancelarOrden(Long ordenId) {
        Orden orden = ordenRepositoryJpa.findById(ordenId);
        orden.cancelarOrden();
        ordenRepositoryJpa.save(orden);
    }

    @PreAuthorize("hashAnyRole('MESERO', 'CAJERO')")
    @Transactional
    public void cerrarOrden(Long ordenId) {
        Orden orden = ordenRepositoryJpa.findById(ordenId);
        orden.validarCerrarOrden();
        ordenRepositoryJpa.save(orden);
    }
}
