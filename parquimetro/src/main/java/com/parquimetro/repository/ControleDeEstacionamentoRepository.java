package com.parquimetro.repository;


import com.parquimetro.entity.Condutor;
import com.parquimetro.entity.ControleDeEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ControleDeEstacionamentoRepository extends JpaRepository<ControleDeEstacionamento, UUID> {

    @Query("SELECT ce FROM ControleDeEstacionamento ce WHERE ce.veiculoUtilizado.placa = :placa")
    Optional<ControleDeEstacionamento> findByVeiculoUtilizadoPlaca(@Param("placa") String placa);
}
