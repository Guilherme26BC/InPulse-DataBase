package br.com.fiap.inpulse.Inpulse_DataBase.dto;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriasResponse {
    private Long categoria_id;
    private String tipo;
    private String nome;
    private String icone;
    private List<String> ideiasNomes;

public CategoriasResponse toDto(Categorias categorias){
    this.setCategoria_id(categorias.getCategoria_id());
    this.setIcone(categorias.getTipo());
    this.setNome(categorias.getNome());
    this.setTipo(categorias.getTipo());
    List<String> nomes = categorias.getIdeias()
           .stream()
           .map(i -> i.getNome()).collect(Collectors.toList());

    this.setIdeiasNomes(nomes);
    return this;
}
    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public List<String> getIdeiasNomes() {
        return ideiasNomes;
    }

    public void setIdeiasNomes(List<String> ideiasNomes) {
        this.ideiasNomes = ideiasNomes;
    }
}
