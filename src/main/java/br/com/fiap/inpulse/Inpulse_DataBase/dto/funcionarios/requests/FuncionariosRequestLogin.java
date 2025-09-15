package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

public class FuncionariosRequestLogin {
    private String email;
    private String senha;

    // Getters and Setters
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
}