package com.enterprise.posapp.ordenes.repository;

import com.enterprise.posapp.ordenes.model.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrdenJpaRepository extends JpaRepository<Orden, Long> {
    @Query("""
            SELECT o 
            FROM Orden o
            LEFT JOIN  FETCH o.items
            WHERE o.id = :id
            """)
    Optional<Orden> findByIdWithItems(Long id);
}
