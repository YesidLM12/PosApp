package com.enterprise.posapp.ordenes.repository;

import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrdenItemRepositoryJpa {
    private final OrdenItemJpaRepository ordenItemJpaRepository;

    public void save(OrdenItem ordenItem){
        ordenItemJpaRepository.save((ordenItem));
    }
}
