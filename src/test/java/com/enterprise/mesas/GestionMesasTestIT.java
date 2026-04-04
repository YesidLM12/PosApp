package com.enterprise.mesas;

import com.enterprise.base.IntegrationTestBase;
import com.enterprise.posapp.mesas.model.enums.Estado;
import com.enterprise.posapp.ordenes.model.entity.Orden;
import com.enterprise.posapp.ordenes.model.enums.EstadoOrden;
import com.enterprise.posapp.ordenes.repository.OrdenRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class GestionMesasTestIT extends IntegrationTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrdenRepositoryJpa ordenRepository;

    @Test
    @WithMockUser(username = "mesero", roles = {"MESERO"})
    void debePermitirCrearOrdenMesaDisponible() throws Exception {
        String requestBody = """
                {
                  "mesa": 1,
                  "mesero": "mesero",
                  "items": [
                    {
                      "productoId": 1,
                      "cantidad": 3,
                      "observacion": ""
                    },
                    {
                      "productoId": 2,
                      "cantidad": 3,
                      "observacion": ""
                    },
                    {
                      "productoId": 3,
                      "cantidad": 3,
                      "observacion": ""
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/api/v1/orden")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andDo(print())
                .andDo(result -> {
                    if(result.getResolvedException() != null) {
                        result.getResolvedException().printStackTrace();
                    }
                })
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.Mensaje").value("Orden creada"));

        List<Orden> ordenes = ordenRepository.findAll();
        assertThat(ordenes).hasSize(1);
        assertThat(ordenes.get(0).getEstado()).isEqualTo(EstadoOrden.ABIERTA);
        assertThat(ordenes.get(0).getMesa().getId()).isEqualTo(1L);
        assertThat(ordenes.get(0).getMesa().getEstado()).isEqualTo(Estado.OCUPADA);
    }

    @Test
    @WithMockUser(username = "mesero", roles = {"MESERO"})
    void alCerrarOrdenDebeVolverMesaAEstadoDisponible() throws Exception {

        mockMvc.perform(put("/api/v1/orden/{ordenId}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Mensaje").value("Orden cancelada"));

        List<Orden> ordenes = ordenRepository.findAll();
        assertThat(ordenes).hasSize(1);
        assertThat(ordenes.get(0).getEstado()).isEqualTo(EstadoOrden.CANCELADA);
        assertThat(ordenes.get(0).getMesa().getEstado()).isEqualTo(Estado.DISPONIBLE);
    }

}
