package com.enterprise.ordenes;

import com.enterprise.posapp.common.exceptions.ConflicException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.model.enums.Estado;
import com.enterprise.posapp.mesas.repository.MesaRepositoryJpa;
import com.enterprise.posapp.ordenes.dto.request.OrdenItemRequest;
import com.enterprise.posapp.ordenes.dto.request.OrdenRequest;
import com.enterprise.posapp.ordenes.repository.OrdenItemRepositoryJpa;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import com.enterprise.posapp.ordenes.service.OrdenService;
import com.enterprise.posapp.productos.model.entity.Productos;
import com.enterprise.posapp.productos.repository.ProductoRepositoryJpa;
import com.enterprise.posapp.usuarios.repository.UsuarioRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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
    void noDebePermitirCrearOrdenSinMesa(){
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L);
        OrdenRequest dto = new OrdenRequest(1, "mesero1", List.of(item));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.OCUPADA);

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(null);

        assertThrows(ConflicException.class, () -> {
            ordenService.crearOrden(dto);
        });
        verify(ordenRepositoryJpa, never()).save(any());
    }

    @Test
    void noDebePermitirCrearOrdenSinMesero(){
        OrdenItemRequest item = new OrdenItemRequest(1L, 1, 1L);
        OrdenRequest dto = new OrdenRequest(1, "mesero1", List.of(item));

        Mesas mesa = new Mesas();
        mesa.setEstado(Estado.OCUPADA);

        when(mesaRepositoryJpa.findByNumberOfMesa(1))
                .thenReturn(mesa);

        when(usuarioRepositoryJpa.findByUsername("mesero1"))
                .thenReturn(null);

        assertThrows(ConflicException.class, () -> {
            ordenService.crearOrden(dto);
        });
        verify(ordenRepositoryJpa, never()).save(any());
    }
}
