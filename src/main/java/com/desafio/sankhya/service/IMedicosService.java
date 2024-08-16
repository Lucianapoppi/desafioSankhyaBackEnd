package com.desafio.sankhya.service;


import com.desafio.sankhya.model.Clientes;
import com.desafio.sankhya.model.Medicos;
import org.springframework.http.ResponseEntity;

public interface IMedicosService {


    ResponseEntity<?> salvarMedico(Medicos medico);

    Object recuperarMedicos();

    Medicos buscarMedico(int idMedico);

    ResponseEntity<?> atualizarMedico(int idMedico, Medicos medico);
}
