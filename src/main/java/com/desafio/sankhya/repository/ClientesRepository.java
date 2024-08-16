package com.desafio.sankhya.repository;

import com.desafio.sankhya.model.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Integer> {

    boolean existsByCpf(String cpf);

    boolean existsByIdCliente(int idCliente);

}