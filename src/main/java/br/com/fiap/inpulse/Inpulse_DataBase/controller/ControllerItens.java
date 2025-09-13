package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.itens.requests.ItensRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.itens.requests.ItensRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.itens.responses.ItemResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("itens")
public class ControllerItens {
    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemResponse> criarItem(@RequestBody ItensRequestCreate dto){
        return ResponseEntity.ok().body(new ItemResponse().toDto(itemService.createItem(dto)));
    }
    @GetMapping
    public ResponseEntity<List<ItemResponse>> buscarTodas(){
        return ResponseEntity.ok().body(itemService.getAll().stream().map(
                i-> new ItemResponse().toDto(i)).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> buscarPorId(@PathVariable Long id){
        return itemService.getItemId(id).map(i -> new ItemResponse().toDto(i))
                .map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponse> atualizar(@PathVariable Long id, @RequestBody ItensRequestUpdate dto){
        return itemService.alterItem(id,dto).map(i -> new ItemResponse().toDto(i))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarIdeia(@PathVariable Long id){
        if(itemService.deleteItem(id)){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
