package com.desafio.sankhya.controller;


import com.desafio.sankhya.model.Clientes;
import com.desafio.sankhya.service.IClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;


@Controller
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private IClientesService iClientesService;

    @PostMapping
    public ResponseEntity<?> cadastrarClientes(@RequestBody Clientes cliente) {
        try {
            ResponseEntity<?> resposta = iClientesService.salvarCliente(cliente);

            return resposta; // Retorna a resposta do serviço diretamente
        } catch (Exception e) {
            // Log da exceção para fins de depuração
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Ocorreu um erro ao cadastrar o cliente."));
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<Clientes>> listarClientes() {
        ArrayList<Clientes> clientes = (ArrayList<Clientes>) iClientesService.recuperarClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Clientes> buscarCliente(@PathVariable("idCliente") int idCliente) {
        Clientes clientesRes = iClientesService.buscarCliente(idCliente);
        if (clientesRes != null) {
            return ResponseEntity.ok(clientesRes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

    }

    @PutMapping("/{idCliente}")
    public ResponseEntity<?> atualizarCliente(@PathVariable("idCliente") int idCliente, @RequestBody Clientes cliente) {
        try {
            ResponseEntity<?> resposta = iClientesService.atualizarCliente(idCliente, cliente);
            return resposta;
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o cliente.");
        }
    }
}
