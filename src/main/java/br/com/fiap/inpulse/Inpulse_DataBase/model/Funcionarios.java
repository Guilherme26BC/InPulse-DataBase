package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set; // Importe a classe Set

@Entity
public class Funcionarios {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long funcionario_id;

    private String primeiro_nome;
    private String ultimo_sobrenome;
    private String imagem_funcionario;

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
    private Set<Ideias> ideias; // Já corrigido

    @ManyToMany(mappedBy = "funcionarios")
    private Set<Programas> programas; // Altere de List para Set

    @ManyToMany(mappedBy = "funcionarios")
    private Set<Item> itens; // Já corrigido
    @ManyToMany(mappedBy = "funcionarios")
    private Set<Missoes> missoes;
    @ManyToMany
    private Set<Selos> selos; // Altere de List para Set
    
    @OneToMany(mappedBy = "funcionarios",
    cascade =  CascadeType.ALL,
    orphanRemoval = true)
    private List<Logs>logs;

    @OneToMany(mappedBy = "funcionario",
           cascade =  CascadeType.ALL,
           orphanRemoval = true)
    private List<Contribuicoes>contribuicoes;

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

    public String getImagem_funcionario() {
        return imagem_funcionario;
    }

    public void setImagem_funcionario(String imagem_funcionario) {
        this.imagem_funcionario = imagem_funcionario;
    }

    public Set<Ideias> getIdeias() {
        return ideias;
    }

    public void setIdeias(Set<Ideias> ideias) {
        this.ideias = ideias;
    }

    public Set<Programas> getProgramas() { // Getter alterado
        return programas;
    }

    public void setProgramas(Set<Programas> programas) { // Setter alterado
        this.programas = programas;
    }

    public Set<Selos> getSelos() { // Getter alterado
        return selos;
    }

    public void setSelos(Set<Selos> selos) { // Setter alterado
        this.selos = selos;
    }

    public List<Logs> getLogs() {
        return logs;
    }

    public void setLogs(List<Logs> logs) {
        this.logs = logs;
    }

    public List<Contribuicoes> getContribuicoes() {
        return contribuicoes;
    }

    public void setContribuicoes(List<Contribuicoes> contribuicoes) {
        this.contribuicoes = contribuicoes;
    }
    public Set<Item> getItens() { // Getter alterado
        return itens;
    }

    public void setItens(Set<Item> itens) { // Setter alterado
        this.itens = itens;
    }

    public Set<Missoes> getMissoes() {
        return missoes;
    }

    public void setMissoes(Set<Missoes> missoes) {
        this.missoes = missoes;
    }

      public String getNome() {
        return this.primeiro_nome + " " + this.ultimo_sobrenome;
    }
}