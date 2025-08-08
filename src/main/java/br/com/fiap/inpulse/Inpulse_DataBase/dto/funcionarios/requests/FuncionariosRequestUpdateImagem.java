package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;

public class FuncionariosRequestUpdateImagem {
    private String imagem_funcionario;

    public Funcionarios toModel(Funcionarios funcionarios){
        if(!this.getImagem_funcionario().isEmpty()){
            funcionarios.setImagem_funcionario(this.getImagem_funcionario());
        }
        return funcionarios;
    }


    public String getImagem_funcionario() {
        return imagem_funcionario;
    }

    public void setImagem_funcionario(String imagem_funcionario) {
        this.imagem_funcionario = imagem_funcionario;
    }
}
