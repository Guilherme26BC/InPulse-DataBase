package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
public class Funcionarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long funcionario_id;

    private String primeiro_nome;
    private String ultimo_sobrenome;

    @Column(unique = true)
    private String email;
    private String senha;
    private BigInteger pontos;
    private BigDecimal moedas;
    private String tier;
    private boolean modo_anonimo;

    @OneToMany(mappedBy = "funcionario",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Ideias> ideias;
    @ManyToMany(mappedBy = "funcionarios")
    private List<Programas> programas;

    @ManyToMany
    private List<Selos> selos;
    @OneToMany(mappedBy = "funcionarios",
    cascade =  CascadeType.ALL,
    orphanRemoval = true)
    private List<Logs>logs;

    public Long getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Long funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    public void setPrimeiro_nome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    public String getUltimo_sobrenome() {
        return ultimo_sobrenome;
    }

    public void setUltimo_sobrenome(String ultimo_sobrenome) {
        this.ultimo_sobrenome = ultimo_sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public boolean isModo_anonimo() {
        return modo_anonimo;
    }

    public void setModo_anonimo(boolean modo_anonimo) {
        this.modo_anonimo = modo_anonimo;
    }

    public List<Ideias> getIdeias() {
        return ideias;
    }

    public void setIdeias(List<Ideias> ideias) {
        this.ideias = ideias;
    }

    public List<Programas> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programas> programas) {
        this.programas = programas;
    }

    public List<Selos> getSelos() {
        return selos;
    }

    public void setSelos(List<Selos> selos) {
        this.selos = selos;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }
}
