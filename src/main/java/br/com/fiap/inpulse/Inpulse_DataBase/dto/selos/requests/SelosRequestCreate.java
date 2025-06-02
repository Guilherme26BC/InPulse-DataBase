package br.com.fiap.inpulse.Inpulse_DataBase.dto.selos.requests;


import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SelosRequestCreate {
    private String nome;
    private String descricao;
    private BigInteger pontos;
    private BigDecimal moedas;
    private String icone;

    public Selos toModel(){
        Selos selos = new Selos();
        selos.setNome(this.getNome());
        selos.setDescricao(this.getDescricao());
        selos.setPontos(this.getPontos());
        selos.setMoedas(this.getMoedas());
        selos.setIcone(this.getIcone());
        return selos;
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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
