package br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.time.LocalDate;

public class IdeiasResponseCreate {
    private Long ideia_id;
    private String nome;
    private String problema;
    private String descricao;
    private String imagem;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate data;
    private BigInteger curtidas;
    private String funcionario;

    public IdeiasResponseCreate toDto(Ideias ideias){
        this.setIdeia_id(ideias.getIdeia_id());
        this.setNome(ideias.getNome());
        this.setProblema(ideias.getProblema());
        this.setDescricao(ideias.getDescricao());
        this.setImagem(ideias.getImagem());
        this.setData(ideias.getData());
        this.setCurtidas(ideias.getCurtidas());
        this.setFuncionario(ideias.getFuncionario().getPrimeiro_nome() + " " + ideias.getFuncionario().getUltimo_sobrenome());
        return this;
    }

    public Long getIdeia_id() {
        return ideia_id;
    }

    public void setIdeia_id(Long ideia_id) {
        this.ideia_id = ideia_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigInteger getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(BigInteger curtidas) {
        this.curtidas = curtidas;
    }

    public String getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(String funcionario) {
        this.funcionario = funcionario;
    }
}
