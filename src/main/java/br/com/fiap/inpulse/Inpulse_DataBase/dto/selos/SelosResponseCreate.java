package br.com.fiap.inpulse.Inpulse_DataBase.dto.selos;



import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SelosResponseCreate {
    private Long selos_id;
    private String nome;
    private String descricao;
    private BigInteger pontos;
    private BigDecimal moedas;
    private String icone;

    public SelosResponseCreate toDto(Selos selos){
        this.setSelos_id(selos.getSelos_id());
        this.setNome(selos.getNome());
        this.setDescricao(selos.getDescricao());
        this.setPontos(selos.getPontos());
        this.setMoedas(selos.getMoedas());
        this.setIcone(selos.getIcone());
        return this;
    }

    public Long getSelos_id() {
        return selos_id;
    }

    public void setSelos_id(Long selos_id) {
        this.selos_id = selos_id;
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
