package com.enterprise.posapp.ordenes.repository;

import com.enterprise.posapp.ordenes.model.entity.Orden;

import java.util.Optional;

public interface OrdenRepository {
    void save (Orden orden);

    Orden findById(long ordenId);

    Optional<Orden> findByWithItems(Long id);
}
