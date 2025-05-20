package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long categoria_id;
    private String tipo;
    @Column(unique = true)
    private String nome;
    @Column(unique = true)
    private String icone;
    @ManyToMany
    @JoinTable(name = "categorias_ideias",
    joinColumns = @JoinColumn(name = "ideias"),
    inverseJoinColumns = @JoinColumn(name = "categorias"))
    private List<Ideias> ideias;

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

    public List<Ideias> getIdeias() {
        return ideias;
    }

    public void setIdeias(List<Ideias> ideias) {
        this.ideias = ideias;
    }
}
