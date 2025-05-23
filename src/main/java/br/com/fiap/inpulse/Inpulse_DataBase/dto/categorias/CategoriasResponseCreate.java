package br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;

public class CategoriasResponseCreate {
    private Long categoria_id;
    private String tipo;
    private String nome;
    private String icone;

    public CategoriasResponseCreate toDto(Categorias categorias){
        this.setCategoria_id(categorias.getCategoria_id());
        this.setTipo(categorias.getTipo());
        this.setNome(categorias.getNome());
        this.setIcone(categorias.getIcone());
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
}
