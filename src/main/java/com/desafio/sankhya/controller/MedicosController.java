package com.desafio.sankhya.controller;

import com.desafio.sankhya.model.Medicos;
import com.desafio.sankhya.service.IMedicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/medicos")
public class MedicosController {
    @Autowired
    private IMedicosService iMedicosService;

    @PostMapping
    public ResponseEntity<?> cadastrarMedicos(@RequestBody Medicos medico) {
        try {
            ResponseEntity<?> resposta = iMedicosService.salvarMedico(medico);

            return resposta; // Retorna a resposta do serviço diretamente
        } catch (Exception e) {
            // Log da exceção para fins de depuração
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Ocorreu um erro ao cadastrar o medico."));
        }
    }

    @GetMapping("/recuperarMedicos")
    public ResponseEntity<List<Medicos>> listarMedicos() {
        List<Medicos> medicos = (List<Medicos>) iMedicosService.recuperarMedicos();
        if (medicos.isEmpty()) {
            return ResponseEntity.noContent().build();  // Retorna 204 se a lista estiver vazia
        }
        return ResponseEntity.ok(medicos);
    }




    @GetMapping("/{idMedico}")
    public ResponseEntity<Medicos> buscarMedico(@PathVariable("idMedico") int idMedico) {
        Medicos medicosRes = iMedicosService.buscarMedico(idMedico);
        if (medicosRes != null) {
            return ResponseEntity.ok(medicosRes);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PutMapping("/{idMedico}")
    public ResponseEntity<?> atualizarMedico(@PathVariable("idMedico") int idMedico, @RequestBody Medicos Medico) {
        try {
            ResponseEntity<?> resposta = iMedicosService.atualizarMedico(idMedico, Medico);
            if (resposta.getBody() != null) {
                return resposta;
            } else {
                return ResponseEntity.status(resposta.getStatusCode()).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

