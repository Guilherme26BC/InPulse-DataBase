package br.com.fiap.inpulse.Inpulse_DataBase.dto.selos.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SelosRequestUpdate {
    private String nome;
    private String descricao;
    private BigInteger pontos;
    private BigDecimal moedas;
    private String icone;

    public Selos toModel(Selos selos){
        if (this.getNome().equals(" ")){
            selos.setNome(selos.getNome());
        }else{
            selos.setNome(this.getNome());
        }

        if(this.getDescricao().equals(" ")){
            selos.setDescricao(selos.getDescricao());
        }else{
            selos.setDescricao(this.getDescricao());
        }

        if(this.getPontos().compareTo(new BigInteger("-1"))<=0){
            selos.setPontos(selos.getPontos());
        }else{
            selos.setPontos(this.getPontos());
        }

       if(this.getMoedas().compareTo(new BigDecimal(-1))<=0 ){
           selos.setMoedas(selos.getMoedas());
       }else{
           selos.setMoedas(this.getMoedas());
       }

        if(this.getIcone().equals(" ")){
            selos.setIcone(selos.getIcone());
        }else{
            selos.setIcone(this.getIcone());
        }
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
