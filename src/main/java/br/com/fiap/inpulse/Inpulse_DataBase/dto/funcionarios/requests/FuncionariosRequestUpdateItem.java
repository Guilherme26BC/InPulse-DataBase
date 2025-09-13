package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Item;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FuncionariosRequestUpdateItem {
    private List<Long> itens_id;

    public Funcionarios toModel(Funcionarios funcionarios, ItemRepository itensRepository){
        List itensAUX = this.getItens_id().stream().map(i ->{
            Item itemaux = itensRepository.findById(i).orElseThrow(()->new RuntimeException("Item não encontrado " + this.getItens_id()));
            return itemaux;
        }).collect(Collectors.toList());
        funcionarios.setItens(itensAUX);
        return funcionarios;
    }
    public List<Long> getItens_id() {
        return itens_id;
    }

    public void setItens_id(List<Long> itens_id) {
        this.itens_id = itens_id;
    }
}
