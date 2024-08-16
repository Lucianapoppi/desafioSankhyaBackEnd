package com.desafio.sankhya.service;

import com.desafio.sankhya.model.Clientes;
import org.springframework.http.ResponseEntity;

public interface IClientesService {

    ResponseEntity<?> salvarCliente(Clientes cliente);

    Object recuperarClientes();

    Clientes buscarCliente(int idCliente);

    ResponseEntity<?> atualizarCliente(int idCliente, Clientes cliente);
}
