package com.enterprise.posapp.pagos.repository;

import com.enterprise.posapp.pagos.model.entity.Pagos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;

public interface PagoRepository {
    void save(Pagos pago);

    Page<Pagos> findAll(Pageable pageable);

    BigDecimal sumByOrdenId(Long id);
}
