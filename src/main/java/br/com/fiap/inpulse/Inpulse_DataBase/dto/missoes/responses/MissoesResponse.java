package br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Missoes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class MissoesResponse {

    private Long id;
    private String nome;
    private String descricao;
    private BigInteger pontos;
    private BigDecimal moedas;
    private List<Long> funcionarios_id;

    public MissoesResponse toDto(Missoes missoes){
        this.setId(missoes.getId());
        this.setNome(missoes.getNome());
        this.setDescricao(missoes.getDescricao());
        this.setPontos(missoes.getPontos());
        this.setMoedas(missoes.getMoedas());
        List<Long> idsFuncionarios = missoes.getFuncionarios().stream()
                .map(f-> f.getFuncionario_id())
                .collect(Collectors.toList());
        this.setFuncionarios_id(idsFuncionarios);
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

    public List<Long> getFuncionarios_id() {
        return funcionarios_id;
    }

    public void setFuncionarios_id(List<Long> funcionarios_id) {
        this.funcionarios_id = funcionarios_id;
    }
}
