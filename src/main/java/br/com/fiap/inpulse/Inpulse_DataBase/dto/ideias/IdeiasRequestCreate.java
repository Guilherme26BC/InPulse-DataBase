package br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;

import java.math.BigInteger;
import java.time.LocalDate;

public class IdeiasRequestCreate {
    private String nome;
    private String problema;
    private String descricao;
    private String imagem;
    private Long funcionario_id;

    public Ideias toModel(FuncionariosRepository funcionariosRepository){
        Ideias ideias = new Ideias();
        ideias.setNome(this.getNome());
        ideias.setProblema(this.getProblema());
        ideias.setDescricao(this.getDescricao());
        ideias.setImagem(this.getImagem());
        ideias.setData(LocalDate.now());
        ideias.setCurtidas(new BigInteger("0"));
        Funcionarios funcionarios = funcionariosRepository.findById(this.getFuncionario_id())
                        .orElseThrow(() ->
                                new RuntimeException("Funcionario inexistente: " + this.getFuncionario_id()));
        ideias.setFuncionario(funcionarios);
        return ideias;
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

    public Long getFuncionario_id() {
        return funcionario_id;
    }

    public void setFuncionario_id(Long funcionario_id) {
        this.funcionario_id = funcionario_id;
    }
}
