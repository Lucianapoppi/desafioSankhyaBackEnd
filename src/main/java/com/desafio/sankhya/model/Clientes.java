package com.desafio.sankhya.model;

import jakarta.persistence.*;


@Entity
public class Clientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private int idCliente;

    @Column(name = "nome_cliente", length = 100, nullable = false)
    private String nomeCliente;


    @Column(name = "cpf", length = 18, unique = true, nullable = false)
    private String cpf;

    @Column(name = "telefone", length = 18, nullable = false)
    private String telefone;

    @Column(name = "email", length = 100, nullable = false)
    private String email;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medicos idMedico;



    @Column(name = "status", nullable = false)
    private String status;


    public Clientes(String nomeCliente, String cpf,String telefone, String email, Medicos idMedico, String status) {

        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
        this.idMedico = idMedico;
        this.status = status;
    }

    public Clientes() {
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Medicos getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(Medicos idMedico) {
        this.idMedico = idMedico;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
