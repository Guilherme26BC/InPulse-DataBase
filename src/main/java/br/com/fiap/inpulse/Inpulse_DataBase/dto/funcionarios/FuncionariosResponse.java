package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

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
    private List<String> ideias;
    private List<String> programas;
    private List<String> selos;

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

        List<String> nomesIdeias = funcionarios.getIdeias().stream()
                .map(p-> p.getNome())
                .collect(Collectors.toList());
        this.setIdeias(nomesIdeias);

        List<String> nomesProgramas = funcionarios.getProgramas().stream()
                .map(p-> p.getNome_programa())
                .collect(Collectors.toList());
        this.setProgramas(nomesProgramas);

        List<String> nomesSelos = funcionarios.getSelos().stream()
                .map(p-> p.getNome())
                .collect(Collectors.toList());
        this.setSelos(nomesSelos);
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

    public List<String> getIdeias() {
        return ideias;
    }

    public void setIdeias(List<String> ideias) {
        this.ideias = ideias;
    }

    public List<String> getProgramas() {
        return programas;
    }

    public void setProgramas(List<String> programas) {
        this.programas = programas;
    }

    public List<String> getSelos() {
        return selos;
    }

    public void setSelos(List<String> selos) {
        this.selos = selos;
    }
}
