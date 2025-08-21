package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProgramasRequestUpdateIdeias {
    private List<Long> ideias_id;

    public Programas toModel(Programas programas, IdeiasRepository ideiasRepository){
        List<Ideias> auxi =this.getIdeias_id().stream().map(i->{
            Ideias ideAux = ideiasRepository.findById(i).orElseThrow(()->
                    new RuntimeException("ideia inexistente: " + this.getIdeias_id()));
            return ideAux;
        }).collect(Collectors.toList());
        programas.setIdeias(auxi);
        return programas;
    }

    public List<Long> getIdeias_id() {
        return ideias_id;
    }

    public void setIdeias_id(List<Long> ideias_id) {
        this.ideias_id = ideias_id;
    }
}
