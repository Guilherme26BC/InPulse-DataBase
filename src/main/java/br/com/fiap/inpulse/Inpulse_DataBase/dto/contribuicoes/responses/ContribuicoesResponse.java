package br.com.fiap.inpulse.Inpulse_DataBase.dto.contribuicoes.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Contribuicoes;

public class ContribuicoesResponse {
    private Long id;
    private String comentario;
    private String funcionario;
    private String ideia;

    public ContribuicoesResponse toDto(Contribuicoes contribuicoes){
        this.setId(contribuicoes.getId());
        this.setComentario(contribuicoes.getComentario());
        this.setFuncionario(contribuicoes.getFuncionario().getPrimeiro_nome() + " " +
                contribuicoes.getFuncionario().getUltimo_sobrenome());
        this.setIdeia("id: " + contribuicoes.getIdeia().getIdeia_id() +
                " nome: " + contribuicoes.getIdeia().getNome());
        return this;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }

    public String getIdeia() {
        return ideia;
    }

    public void setIdeia(String ideia) {
        this.ideia = ideia;
    }
}
