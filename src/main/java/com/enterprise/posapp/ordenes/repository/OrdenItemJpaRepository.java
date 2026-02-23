package com.enterprise.posapp.ordenes.repository;

import com.enterprise.posapp.ordenes.model.entity.OrdenItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenItemJpaRepository extends JpaRepository<OrdenItem, Long> {
}
