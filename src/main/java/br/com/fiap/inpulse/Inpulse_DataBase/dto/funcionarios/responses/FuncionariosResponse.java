package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.responses.IdeiasResponseFuncionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.selos.responses.SelosResponseFuncionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class FuncionariosResponse {
    private Long funcionario_id;
    private String primeiro_nome;
    private String ultimo_sobrenome;
    private String email;
    private String senha;
    private BigInteger pontos;
    private BigDecimal moedas;
    private String tier;
    private boolean modo_anonimo;
    private String imagem_funcionario;
    private List<IdeiasResponseFuncionarios> ideias;
    private List<String> programas;
    private List<SelosResponseFuncionarios> selos;
    private List<String> logs;

    public FuncionariosResponse toModel(Funcionarios funcionarios){
        this.setFuncionario_id(funcionarios.getFuncionario_id());
        this.setPrimeiro_nome(funcionarios.getPrimeiro_nome());
        this.setUltimo_sobrenome(funcionarios.getUltimo_sobrenome());
        this.setEmail(funcionarios.getEmail());
        this.setSenha(funcionarios.getSenha());
        this.setPontos(funcionarios.getPontos());
        this.setMoedas(funcionarios.getMoedas());
        this.setTier(funcionarios.getTier());
        this.setModo_anonimo(funcionarios.isModo_anonimo());
        this.setImagem_funcionario(funcionarios.getImagem_funcionario());

        List<IdeiasResponseFuncionarios> ideias = funcionarios.getIdeias().stream()
                .map(p-> new IdeiasResponseFuncionarios().toDto(p))
                .collect(Collectors.toList());
       this.setIdeias(ideias);

        List<String> nomesProgramas = funcionarios.getProgramas().stream()
                .map(p-> p.getNome_programa())
                .collect(Collectors.toList());
        this.setProgramas(nomesProgramas);

        List<SelosResponseFuncionarios> nomesSelos = funcionarios.getSelos().stream()
                .map(p-> new SelosResponseFuncionarios().toDto(p))
                .collect(Collectors.toList());
        this.setSelos(nomesSelos);

        List<String> nomesLogs = funcionarios.getLogs().stream()
                .map(l->l.getEvento()).collect(Collectors.toList());
       this.setLogs(nomesLogs);
       return this;
    }


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

    public List<IdeiasResponseFuncionarios> getIdeias() {
        return ideias;
    }

    public void setIdeias(List<IdeiasResponseFuncionarios> ideias) {
        this.ideias = ideias;
    }

    public List<String> getProgramas() {
        return programas;
    }

    public void setProgramas(List<String> programas) {
        this.programas = programas;
    }

    public List<SelosResponseFuncionarios> getSelos() {
        return selos;
    }

    public void setSelos(List<SelosResponseFuncionarios> selos) {
        this.selos = selos;
    }

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    public String getImagem_funcionario() {
        return imagem_funcionario;
    }

    public void setImagem_funcionario(String imagem_funcionario) {
        this.imagem_funcionario = imagem_funcionario;
    }
}
