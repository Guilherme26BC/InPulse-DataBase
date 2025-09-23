package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;

import java.time.LocalDate;

public class ProgramasRequestUpdate {
    private String nome_programa;
    private String descricao_programa;
    private LocalDate dataFim;

    public Programas toModel(Programas programas, FuncionariosRepository funcionariosRepository
                             ){
        if(this.getNome_programa().equals(" "))
            programas.setNome_programa(programas.getNome_programa());
        else programas.setNome_programa(this.getNome_programa());

        if(this.getDescricao_programa().equals(" "))
            programas.setDescricao_programa(programas.getDescricao_programa());
        else programas.setDescricao_programa(this.getDescricao_programa());

        if(this.getDataFim() == null)
            programas.setDataFim(programas.getDataFim());
        else programas.setDataFim(this.getDataFim());
        return programas;
    }

    public String getNome_programa() {
        return nome_programa;
    }

    public void setNome_programa(String nome_programa) {
        this.nome_programa = nome_programa;
    }

    public String getDescricao_programa() {
        return descricao_programa;
    }

    public void setDescricao_programa(String descricao_programa) {
        this.descricao_programa = descricao_programa;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
