package br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Missoes;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MissoesRequestUpdate {
    private BigDecimal moedas;
    private BigInteger pontos;

    public Missoes toModel(Missoes missoes){
        if (this.getMoedas().compareTo(new BigDecimal("-1")) <= 0) {
            missoes.setMoedas(missoes.getMoedas());
        }else{
        missoes.setMoedas(this.getMoedas());
        }

        if (this.getPontos().compareTo(new BigInteger("-1")) <= 0) {
            missoes.setPontos(missoes.getPontos());
        } else {
            missoes.setPontos(this.getPontos());
        }
        return missoes;
    }
    public BigDecimal getMoedas() {
        return moedas;
    }

    public void setMoedas(BigDecimal moedas) {
        this.moedas = moedas;
    }

    public BigInteger getPontos() {
        return pontos;
    }

    public void setPontos(BigInteger pontos) {
        this.pontos = pontos;
    }
}
