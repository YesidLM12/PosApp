package com.enterprise.posapp.ordenes.repository;

import com.enterprise.posapp.ordenes.model.entity.Orden;

public interface OrdenRepository {
    void save (Orden orden);

    Orden findById(long ordenId);
}
