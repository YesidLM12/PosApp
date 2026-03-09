package com.enterprise.posapp.mesas.repository;

import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.mesas.model.entity.Mesas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MesaRepositoryJpa implements MesaRepository {
    private final MesaJpaRepository mesaJpaRepository;

    @Override
    public List<Mesas> findAll() {
        return mesaJpaRepository.findAll();
    }

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
