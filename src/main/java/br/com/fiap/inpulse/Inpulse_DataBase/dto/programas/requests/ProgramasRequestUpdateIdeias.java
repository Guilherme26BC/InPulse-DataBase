package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProgramasRequestUpdateIdeias {
    private Long ideias_id;

    public Programas toModel(Programas programas, IdeiasRepository ideiasRepository){
        Ideias funAux = ideiasRepository.findById(this.getIdeias_id()).orElseThrow(()->
                new RuntimeException("Funcionario inexistente: " + this.getIdeias_id()));
        programas.getIdeias().add(funAux);
        return programas;

    }

    public Long getIdeias_id() {
        return ideias_id;
    }

    public void setIdeias_id(Long ideias_id) {
        this.ideias_id = ideias_id;
    }
}
