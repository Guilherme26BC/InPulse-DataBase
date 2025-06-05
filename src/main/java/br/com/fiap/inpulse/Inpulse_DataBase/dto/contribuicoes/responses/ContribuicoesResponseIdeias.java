package br.com.fiap.inpulse.Inpulse_DataBase.dto.contribuicoes.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Contribuicoes;

public class ContribuicoesResponseIdeias {
    private String coment;
    private String nomeAutor;

    public ContribuicoesResponseIdeias toDto(Contribuicoes contribuicoes){
        this.setComent(contribuicoes.getComentario());
        this.setNomeAutor((contribuicoes.getFuncionario().getPrimeiro_nome()+" "+ contribuicoes.getFuncionario().getUltimo_sobrenome()));
        return this;
    }
    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public String getNomeAutor() {
        return nomeAutor;
    }

    public void setNomeAutor(String nomeAutor) {
        this.nomeAutor = nomeAutor;
    }
}
