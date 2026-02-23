package com.enterprise.ordenes;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.model.enums.Estado;
import com.enterprise.posapp.mesas.repository.MesaRepositoryJpa;
import com.enterprise.posapp.ordenes.dto.request.OrdenItemRequest;
import com.enterprise.posapp.ordenes.dto.request.OrdenRequest;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenItemRepositoryJpa;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import com.enterprise.posapp.ordenes.service.OrdenService;
import com.enterprise.posapp.productos.model.entity.Productos;
import com.enterprise.posapp.productos.repository.ProductoRepositoryJpa;
import com.enterprise.posapp.usuarios.model.entity.Usuarios;
import com.enterprise.posapp.usuarios.repository.UsuarioRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GestionOrdenesTest {
    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @Mock
    private MesaRepositoryJpa mesaRepositoryJpa;

    @Mock
    private OrdenRepositoryJpa ordenRepositoryJpa;

    @Mock
    private ProductoRepositoryJpa productoRepositoryJpa;

    @Mock
    private OrdenItemRepositoryJpa ordenItemRepositoryJpa;
    @InjectMocks
    private OrdenService ordenService;

    @Test
    void noDebePermitirCrearOrdenSinMesa() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L);
        OrdenRequest dto = new OrdenRequest(1, "mesero1", List.of(item));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.OCUPADA);

        Usuarios mesero = new Usuarios();
        mesero.setUsername("mesero1");

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(null);

        when(usuarioRepositoryJpa.findByUsername("mesero1"))
                .thenReturn(mesero);

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.crearOrden(dto)
        );
        System.out.println(exception.getMessage());
        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void noDebePermitirCrearOrdenSinMesero() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L);
        OrdenRequest dto = new OrdenRequest(1, "mesero1", List.of(item));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.OCUPADA);

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(mesa);

        when(usuarioRepositoryJpa.findByUsername("mesero1"))
                .thenReturn(null);

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.crearOrden(dto)
        );
        System.out.println(exception.getMessage());
        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void noDebePermitirCerrarOrdenSinProductos() {
        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.OCUPADA);

        Orden orden = new Orden();
        orden.setId(1L);
        orden.setEstado(EstadoOrden.SERVIDA);
        orden.setMesa(mesa);
        orden.setItems(Collections.emptyList());

        when(ordenRepositoryJpa.findById(1))
                .thenReturn(orden);

        assertThrows(ConflicException.class, () -> {
            ordenService.cerrarOrden(1L);
        });
        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void noDebePermitirModificarOrdenEstadoCerrada() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L);
        OrdenRequest dto = new OrdenRequest(1, "mesero1", List.of(item));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.OCUPADA);

        Productos producto = new Productos();
        producto.setId(1);

        Orden orden = new Orden();
        orden.setId(1L);
        orden.setEstado(EstadoOrden.CERRADA);
        orden.setMesa(mesa);
        orden.setItems(Collections.emptyList());

        when(ordenRepositoryJpa.findById(item.ordenId()))
                .thenReturn(orden);

        when(productoRepositoryJpa.findById(item.productoId()))
                .thenReturn(Optional.of(producto));

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.modificarOrden(item)
        );

        System.out.println(exception.getMessage());
        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void noDebePermitirReabrirOrdenCancelada() {

        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L);

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.OCUPADA);

        Productos producto = new Productos();
        producto.setId(1);

        Orden orden = new Orden();
        orden.setId(1L);
        orden.setEstado(EstadoOrden.CANCELADA);
        orden.setMesa(mesa);
        orden.setItems(new ArrayList<>());

        when(ordenRepositoryJpa.findById(item.ordenId()))
                .thenReturn(orden);

        when(productoRepositoryJpa.findById(item.productoId()))
                .thenReturn(Optional.of(producto));

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.modificarOrden(item)
        );

        System.out.println(exception.getMessage());

        verify(ordenRepositoryJpa, never()).save(any());
    }
}
