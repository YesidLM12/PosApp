package com.enterprise.posapp.mesas.repository;

import com.enterprise.posapp.mesas.model.entity.Mesas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MesaJpaRepository extends JpaRepository<Mesas, Long> {
    Optional<Mesas> findMesasByNumero(int numero);
}
