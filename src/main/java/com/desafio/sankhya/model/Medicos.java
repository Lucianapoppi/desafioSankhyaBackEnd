package com.desafio.sankhya.model;

import jakarta.persistence.*;


@Entity
public class Medicos{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_medico")
    private int idMedico;

    @Column(name = "nome_medico", length = 50)
    private String nomeMedico;

    @Column(name = "especializacao")
    private String especializacao;

    @Column(name = "experiencia")
    private String experiencia;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;


    @Column(name = "status", nullable = false)
    private String status;

    public Medicos( String nomeMedico, String especializacao, String experiencia, String telefone, String email, String status ) {

        this.nomeMedico = nomeMedico;
        this.especializacao = especializacao;
        this.experiencia = experiencia;
        this.telefone = telefone;
        this.email = email;
        this.status = status;
    }

    public Medicos() {
    }

    public int getIdMedico() {
        return idMedico;
    }
    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }
    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getEspecializacao() {
        return especializacao;
    }
    public void setEspecializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public String getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
