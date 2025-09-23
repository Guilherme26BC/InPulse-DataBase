package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ProgramasRepository;

public class ProgramasRequestUpdateIdeias {
    private Long ideias_id;

    @Autowired
    private ProgramasRepository programasRepository;

    public Programas toModel(Programas programas, IdeiasRepository ideiasRepository){
        Ideias funAux = ideiasRepository.findById(this.getIdeias_id()).orElseThrow(()->
                new RuntimeException("Funcionario inexistente: " + this.getIdeias_id()));
                
        if (programasRepository.findById(funAux.getId()).isEmpty()) {
            programas.getIdeias().add(funAux);
        }
        else {
            throw new RuntimeException("Funcionario ja cadastrado no programa: " + this.getIdeias_id());
        }
        return programas;

    }

    public Long getIdeias_id() {
        return ideias_id;
    }

    public void setIdeias_id(Long ideias_id) {
        this.ideias_id = ideias_id;
    }
}
