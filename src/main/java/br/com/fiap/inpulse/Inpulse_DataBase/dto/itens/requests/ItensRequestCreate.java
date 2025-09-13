package br.com.fiap.inpulse.Inpulse_DataBase.dto.itens.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Item;

import java.math.BigDecimal;

public class ItensRequestCreate {
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String tier;

    public Item toModel(){
        Item item = new Item();
        item.setNome(this.getNome());
        item.setDescricao(this.getDescricao());
        item.setPreco(this.getPreco());
        item.setTier(this.getTier());
        return item;
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
