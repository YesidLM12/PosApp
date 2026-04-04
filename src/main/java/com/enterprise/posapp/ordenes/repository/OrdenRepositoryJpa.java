package com.enterprise.posapp.ordenes.repository;

import com.enterprise.posapp.common.exceptions.ResourceNotFoundException;
import com.enterprise.posapp.ordenes.model.entity.Orden;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrdenRepositoryJpa implements OrdenRepository {
    private final OrdenJpaRepository ordenJpaRepository;

    @Override
    public void save(Orden orden) {
        ordenJpaRepository.save(orden);
    }

    @Override
    public Orden findById(long ordenId) {
        return ordenJpaRepository.findById(ordenId)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada"));
    }

    @Override
    public Optional<Orden> findByWithItems(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Orden> findAll() {
        return ordenJpaRepository.findAll();
    }
}
