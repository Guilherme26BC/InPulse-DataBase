package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class ProgramasResponseCreate {
    private Long programa_id;
    private String nome_programa;
    private String descricao_programa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim;

    public ProgramasResponseCreate toDto(Programas programas){
        this.setPrograma_id(programas.getPrograma_id());
        this.setNome_programa(programas.getNome_programa());
        this.setDescricao_programa(programas.getDescricao_programa());
        this.setDataInicio(programas.getDataInicio());
        this.setDataFim(programas.getDataFim());
        return this;
    }
    public Long getPrograma_id() {
        return programa_id;
    }

    public void setPrograma_id(Long programa_id) {
        this.programa_id = programa_id;
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
