package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProgramasRequestUpdateFuncionarios {
    private List<Long> funcionarios_id;

    public Programas toModel(Programas programas, FuncionariosRepository funcionariosRepository){
                List<Funcionarios> aux = this.getFuncionarios_id().stream().map(f->{
                Funcionarios funAux = funcionariosRepository.findById(f).orElseThrow(()->
                        new RuntimeException("Funcionario inexistente: " + this.getFuncionarios_id()));
                return funAux;
            }).collect(Collectors.toList());
            programas.setFuncionarios(aux);
            return programas;
    }
    public List<Long> getFuncionarios_id() {
        return funcionarios_id;
    }

    public void setFuncionarios_id(List<Long> funcionarios_id) {
        this.funcionarios_id = funcionarios_id;
    }
}
