package com.parquimetro.repository;


import com.parquimetro.entity.Condutor;
import com.parquimetro.entity.ControleDeEstacionamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ControleDeEstacionamentoRepository extends JpaRepository<ControleDeEstacionamento, UUID> {

}
