package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.List;

public class ProgramasRequestCreate {
    private String nome_programa;
    private String descricao_programa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim;

    public Programas toModel(){
        Programas programas = new Programas();
        programas.setNome_programa(this.getNome_programa());
        programas.setDescricao_programa(this.getDescricao_programa());
        programas.setDataInicio(this.getDataInicio());
        programas.setDataFim(this.getDataFim());
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}
