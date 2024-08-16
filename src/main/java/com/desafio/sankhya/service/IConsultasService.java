package com.desafio.sankhya.service;

import com.desafio.sankhya.model.Consultas;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IConsultasService {
    ResponseEntity<?> salvarConsulta(Consultas consulta);

    public List<Consultas> recuperarConsultas();

    Consultas buscarConsulta(int idConsulta);

    ResponseEntity<?> atualizarConsulta(int idConsulta, Consultas consulta);
}
