package br.com.fiap.inpulse.Inpulse_DataBase.dto;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.List;

public class CategoriasRequestCreate {
    private String tipo;
    private String nome;
    private String icone;

    public Categorias toModel(){
        Categorias categorias = new Categorias();
        categorias.setTipo(this.getTipo());
        categorias.setNome(this.getNome());
        categorias.setIcone(this.getIcone());
        return categorias;
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
}
