package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;

public class ProgramasRequestUpdateFuncionarios {
    private Long funcionarios_id;

    public Programas toModel(Programas programas, FuncionariosRepository funcionariosRepository){
        Funcionarios funAux = funcionariosRepository.findById(this.getFuncionarios_id()).orElseThrow(()->
                new RuntimeException("Funcionario inexistente: " + this.getFuncionarios_id()));

        if (programas.getFuncionarios().contains(funAux)) {
            throw new RuntimeException("Funcionario ja cadastrado no programa: " + this.getFuncionarios_id());
        }
        return programas;

    }
    public Long getFuncionarios_id() {
        return funcionarios_id;
    }

    public void setFuncionarios_id(Long funcionarios_id) {
        this.funcionarios_id = funcionarios_id;
    }
}
