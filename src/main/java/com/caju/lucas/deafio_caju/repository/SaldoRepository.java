package com.caju.lucas.deafio_caju.repository;

import com.caju.lucas.deafio_caju.model.Saldo;
import com.caju.lucas.deafio_caju.model.enums.MccEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Long> {

	Saldo findByTipo(MccEnum tipo);
}
