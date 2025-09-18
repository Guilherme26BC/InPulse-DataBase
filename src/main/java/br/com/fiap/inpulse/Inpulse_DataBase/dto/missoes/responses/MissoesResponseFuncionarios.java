package br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Missoes;

public class MissoesResponseFuncionarios {
    private Long id;
    private String nome;
    private String descricao;

    public MissoesResponseFuncionarios toDto(Missoes missoes){
        this.setId(missoes.getId());
        this.setNome(missoes.getNome());
        this.setDescricao(missoes.getNome());
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
