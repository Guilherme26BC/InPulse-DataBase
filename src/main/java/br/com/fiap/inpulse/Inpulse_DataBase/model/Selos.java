package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
public class Selos {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long selos_id;
    private String nome;
    private String descricao;
    private BigInteger pontos;
    private BigDecimal moedas;

    @Column(unique = true)
    private String icone;

    @ManyToMany
    @JoinTable(name = "selos_funcionarios",
    joinColumns = @JoinColumn(name = "funcionarios"),
    inverseJoinColumns = @JoinColumn(name = "selos"))
    private List<Funcionarios> funcionarios;

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

    public List<Funcionarios> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionarios> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
