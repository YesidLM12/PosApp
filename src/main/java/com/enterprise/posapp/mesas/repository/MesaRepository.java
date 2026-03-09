package com.enterprise.posapp.mesas.repository;

import com.enterprise.posapp.mesas.model.entity.Mesas;

import java.util.List;

public interface MesaRepository {
    List<Mesas> findAll();

    void save(Mesas mesa);

    Mesas findByNumberOfMesa(int nMesa);
}