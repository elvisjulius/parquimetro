package com.parquimetro.repository;


import com.parquimetro.entity.Condutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CondutorRepository extends JpaRepository<Condutor, UUID> {

    @Query("SELECT c FROM Condutor c JOIN c.veiculos v WHERE v.id = :veiculoId")
    Optional<Condutor> findByVeiculoId(@Param("veiculoId") UUID veiculoId);

    @Query("SELECT c FROM Condutor c JOIN c.veiculos v WHERE v.placa = :placa")
    Optional<Condutor> findByVeiculoPlaca(@Param("placa") String placa);

}
