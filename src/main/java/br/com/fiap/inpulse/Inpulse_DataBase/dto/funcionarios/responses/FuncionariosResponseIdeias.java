package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;

public class FuncionariosResponseIdeias {
    Long id;
    String nome;

    public FuncionariosResponseIdeias toDto(Funcionarios funcionarios){
         this.setId(funcionarios.getFuncionario_id());
         String funcionario_nome;
         if(funcionarios.isModo_anonimo()){
                 funcionario_nome= "anonimo";
         } else{
             funcionario_nome =  funcionarios.getPrimeiro_nome() + " " + funcionarios.getUltimo_sobrenome();
         }
        this.setNome(funcionario_nome);
        return this;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
