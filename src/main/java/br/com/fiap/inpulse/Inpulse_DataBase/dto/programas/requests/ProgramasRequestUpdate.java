package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ProgramasRequestUpdate {
    private String nome_programa;
    private String descricao_programa;
    private LocalDate dataFim;
    private List<Long> ideias_id;

    public Programas toModel(Programas programas, FuncionariosRepository funcionariosRepository,
                             IdeiasRepository ideiasRepository){
        if(this.getNome_programa().equals(" "))
            programas.setNome_programa(programas.getNome_programa());
        else programas.setNome_programa(this.getNome_programa());

        if(this.getDescricao_programa().equals(" "))
            programas.setDescricao_programa(programas.getDescricao_programa());
        else programas.setDescricao_programa(this.getDescricao_programa());

        if(this.getDataFim() == null)
            programas.setDataFim(programas.getDataFim());
        else programas.setDataFim(this.getDataFim());

        List<Funcionarios> aux = this.getIdeias_id().stream().map(f->{
                Ideias funAux = ideiasRepository.findById(f).orElseThrow(()->
                        new RuntimeException("Funcionario inexistente: " + this.getIdeias_id()));
                return funAux.getFuncionario();
            }).collect(Collectors.toList());
            programas.setFuncionarios(aux);

        List<Ideias> auxi =this.getIdeias_id().stream().map(i->{
            Ideias ideAux = ideiasRepository.findById(i).orElseThrow(()->
                    new RuntimeException("ideia inexistente: " + this.getIdeias_id()));
            return ideAux;
        }).collect(Collectors.toList()); ;
        programas.setIdeias(auxi);

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

    public List<Long> getIdeias_id() {
        return ideias_id;
    }

    public void setIdeias_id(List<Long> ideias_id) {
        this.ideias_id = ideias_id;
    }
}
