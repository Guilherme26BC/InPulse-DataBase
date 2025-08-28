package br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;

public class ProgramasResponseIF {
    Long programa_id;
    String nome;

    public ProgramasResponseIF toDto(Programas programas){
        this.setPrograma_id(programas.getPrograma_id());
        this.setNome(programas.getNome_programa());
        return this;
    }

    public Long getPrograma_id() {
        return programa_id;
    }

    public void setPrograma_id(Long programa_id) {
        this.programa_id = programa_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
