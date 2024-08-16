package com.desafio.sankhya.repository;

import com.desafio.sankhya.model.Consultas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ConsultasRepository extends JpaRepository<Consultas, Integer> {

}

