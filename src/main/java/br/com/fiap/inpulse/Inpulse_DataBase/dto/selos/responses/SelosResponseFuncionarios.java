package br.com.fiap.inpulse.Inpulse_DataBase.dto.selos.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;

public class SelosResponseFuncionarios {
    String nome;
    String descricao;

    public SelosResponseFuncionarios toDto(Selos selos){
        this.setNome(selos.getNome());
        this.setDescricao(selos.getDescricao());
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
