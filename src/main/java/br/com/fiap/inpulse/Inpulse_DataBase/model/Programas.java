package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
@Entity
public class Programas {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long programa_id;
    private String nome_programa;
    private String descricao_programa;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToMany
    @JoinTable(
            name = "programas_funcionarios",
            joinColumns = @JoinColumn(name = "programas_programa_id"), // FK da tabela 'programas'
            inverseJoinColumns = @JoinColumn(name = "funcionarios_funcionario_id") // FK da tabela 'funcionarios'
    )
    private List<Funcionarios> funcionarios;

    @ManyToMany
    @JoinTable(name = "programas_ideias",
            joinColumns = @JoinColumn(name = "ideias"),
            inverseJoinColumns = @JoinColumn(name = "programas"))
    private List<Ideias> ideias;

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

    public List<Funcionarios> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionarios> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public List<Ideias> getIdeias() {
        return ideias;
    }

    public void setIdeias(List<Ideias> ideias) {
        this.ideias = ideias;
    }
}
