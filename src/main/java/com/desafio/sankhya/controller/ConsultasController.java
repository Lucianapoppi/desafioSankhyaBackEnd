package com.desafio.sankhya.controller;

import com.desafio.sankhya.model.Consultas;
import com.desafio.sankhya.service.IConsultasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("/consultas")
public class ConsultasController {
    @Autowired
    private IConsultasService iConsultasService;

    @PostMapping
    public ResponseEntity<?> cadastrarConsultas(@RequestBody Consultas consulta) {
        try {
            ResponseEntity<?> resposta = iConsultasService.salvarConsulta(consulta);

            return resposta; // Retorna a resposta do serviço diretamente
        } catch (Exception e) {
            // Log da exceção para fins de depuração
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Ocorreu um erro ao cadastrar o cliente."));
        }
    }
    @GetMapping
    public ResponseEntity<List<Consultas>> listarConsultas() {
        List<Consultas> consultas = iConsultasService.recuperarConsultas();
        return ResponseEntity.ok(consultas);
    }


    @GetMapping("/{idConsulta}")
    public ResponseEntity<Consultas> buscarConsulta(@PathVariable("idConsulta") int idConsulta) {
        Consultas consultasRes = iConsultasService.buscarConsulta(idConsulta);
        if (consultasRes != null) {
            return ResponseEntity.ok(consultasRes);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{idConsulta}")
    public ResponseEntity<?> atualizarConsulta(@PathVariable("idConsultaMatricula") int idConsulta, @RequestBody Consultas Consulta) {
        try {
            ResponseEntity<?> resposta = iConsultasService.atualizarConsulta(idConsulta, Consulta);
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