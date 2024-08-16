package com.desafio.sankhya.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
public class Consultas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private int idConsulta;

    @Column(name = "data_consulta", nullable = false)
    private Date dataConsulta;

    @Column(name = "hora_consulta", length = 6, nullable = false)
    private String horaConsulta;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_cliente", nullable = false)
    private Clientes idCliente;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_medico", nullable = false)
    private Medicos idMedico;

    public Consultas(int idConsulta, Date dataConsulta, String horaConsulta, Clientes idCliente, Medicos idMedico) {
        this.idConsulta = idConsulta;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.idCliente = idCliente;
        this.idMedico = idMedico;
    }

    public int getIdConsulta() {
        return idConsulta;
    }
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }
    public Date getDataConsulta() {
        return dataConsulta;
    }
    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
    public String getHoraConsulta() {
        return horaConsulta;
    }
    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
    }
    public Clientes getCliente() {
        return idCliente;
    }
    public void setCliente(Clientes idCliente) {
        this.idCliente = idCliente;
    }
    public Medicos getMedico() {
        return idMedico;
    }
    public void setMedico(Medicos idMedico) {
        this.idMedico = idMedico;
    }
}
