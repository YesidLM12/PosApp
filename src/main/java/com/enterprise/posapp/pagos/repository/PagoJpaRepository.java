package com.enterprise.posapp.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.posapp.pagos.model.entity.Pagos;

public interface PagoJpaRepository extends JpaRepository<Pagos, Long> {

}
