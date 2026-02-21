package com.enterprise.posapp.ordenes.service;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.repository.MesaRepositoryJpa;
import com.enterprise.posapp.ordenes.dto.request.OrdenItemRequest;
import com.enterprise.posapp.ordenes.dto.request.OrdenRequest;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
import com.enterprise.posapp.ordenes.repository.OrdenItemRepositoryJpa;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import com.enterprise.posapp.productos.model.entity.Productos;
import com.enterprise.posapp.productos.repository.ProductoJpaRepository;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.UsuarioRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenService {
    private final OrdenRepositoryJpa ordenRepositoryJpa;
    private final OrdenItemRepositoryJpa ordenItemRepositoryJpa;
    private final MesaRepositoryJpa mesaRepositoryJpa;
    private final UsuarioRepositoryJpa usuarioRepositoryJpa;
    private final ProductoJpaRepository productoJpaRepository;

    List<OrdenItem> items = new ArrayList<>();

    @Transactional
    public void crearOrden(OrdenRequest dto) {
        Usuarios mesero = usuarioRepositoryJpa.findByUsername(dto.mesero());
        Mesas mesa = mesaRepositoryJpa.findByNumberOfMesa(dto.mesa());

        if (mesa.getEstado().equals("OCUPADA")) {
            throw new ConflicException("Mesa no disponible");
        }

        Orden orden = Orden
                .builder()
                .created_at(LocalDateTime.now())
                .usuario(mesero)
                .estado("Activa")
                .mesa(mesa)
                .build();


        for (OrdenItemRequest it : dto.items()) {
            OrdenItem item = OrdenItem.builder()
                    .orden(orden)
                    .producto(productoJpaRepository.findByNombre(it.productos().getNombre()))
                    .cantidad(it.cantidad())
                    .precio_unitario(it.productos().getPrecio())
                    .build();

            items.add(item);
            ordenItemRepositoryJpa.save(item);
        }

        orden.setItems(items);
        orden.setEstado("EN PREPARACIÓN");
        mesa.setEstado("OCUPADA");

        ordenRepositoryJpa.save(orden);
        mesaRepositoryJpa.save(mesa);
    }

    @Transactional
    public void modificarOrden(OrdenItemRequest item) {
        Orden orden = ordenRepositoryJpa.findById(item.ordenId());
        Productos producto = productoJpaRepository.findByNombre(item.productos().getNombre());

        orden.agregarOActualizarProducto(producto, item.cantidad());

        ordenRepositoryJpa.save(orden);
    }

    @Transactional
    public void cancelarOrden(Long ordenId) {
        Orden orden = ordenRepositoryJpa.findById(ordenId);
        orden.cancelarOrden();
        ordenRepositoryJpa.save(orden);
    }

}
