package br.com.fiap.inpulse.Inpulse_DataBase.dto.itens.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class ItemResponseCreate {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String tier;

    public ItemResponseCreate toDto(Item item){
        this.setId(item.getId());
        this.setNome(item.getNome());
        this.setDescricao(item.getDescricao());
        this.setPreco(item.getPreco());
        this.setTier(item.getTier());

        return this;
    }
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
}


