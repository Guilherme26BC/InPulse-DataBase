package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;

public class FuncionariosResponseIdeias {
    private Long id;
    private String primeiro_nome; // Ajustado
    private String ultimo_sobrenome; // Ajustado

    public FuncionariosResponseIdeias toDto(Funcionarios funcionario) {
        this.setId(funcionario.getFuncionario_id());
        this.setPrimeiro_nome(funcionario.getPrimeiro_nome()); // Mapeamento corrigido
        this.setUltimo_sobrenome(funcionario.getUltimo_sobrenome()); // Mapeamento corrigido
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    public void setPrimeiro_nome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    public String getUltimo_sobrenome() {
        return ultimo_sobrenome;
    }

    public void setUltimo_sobrenome(String ultimo_sobrenome) {
        this.ultimo_sobrenome = ultimo_sobrenome;
    }
}