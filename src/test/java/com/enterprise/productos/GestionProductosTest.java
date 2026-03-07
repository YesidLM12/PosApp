package com.enterprise.productos;

import com.enterprise.posapp.productos.repository.CategoriaRepositoryJpa;
import com.enterprise.posapp.productos.repository.ProductoRepositoryJpa;
import com.enterprise.posapp.productos.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GestionProductosTest {
    @Mock
    private ProductoRepositoryJpa productoRepositoryJpa;

    @Mock
    private CategoriaRepositoryJpa categoriaRepositoryJpa;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void noDebePermitirAgregarProductosInactivos() {

    }

}
