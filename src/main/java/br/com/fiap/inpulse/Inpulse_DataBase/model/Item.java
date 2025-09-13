package br.com.fiap.inpulse.Inpulse_DataBase.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String tier;
    @ManyToMany
    @JoinTable(
            name = "itens_funcionarios",
            joinColumns = @JoinColumn(name = "iten_itens_id"), // FK da tabela 'programas'
            inverseJoinColumns = @JoinColumn(name = "funcionarios_funcionario_id") // FK da tabela 'funcionarios'
    )
    private List<Funcionarios> funcionarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public List<Funcionarios> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionarios> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
