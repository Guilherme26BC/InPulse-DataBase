package br.com.fiap.inpulse.Inpulse_DataBase.dto.contribuicoes.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Contribuicoes;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;

public class ContribuicoesRequestCreate {
    private String comentario;
    private Long funcionario_id;
    private Long ideia_id;

    public Contribuicoes toModel(FuncionariosRepository funcionariosRepository, IdeiasRepository ideiasRepository){
        Contribuicoes contribuicoes = new Contribuicoes();
        contribuicoes.setComentario(this.getComentario());

        Funcionarios funcionario = funcionariosRepository.findById(this.getFuncionario_id())
                .orElseThrow(()-> new RuntimeException("Funcionário não encontrado " + this.getFuncionario_id()));
        contribuicoes.setFuncionario(funcionario);
        Ideias ideia = ideiasRepository.findById(this.getIdeia_id())
                .orElseThrow(()-> new RuntimeException("Ideia não encontrada " + this.getIdeia_id()));
        contribuicoes.setIdeia(ideia);
        return contribuicoes;
    }
    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Long funcionario_id) {
        this.funcionario_id = funcionario_id;
    }

    public Long getIdeia_id() {
        return ideia_id;
    }

    public void setIdeia_id(Long ideia_id) {
        this.ideia_id = ideia_id;
    }
}
