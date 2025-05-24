package br.com.fiap.inpulse.Inpulse_DataBase.dto.logs;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Logs;
import br.com.fiap.inpulse.Inpulse_DataBase.model.LogsStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogsResponse {
    private Long log_id;
    private LocalDate data;
    private LogsStatus status;
    private LocalTime hora;
    private String evento;
    private String email;
    private String funcionariosNome;

    public LogsResponse toDto(Logs log){
        this.setId(log.getLog_id());
        this.setData(log.getData());
        this.setStatus(log.getStatus());
        this.setHora(log.getHora());
        this.setEvento(log.getEvento());
        this.setEmail(log.getFuncionarios().getEmail());
        String user = log.getFuncionarios().getPrimeiro_nome()+"."+log.getFuncionarios().getUltimo_sobrenome();
        this.setFuncionariosNome(user);
        return this;
    }
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

    public String getFuncionariosNome() {
        return funcionariosNome;
    }

    public void setFuncionariosNome(String funcionariosNome) {
        this.funcionariosNome = funcionariosNome;
    }
}
