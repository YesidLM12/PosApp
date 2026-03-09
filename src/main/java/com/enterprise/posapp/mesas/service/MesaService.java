package com.enterprise.posapp.mesas.service;

import com.enterprise.posapp.mesas.dto.response.MesaResponse;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import com.enterprise.posapp.mesas.repository.MesaRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MesaService {
    private final MesaRepositoryJpa mesaRepositoryJpa;

    public List<MesaResponse> getMesas (){
       List<Mesas> mesas =  mesaRepositoryJpa.findAll();

       return mesas.stream().map(m -> new MesaResponse(
               m.getNumero(),
               m.getEstado()
       )).toList();
    }

    public MesaResponse getMesaByNMesa(int nMesa) {
        Mesas mesa = mesaRepositoryJpa.findByNumberOfMesa(nMesa);

        return new MesaResponse(
                mesa.getNumero(),
                mesa.getEstado()
        );
    }
}
