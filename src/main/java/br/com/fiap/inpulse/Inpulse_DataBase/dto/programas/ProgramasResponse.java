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
import java.util.stream.Collectors;

public class ProgramasResponse {
    private Long programa_id;
    private String nome_programa;
    private String descricao_programa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataInicio;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataFim;
    private List<String> funcionarios_nome;
    private List<String> ideias_nome;

    public ProgramasResponse toDto(Programas programas){
        this.setPrograma_id(programas.getPrograma_id());
        this.setNome_programa(programas.getNome_programa());
        this.setDescricao_programa(programas.getDescricao_programa());
        this.setDataInicio(programas.getDataInicio());
        this.setDataFim(programas.getDataFim());
        List<String> nomesFun = programas.getFuncionarios().stream()
                .map(p->{  return p.getPrimeiro_nome() + " " + p.getUltimo_sobrenome();
    }).collect(Collectors.toList());
        this.setFuncionarios_nome(nomesFun);
        List<String> nomesIdeias = programas.getIdeias().stream()
                .map(p->{  return p.getNome();
                }).collect(Collectors.toList());
        this.setIdeias_nome(nomesIdeias);
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

    public List<String> getFuncionarios_nome() {
        return funcionarios_nome;
    }

    public void setFuncionarios_nome(List<String> funcionarios_nome) {
        this.funcionarios_nome = funcionarios_nome;
    }

    public List<String> getIdeias_nome() {
        return ideias_nome;
    }

    public void setIdeias_nome(List<String> ideias_nome) {
        this.ideias_nome = ideias_nome;
    }
}
