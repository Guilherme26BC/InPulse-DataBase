package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Item;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ItemRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.service.ItemService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class FuncionariosRequestUpdateItem {
    private List<Long> itens_id;

    public Funcionarios toModel(Funcionarios funcionarios, ItemRepository itensRepository, ItemService itemService) {
        BigDecimal moedasF = funcionarios.getMoedas();

        // Usando AtomicReference para permitir mutação dentro do lambda
        AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);

        Set<Item> itensAUX = this.getItens_id().stream().map(i -> {
            Item itemaux = itensRepository.findById(i)
                    .orElseThrow(() -> new RuntimeException("Item não encontrado " + this.getItens_id()));

            // Atualiza o valor de total dentro do lambda
            total.set(total.get().add(itemaux.getPreco())); // Atualiza o total dentro do AtomicReference

            return itemaux;
        }).collect(Collectors.toSet());

        // Verifica se o saldo é suficiente
        if (moedasF.compareTo(total.get()) < 0) {
            throw new RuntimeException("Saldo insuficiente. Moedas: " + moedasF + " é menor que o total de itens: " + total.get());
        } else {
            // Atualiza os itens e as moedas do funcionário
            funcionarios.setItens(itensAUX);
            funcionarios.setMoedas(moedasF.subtract(total.get())); // Subtração de moedas
            itensAUX.forEach(item -> itemService.atualizarItemFuncionario(item.getId(), funcionarios.getFuncionario_id()));
        }

        return funcionarios;
    }

    public List<Long> getItens_id() {
        return itens_id;
    }

    public void setItens_id(List<Long> itens_id) {
        this.itens_id = itens_id;
    }
}