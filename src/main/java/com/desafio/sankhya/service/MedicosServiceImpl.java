package com.desafio.sankhya.service;


import com.desafio.sankhya.model.Medicos;
import com.desafio.sankhya.repository.MedicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MedicosServiceImpl implements IMedicosService {

    @Autowired
    private MedicosRepository medicosRepository;


    @Override
    public ResponseEntity<?> salvarMedico(Medicos medico) {
        try {
            if (medicosRepository.existsByEmail(medico.getEmail())) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email já está sendo usado."));
            }

            ResponseEntity<?> validationResponse = validateMedico(medico);
            if (validationResponse != null) {
                return validationResponse;
            }

            Medicos savedMedico = medicosRepository.save(medico);

            return ResponseEntity.ok(Collections.singletonMap("message", "Medico cadastrado com sucesso.")); // Retorna a mensagem de sucesso

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Ocorreu um erro ao cadastrar o medico."));
        }

    }

    @Override
    public List<Medicos> recuperarMedicos() {
        try {
            List<Medicos> medicos = medicosRepository.findAll();
            System.out.println("Médicos encontrados: " + medicos.size());
            return medicos;
        } catch (Exception e) {
            e.printStackTrace();  // Exibe o stack trace para facilitar a depuração
            throw new RuntimeException("Erro ao recuperar médicos do banco de dados", e);
        }
    }




    @Override
    public Medicos buscarMedico(int idMedico) {
        try {
            return medicosRepository.findById(idMedico).orElse(null);
        } catch (Exception e) {
            return null;
        }    }

    @Override
    public ResponseEntity<?> atualizarMedico(int idMedico, Medicos medico) {
        try {

            Medicos medicoExistente = medicosRepository.findById(idMedico).orElse(null);
            if (medicoExistente != null) {

                updateMedico(medicoExistente, medico);
                Medicos medicoAtualizado = medicosRepository.save(medicoExistente);

                return ResponseEntity.ok(medicoAtualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private void updateMedico(Medicos medicoExistente, Medicos medico) {
        medicoExistente.setNomeMedico(medico.getNomeMedico());
        medicoExistente.setTelefone(medico.getTelefone());
        medicoExistente.setEmail(medico.getEmail());
        medicoExistente.setIdMedico(medico.getIdMedico());
        medicoExistente.setStatus(medico.getStatus());
    }

    private ResponseEntity<?> validateMedico(Medicos medico) {
        if (medico.getNomeMedico() == null || medico.getNomeMedico().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Nome do medico é obrigatório."));
        }

        if (medico.getTelefone() == null || medico.getTelefone().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Telefone do medico é obrigatório."));
        }

        if (medico.getEmail() == null || medico.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email do medico é obrigatório."));
        }

        if (medico.getStatus() == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Status do medico é obrigatório."));
        }

        if (!"ATIVO".equals(medico.getStatus()) && !"INATIVO".equals(medico.getStatus())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Status do medico ativo ou inativo é obrigatório."));
        }

        return null;
    }

}
