package com.enterprise.posapp.mesas.repository;

import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MesaRepositoryJpa implements MesaRepository{
    private final MesaJpaRepository mesaJpaRepository;

    @Override
    public void save(Mesas mesa) {
        mesaJpaRepository.save(mesa);
    }

    @Override
    public Mesas findByNumberOfMesa(int nMesa) {
        return mesaJpaRepository.findMesasByNumero(nMesa)
                .orElseThrow(() -> new ResourceNotFoundException("Mesa no encontrada"));
    }
}
