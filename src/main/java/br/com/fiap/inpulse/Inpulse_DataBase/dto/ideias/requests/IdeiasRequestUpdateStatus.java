package br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;

public class IdeiasRequestUpdateStatus {
    private String status;

    public Ideias toModel(Ideias ideia) {
        ideia.setStatus(this.status);
        return ideia;
    }

    // Getters e Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}