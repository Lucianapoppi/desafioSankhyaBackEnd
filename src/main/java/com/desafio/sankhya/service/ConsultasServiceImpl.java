package com.desafio.sankhya.service;

import com.desafio.sankhya.model.Consultas;
import com.desafio.sankhya.repository.ClientesRepository;
import com.desafio.sankhya.repository.ConsultasRepository;
import com.desafio.sankhya.repository.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ConsultasServiceImpl implements IConsultasService{

    @Autowired
    public ConsultasRepository consultasRepository;

    @Autowired
    public MedicosRepository medicosRepository;

    @Autowired
    public ClientesRepository clientesRepository;

    @Override
    public ResponseEntity<?> salvarConsulta(Consultas consulta) {
        try {

            ResponseEntity<?> validationResponse = validateConsulta(consulta);
            if (validationResponse != null) {
                return validationResponse;
            }

            Consultas savedConsultas = consultasRepository.save(consulta);

            return ResponseEntity.ok(Collections.singletonMap("message", "consulta cadastrada com sucesso.")); // Retorna a mensagem de sucesso

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Ocorreu um erro ao cadastrar a consulta."));
        }
    }

    private ResponseEntity<?> validateConsulta(Consultas consulta) {
        if (consulta.getDataConsulta() == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "A data da consulta é obrigatório."));
        }
        if (consulta.getHoraConsulta() == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "O horário da consulta é obrigatório."));
        }
        if (!medicosRepository.existsByIdMedico(consulta.getMedico().getIdMedico())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "O id do médico não existe da consulta é obrigatório."));
        }
        if (!clientesRepository.existsByIdCliente(consulta.getCliente().getIdCliente())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "O id do cliente não existe da consulta é obrigatório."));
        }
        return null;
    }

    @Override
    public List<Consultas> recuperarConsultas() {
        try {
            return consultasRepository.findAll();
        } catch (Exception e) {
            // Log o erro para análise
            System.err.println("Erro ao recuperar consultas: " + e.getMessage());
            // Retorne uma lista vazia em caso de erro
            return Collections.emptyList();
        }
    }

    public interface ConsultasService {
        List<Consultas> recuperarConsultas();
    }


    @Override
    public Consultas buscarConsulta(int idConsulta) {
        try {
            return consultasRepository.findById(idConsulta).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseEntity<?> atualizarConsulta(int idConsulta, Consultas consulta) {
        try {

            Consultas consultaExistente = consultasRepository.findById(idConsulta).orElse(null);
            if (consultaExistente != null) {

                updateConsulta(consultaExistente, consulta);
                Consultas consultaAtualizado = consultasRepository.save(consultaExistente);

                return ResponseEntity.ok(consultaAtualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private void updateConsulta(Consultas consultaExistente, Consultas consulta) {
        consultaExistente.setDataConsulta(consulta.getDataConsulta());
        consultaExistente.setHoraConsulta(consulta.getHoraConsulta());
        consultaExistente.setCliente(consulta.getCliente());
        consultaExistente.setMedico(consulta.getMedico());
        ;
    }
}


