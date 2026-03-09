package com.enterprise.posapp.pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.enterprise.posapp.pagos.model.entity.Pagos;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoJpaRepository extends JpaRepository<Pagos, Long> {

}
