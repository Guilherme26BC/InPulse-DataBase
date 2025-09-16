package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.itens.requests.ItensRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.itens.requests.ItensRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Item;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Item createItem(ItensRequestCreate dto){
        return itemRepository.save(dto.toModel());
    }
    public Optional<Item> getItemId(Long id){
        return itemRepository.findById(id);
    }
    public List<Item> getAll(){
        return itemRepository.findAll();
    }
    public Optional<Item> alterItem(Long id, ItensRequestUpdate dto){
        return itemRepository.findById(id).map(i -> itemRepository.save(dto.toModel(i)));
    }
    public boolean deleteItem(Long id){
        if(itemRepository.existsById(id)){
            itemRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    public Item atualizarItemFuncionario(Long idItem, Long idfuncionario){
        Funcionarios funcionario = funcionariosRepository.findById(idfuncionario).orElseThrow(() -> new RuntimeException());
        Item it = itemRepository.findById(idItem).orElseThrow(() -> new RuntimeException());
        it.getFuncionarios().add(funcionario);
        return itemRepository.save(it);
    }
}
