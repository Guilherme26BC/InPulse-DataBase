package br.com.fiap.inpulse.Inpulse_DataBase.dto.logs;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Logs;
import br.com.fiap.inpulse.Inpulse_DataBase.model.LogsStatus;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;

import java.time.LocalDate;
import java.time.LocalTime;

public class LogsRequestCreate {
    private LogsStatus status;
    private String evento;
    private Long funcionarios_id;

    public Logs toModel(FuncionariosRepository funcionariosRepository){
        Logs log = new Logs();
        log.setStatus(this.getStatus());
        log.setEvento(this.getEvento());
        Funcionarios funcionario = funcionariosRepository.findById(this.getFuncionarios_id())
                .orElseThrow(() ->
                new RuntimeException("Funcionario inexistente: " + this.getFuncionarios_id()));
        log.setFuncionarios(funcionario);
        log.setEmail(funcionario.getEmail());

        log.setData(LocalDate.now());
        log.setHora(LocalTime.now());
        return log;
    }
    public LogsStatus getStatus() {
        return status;
    }

    public void setStatus(LogsStatus status) {
        this.status = status;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public Long getFuncionarios_id() {
        return funcionarios_id;
    }

    public void setFuncionarios_id(Long funcionarios_id) {
        this.funcionarios_id = funcionarios_id;
    }
}
