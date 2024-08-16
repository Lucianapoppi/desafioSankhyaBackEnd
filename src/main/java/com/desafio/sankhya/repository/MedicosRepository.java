package com.desafio.sankhya.repository;

import com.desafio.sankhya.model.Medicos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicosRepository extends JpaRepository<Medicos, Integer> {

    boolean existsByEmail(String email);

    boolean existsByIdMedico(int idMedico);
}
