package com.enterprise.posapp.ordenes.repository;

import com.enterprise.posapp.ordenes.model.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenJpaRepository extends JpaRepository<Orden, Long> {
}
