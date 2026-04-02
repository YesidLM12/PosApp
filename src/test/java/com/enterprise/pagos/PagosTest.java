package com.enterprise.pagos;

import com.enterprise.posapp.cocina.model.entity.Ticket;
import com.enterprise.posapp.cocina.model.enums.EstadoTicket;
import com.enterprise.posapp.cocina.repository.TicketRepositoryJpa;
import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.model.enums.Estado;
import com.enterprise.posapp.mesas.repository.MesaRepositoryJpa;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenItemRepositoryJpa;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import com.enterprise.posapp.ordenes.service.OrdenService;
import com.enterprise.posapp.pagos.dto.request.PagoRequest;
import com.enterprise.posapp.pagos.dto.response.PagoResponse;
import com.enterprise.posapp.pagos.model.enums.Metodo;
import com.enterprise.posapp.pagos.repository.PagoRepositoryJpa;
import com.enterprise.posapp.pagos.service.PagoService;
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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PagosTest {
    @Mock
    private UsuarioRepositoryJpa usuarioRepositoryJpa;

    @Mock
    private MesaRepositoryJpa mesaRepositoryJpa;

    @Mock
    private OrdenRepositoryJpa ordenRepositoryJpa;

    @Mock
    private PagoRepositoryJpa pagoRepositoryJpa;

    @Mock
    private ProductoRepositoryJpa productoRepositoryJpa;

    @Mock
    private OrdenItemRepositoryJpa ordenItemRepositoryJpa;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Mock
    private TicketRepositoryJpa ticketRepositoryJpa;

    @InjectMocks
    private OrdenService ordenService;

    @InjectMocks
    private PagoService pagoService;

    @Test
    void noDebePermitirPagoSiOrdenNoEstaEnEstadoValido(){
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
                .estado(EstadoOrden.CERRADA)
                .mesa(mesa)
                .items(List.of(item, item2))
                .created_at(LocalDateTime.now())
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .orden(orden)
                .estado(EstadoTicket.LISTO)
                .build();

        PagoRequest pago = new PagoRequest(orden.getId(), Metodo.EFECTIVO, BigDecimal.valueOf(50000));

        when(ordenRepositoryJpa.findById(1L))
                .thenReturn(orden);


        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> pagoService.pagarOrden(pago));
        System.out.println(exception.getMessage());

        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void debeRechazarPagoInsuficiente(){
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
                .estado(EstadoOrden.SERVIDA)
                .mesa(mesa)
                .items(List.of(item, item2))
                .created_at(LocalDateTime.now())
                .total(BigDecimal.valueOf(50000))
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .orden(orden)
                .estado(EstadoTicket.LISTO)
                .build();

        PagoRequest pago = new PagoRequest(orden.getId(), Metodo.EFECTIVO, BigDecimal.valueOf(50000));

        when(ordenRepositoryJpa.findById(1L))
                .thenReturn(orden);

        when(pagoRepositoryJpa.sumByOrdenId(orden.getId()))
                .thenReturn(BigDecimal.valueOf(45000));


        ConflicException exception = assertThrows(
                ConflicException.class,
                () -> pagoService.pagarOrden(pago));
        System.out.println(exception.getMessage());

        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void debeCalcularCorrectamenteCambio() {
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
                .estado(EstadoOrden.SERVIDA)
                .mesa(mesa)
                .items(List.of(item, item2))
                .created_at(LocalDateTime.now())
                .total(BigDecimal.valueOf(50000))
                .build();

        Ticket ticket = Ticket.builder()
                .id(1L)
                .orden(orden)
                .estado(EstadoTicket.LISTO)
                .build();

        PagoRequest pago = new PagoRequest(orden.getId(), Metodo.EFECTIVO, BigDecimal.valueOf(60000));

        when(ordenRepositoryJpa.findById(1L))
                .thenReturn(orden);

        when(pagoRepositoryJpa.sumByOrdenId(orden.getId()))
                .thenReturn(BigDecimal.valueOf(60000));

        when(ticketRepositoryJpa.findByOrdenId(1L))
                .thenReturn(Optional.ofNullable(ticket));

        PagoResponse response = pagoService.pagarOrden(pago);

        assertEquals(BigDecimal.valueOf(10000), response.cambio());
    }

}
