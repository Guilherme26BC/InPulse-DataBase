package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ideias", indexes = {
        @Index(name = "idx_funcionario_id", columnList = "funcionario_id")
})
public class Ideias {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ideia_id;

    @Column(unique = true)
    private String nome;

    private String problema;
    private String descricao;
    private String imagem;
    private LocalDate data;
    private BigInteger curtidas;
    private String status; // Adicione esta linha

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionarios funcionario;

    @ManyToMany
    private List<Programas> programas;

    @ManyToMany
    private Set<Categorias> categorias; // Altere List para Set

    @OneToMany(mappedBy = "ideia", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Contribuicoes> contribuicoes;

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

    // Adicione os métodos getter e setter para o campo 'status'
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Funcionarios getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    public List<Programas> getProgramas() {
        return programas;
    }

    public void setProgramas(List<Programas> programas) {
        this.programas = programas;
    }

    public Set<Categorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categorias> categorias) {
        this.categorias = categorias;
    }

    public Set<Contribuicoes> getContribuicoes() {
        return contribuicoes;
    }

    public void setContribuicoes(Set<Contribuicoes> contribuicoes) {
        this.contribuicoes = contribuicoes;
    }
}