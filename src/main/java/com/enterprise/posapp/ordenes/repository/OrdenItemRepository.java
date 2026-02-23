package com.enterprise.posapp.ordenes.repository;

import com.enterprise.posapp.ordenes.model.entity.OrdenItem;

public interface OrdenItemRepository {
    void save(OrdenItem ordenItem);
}
