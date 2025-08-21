package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;

public class FuncionariosRequestUpdateSenha {
    String senha;

    public Funcionarios toModel(Funcionarios funcionarios){
        funcionarios.setSenha(this.getSenha());
        return funcionarios;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
