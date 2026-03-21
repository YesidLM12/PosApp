package com.enterprise.posapp.pagos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.enterprise.posapp.pagos.model.entity.Pagos;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Repository
@RequiredArgsConstructor
public class PagoRepositoryJpa implements PagoRepository {
    private final PagoJpaRepository pagoJpaRepository;

    @Override
    public void save(@NonNull Pagos pago) {
        pagoJpaRepository.save(pago);
    }

    @Override
    public Page<Pagos> findAll(@NonNull Pageable pageable) {
        return pagoJpaRepository.findAll(pageable);
    }

    @Override
    public BigDecimal sumByOrdenId(Long id) {
        return pagoJpaRepository.sumByOrdenId(id);
    }

}
