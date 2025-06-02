package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;


import java.math.BigDecimal;
import java.math.BigInteger;

public class FuncionariosRequestCreate {
    private String primeiro_nome;
    private String ultimo_sobrenome;
    private String email;
    private String senha;
    private boolean modo_anonimo;

    public Funcionarios toModel(){
        Funcionarios funcionarios = new Funcionarios();
        funcionarios.setPrimeiro_nome(this.getPrimeiro_nome());
        funcionarios.setUltimo_sobrenome(this.getUltimo_sobrenome());
        funcionarios.setEmail(this.getEmail());
        funcionarios.setSenha(this.getSenha());
        funcionarios.setPontos( new BigInteger("0"));
        funcionarios.setMoedas(new BigDecimal(0));
        funcionarios.setTier("Bronze");
        funcionarios.setModo_anonimo(this.isModo_anonimo());
        return funcionarios;
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

    public boolean isModo_anonimo() {
        return modo_anonimo;
    }

    public void setModo_anonimo(boolean modo_anonimo) {
        this.modo_anonimo = modo_anonimo;
    }
}
