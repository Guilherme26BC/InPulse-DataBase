package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
@Entity
public class Missoes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String descricao;
    private BigInteger pontos;
    private BigDecimal moedas;
    @ManyToMany
    @JoinTable(
            name = "missao_funcionarios",
            joinColumns = @JoinColumn(name = "missao_missao_id"), // FK da tabela 'programas'
            inverseJoinColumns = @JoinColumn(name = "funcionarios_funcionario_id") // FK da tabela 'funcionarios'
    )
    private List<Funcionarios> funcionarios;
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

    public List<Funcionarios> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionarios> funcionarios) {
        this.funcionarios = funcionarios;
    }
}


