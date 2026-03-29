package com.enterprise.ordenes;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.model.enums.Estado;
import com.enterprise.posapp.mesas.repository.MesaRepositoryJpa;
import com.enterprise.posapp.ordenes.dto.request.OrdenItemRequest;
import com.enterprise.posapp.ordenes.dto.request.OrdenRequest;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
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
import org.springframework.context.ApplicationEventPublisher;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private OrdenService ordenService;

    @Test
    void noDebePermitirCrearOrdenSinMesa() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L, "");
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
                () -> ordenService.crearOrden(dto));
        System.out.println(exception.getMessage());
        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void noDebePermitirCrearOrdenSinMesero() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L, "");
        OrdenRequest dto = new OrdenRequest(1, "mesero1", List.of(item));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.OCUPADA);

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(mesa);

        when(usuarioRepositoryJpa.findByUsername("mesero1"))
                .thenReturn(null);

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.crearOrden(dto));
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
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L, "");

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
                .thenReturn(producto);

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.modificarOrden(orden.getId(), List.of(item)));

        System.out.println(exception.getMessage());
        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void noDebePermitirReabrirOrdenCancelada() {

        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L, "");

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
                .thenReturn(producto);

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.modificarOrden(orden.getId(), List.of(item)));

        System.out.println(exception.getMessage());

        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void noDebePermitirAgregarProductosInactivos() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L, "");

        OrdenRequest ordenRequest = new OrdenRequest(1, "mesero1", List.of(item));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.DISPONIBLE);

        Usuarios mesero = new Usuarios();
        mesero.setUsername("mesero1");

        Productos producto = new Productos();
        producto.setId(1L);
        producto.setActivo(false);

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(mesa);

        when(usuarioRepositoryJpa.findByUsername("mesero1"))
                .thenReturn(mesero);

        when(productoRepositoryJpa.findById(1L))
                .thenReturn(producto);

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.crearOrden(ordenRequest));
        System.out.println(exception.getMessage());
        verify(productoRepositoryJpa, never()).save(any());
    }

    @Test
    void debeCalcularCorrectamenteTotales() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L, "");

        OrdenItemRequest item2 = new OrdenItemRequest(1L, 1, 2L, "");

        OrdenRequest ordenRequest = new OrdenRequest(1, "mesero1", List.of(item, item2));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.DISPONIBLE);
        mesa.setNumero(1);

        Usuarios mesero = new Usuarios();
        mesero.setUsername("mesero1");

        Productos producto1 = new Productos();
        producto1.setId(1L);
        producto1.setActivo(true);
        producto1.setPrecio(BigDecimal.valueOf(20000));

        Productos producto2 = new Productos();
        producto2.setId(2L);
        producto2.setActivo(true);
        producto2.setPrecio(BigDecimal.valueOf(30000));

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(mesa);

        when(usuarioRepositoryJpa.findByUsername("mesero1"))
                .thenReturn(mesero);

        when(productoRepositoryJpa.findById(1L))
                .thenReturn(producto1);

        when(productoRepositoryJpa.findById(2L))
                .thenReturn(producto2);

        Orden orden = ordenService.crearOrden(ordenRequest);

        assertEquals(BigDecimal.valueOf(50000), orden.getTotal());

        verify(ordenRepositoryJpa).save(any(Orden.class));
    }

    @Test
    void debeRecalcularTotalesCuandoSeActualizaOrden() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L, "");
        OrdenItemRequest item2 = new OrdenItemRequest(1L, 1, 2L, "");
        OrdenItemRequest item3 = new OrdenItemRequest(1L, 1, 3L, "");

        OrdenRequest ordenRequest = new OrdenRequest(1, "mesero1", List.of(item, item2));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.DISPONIBLE);
        mesa.setNumero(1);

        Usuarios mesero = new Usuarios();
        mesero.setUsername("mesero1");

        Productos producto1 = new Productos();
        producto1.setId(1L);
        producto1.setActivo(true);
        producto1.setPrecio(BigDecimal.valueOf(20000));

        Productos producto2 = new Productos();
        producto2.setId(2L);
        producto2.setActivo(true);
        producto2.setPrecio(BigDecimal.valueOf(30000));

        Productos producto3 = new Productos();
        producto3.setId(3L);
        producto3.setActivo(true);
        producto3.setPrecio(BigDecimal.valueOf(40000));

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(mesa);

        when(usuarioRepositoryJpa.findByUsername("mesero1"))
                .thenReturn(mesero);

        when(productoRepositoryJpa.findById(1L))
                .thenReturn(producto1);

        when(productoRepositoryJpa.findById(2L))
                .thenReturn(producto2);

        when(productoRepositoryJpa.findById(3L))
                .thenReturn(producto3);

        Orden orden = ordenService.crearOrden(ordenRequest);
        orden.setId(1L);

        when(ordenRepositoryJpa.findById(1L))
                .thenReturn(orden);

        ordenService.modificarOrden(orden.getId(), List.of(item3));

        assertEquals(BigDecimal.valueOf(90000), orden.getTotal());

        verify(ordenRepositoryJpa, times(2)).save(any(Orden.class));
        System.out.println("Test completado!");
    }

    @Test
    void debeRecalcularTotalEliminarProducto() {
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L, "");
        OrdenItemRequest item2 = new OrdenItemRequest(1L, 0, 2L, "");

        OrdenRequest ordenRequest = new OrdenRequest(1, "mesero1", List.of(item, item2));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.DISPONIBLE);
        mesa.setNumero(1);

        Usuarios mesero = new Usuarios();
        mesero.setUsername("mesero1");

        Productos producto1 = new Productos();
        producto1.setId(1L);
        producto1.setActivo(true);
        producto1.setPrecio(BigDecimal.valueOf(20000));

        Productos producto2 = new Productos();
        producto2.setId(2L);
        producto2.setActivo(true);
        producto2.setPrecio(BigDecimal.valueOf(30000));

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(mesa);

        when(usuarioRepositoryJpa.findByUsername("mesero1"))
                .thenReturn(mesero);

        when(productoRepositoryJpa.findById(1L))
                .thenReturn(producto1);

        when(productoRepositoryJpa.findById(2L))
                .thenReturn(producto2);

        Orden orden = ordenService.crearOrden(ordenRequest);
        orden.setId(1L);

        when(ordenRepositoryJpa.findById(1L))
                .thenReturn(orden);

        ordenService.modificarOrden(orden.getId(), List.of(item2));

        assertEquals(BigDecimal.valueOf(20000), orden.getTotal());

        verify(ordenRepositoryJpa, times(2)).save(any(Orden.class));
        System.out.println("Test completado!");
    }

    @Test
    void debeRecalcularElTotalOrdenCambiaCantidad() {
        Usuarios mesero = new Usuarios();
        mesero.setUsername("mesero1");

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.DISPONIBLE);
        mesa.setNumero(1);

        Productos producto1 = new Productos();
        producto1.setId(1L);
        producto1.setActivo(true);
        producto1.setPrecio(BigDecimal.valueOf(20000));

        Productos producto2 = new Productos();
        producto2.setId(2L);
        producto2.setActivo(true);
        producto2.setPrecio(BigDecimal.valueOf(30000));

        Productos producto3 = new Productos();
        producto3.setId(3L);
        producto3.setActivo(true);
        producto3.setPrecio(BigDecimal.valueOf(40000));

        OrdenItem item = new OrdenItem();
        item.setId(1L);
        item.setCantidad(1);
        item.setProducto(producto1);
        item.setObservacion("");

        OrdenItem item2 = new OrdenItem();
        item2.setId(2L);
        item2.setCantidad(1);
        item2.setProducto(producto2);
        item2.setObservacion("");

        Orden orden = Orden.builder()
                .id(1L)
                .usuario(mesero)
                .estado(EstadoOrden.ABIERTA)
                .mesa(mesa)
                .items(List.of(item, item2))
                .created_at(LocalDateTime.now())
                .build();

        when(ordenRepositoryJpa.findById(1L))
                .thenReturn(orden);

        when(productoRepositoryJpa.findById(2L))
                .thenReturn(producto2);

        OrdenItemRequest itemR = new OrdenItemRequest(1L, 1, producto2.getId(), "");

        ordenService.modificarOrden(orden.getId(), List.of(itemR));

        assertEquals(BigDecimal.valueOf(80000), orden.getTotal());

        verify(ordenRepositoryJpa).save(any(Orden.class));
    }

    @Test
    void noDebeAceptarTotalesNegativos() {
        OrdenItemRequest item = new OrdenItemRequest(1L, -1, 1L, "");

        OrdenRequest ordenRequest = new OrdenRequest(1, "mesero1", List.of(item));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.DISPONIBLE);

        Usuarios mesero = new Usuarios();
        mesero.setUsername("mesero1");

        Productos producto = new Productos();
        producto.setId(1L);
        producto.setActivo(true);
        producto.setPrecio(BigDecimal.valueOf(20000));

        when(mesaRepositoryJpa.findByNumberOfMesa(1)).thenReturn(mesa);
        when(usuarioRepositoryJpa.findByUsername("mesero1")).thenReturn(mesero);
        when(productoRepositoryJpa.findById(1L)).thenReturn(producto);

        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> ordenService.crearOrden(ordenRequest));

        System.out.println(exception.getMessage());

        verify(ordenRepositoryJpa, never()).save(any());
    }
}
