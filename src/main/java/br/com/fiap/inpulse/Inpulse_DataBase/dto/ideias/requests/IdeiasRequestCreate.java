package br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.CategoriasRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class IdeiasRequestCreate {
    private String nome;
    private String problema;
    private String descricao;
    private String imagem;
    private Long funcionario_id;
    private List<Long> categorias_id;
    public Ideias toModel(FuncionariosRepository funcionariosRepository, CategoriasRepository categoriasRepository){
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
        List<Categorias> categorias = categorias_id.stream().map(c->{
            Categorias cat = categoriasRepository.findById(c)
                    .orElseThrow(()-> new RuntimeException("Categoria não encontrada" + this.getCategorias_id()));
            return cat;
        }).collect(Collectors.toList());
        ideias.setCategorias(categorias);

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

    public List<Long> getCategorias_id() {
        return categorias_id;
    }

    public void setCategorias_id(List<Long> categorias_id) {
        this.categorias_id = categorias_id;
    }
}
