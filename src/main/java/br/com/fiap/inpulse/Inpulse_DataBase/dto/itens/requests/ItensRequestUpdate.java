package br.com.fiap.inpulse.Inpulse_DataBase.dto.itens.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Item;

import java.math.BigDecimal;
import java.math.BigInteger;

public class ItensRequestUpdate {
    private BigDecimal preco;


    public Item toModel(Item item) {
        if (this.getPreco().compareTo(new BigDecimal("-1")) <= 0) {
            item.setPreco(item.getPreco());
        } else {
            item.setPreco(this.getPreco());
        }
        return item;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

}
