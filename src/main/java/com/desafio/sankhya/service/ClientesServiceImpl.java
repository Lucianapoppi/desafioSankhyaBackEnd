package com.desafio.sankhya.service;

import com.desafio.sankhya.model.Clientes;
import com.desafio.sankhya.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class ClientesServiceImpl implements IClientesService {

    @Autowired
    private ClientesRepository clientesRepository;


    @Override
    public ResponseEntity<?> salvarCliente(Clientes cliente) {
        try {
            if (clientesRepository.existsByCpf(cliente.getCpf())) {

                return ResponseEntity.badRequest().body(Collections.singletonMap("message", "CPF já está sendo usado."));
            }

            ResponseEntity<?> validationResponse = validateCliente(cliente);
            if (validationResponse != null) {
                return validationResponse;
            }

            Clientes savedCliente = clientesRepository.save(cliente);

            return ResponseEntity.ok(Collections.singletonMap("message", "Cliente cadastrado com sucesso.")); // Retorna a mensagem de sucesso

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Ocorreu um erro ao cadastrar o cliente."));
        }
    }

    @Override
    public Object recuperarClientes() {
        try {
            return clientesRepository.findAll();
        } catch (Exception e) {
           return Collections.singletonMap("message", "Ocorreu um erro ao recuperar os clientes.");
        }
    }

    @Override
    public Clientes buscarCliente(int idCliente) {
        try {
            return clientesRepository.findById(idCliente).orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ResponseEntity<?> atualizarCliente(int idCliente, Clientes cliente) {
        try {
            Clientes clienteExistente = clientesRepository.findById(idCliente).orElse(null);
            if (clienteExistente != null) {
                updateCliente(clienteExistente, cliente);
                Clientes clienteAtualizado = clientesRepository.save(clienteExistente);
                return ResponseEntity.ok(clienteAtualizado);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao atualizar o cliente.");
        }
    }


    private void updateCliente(Clientes clienteExistente, Clientes cliente) {
        clienteExistente.setNomeCliente(cliente.getNomeCliente());
        clienteExistente.setCpf(cliente.getCpf());
        clienteExistente.setTelefone(cliente.getTelefone());
        clienteExistente.setEmail(cliente.getEmail());
        clienteExistente.setIdMedico(cliente.getIdMedico());
        clienteExistente.setStatus(cliente.getStatus());
    }


    private ResponseEntity<?> validateCliente(Clientes cliente) {
        if (cliente.getNomeCliente() == null || cliente.getNomeCliente().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Nome do cliente é obrigatório."));
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Telefone do cliente é obrigatório."));
        }

        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Email do cliente é obrigatório."));
        }

        if (cliente.getIdMedico() == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Id do médico do cliente é obrigatório."));
        }

        if (cliente.getStatus() == null) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Status do cliente é obrigatório."));
        }

        if (cliente.getStatus() == null || cliente.getStatus().isEmpty()) {
            throw new IllegalArgumentException("Status do cliente ativo ou inativo é obrigatório.");
        }


        return null;
    }
}
