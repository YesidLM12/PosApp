package com.enterprise.posapp.mesas.repository;

import com.enterprise.posapp.mesas.model.entity.Mesas;

public interface MesaRepository {
    void save(Mesas mesa);

    Mesas findByNumberOfMesa(int nMesa);
}