package com.enterprise.posapp.pagos.repository;

import com.enterprise.posapp.ordenes.model.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.posapp.pagos.model.entity.Pagos;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PagoJpaRepository extends JpaRepository<Pagos, Long> {
    @Query("""
            SELECT COALESCE(SUM(p.monto), 0)
            FROM Pagos p
            WHERE p.orden.id = :ordenId
            """)
    BigDecimal sumByOrdenId(@Param("ordenId") Long ordenId);
}
