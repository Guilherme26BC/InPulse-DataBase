package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
//CORRIGIR OS CASCADES DAS CLASSES FILHAS
@Entity
public class Logs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long log_id;
    private LocalDate data;
    private LogsStatus status;
    private LocalTime hora;
    private String evento;
    private String email;
    @ManyToOne
    @JoinColumn(name ="funcionarios_id")
    private Funcionarios funcionarios;

    public Long getLog_id() {
        return log_id;
    }

    public void setId(Long id) {
        this.log_id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LogsStatus getStatus() {
        return status;
    }

    public void setStatus(LogsStatus status) {
        this.status = status;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Funcionarios getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionarios funcionarios) {
        this.funcionarios = funcionarios;
    }
}
