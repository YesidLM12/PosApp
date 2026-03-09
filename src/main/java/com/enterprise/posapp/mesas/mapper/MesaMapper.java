package com.enterprise.posapp.mesas.mapper;

import com.enterprise.posapp.mesas.dto.response.MesaResponse;
import com.enterprise.posapp.mesas.model.entity.Mesas;

public class MesaMapper {
    public static MesaResponse toResponse(Mesas mesa) {
        return new MesaResponse(
                mesa.getNumero(),
                mesa.getEstado()
        );
    }
}
