package br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Missoes;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MissoesRequestCreate {
    private String nome;
    private String descricao;
    private BigInteger pontos;
    private BigDecimal moedas;

    public Missoes toModel(){
        Missoes missoes = new Missoes();
        missoes.setNome(this.getNome());
        missoes.setDescricao(this.getDescricao());
        missoes.setPontos(this.getPontos());
        missoes.setMoedas(this.getMoedas());
        return missoes;
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

    public BigInteger getPontos() {
        return pontos;
    }

    public void setPontos(BigInteger pontos) {
        this.pontos = pontos;
    }

    public BigDecimal getMoedas() {
        return moedas;
    }

    public void setMoedas(BigDecimal moedas) {
        this.moedas = moedas;
    }
}
