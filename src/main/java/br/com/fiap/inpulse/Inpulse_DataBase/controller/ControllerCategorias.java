package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias.requests.CategoriasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias.requests.CategoriasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias.responses.CategoriasResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias.responses.CategoriasResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.CategoriasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categorias")
public class ControllerCategorias {

    @Autowired
    private CategoriasService categoriasService;

    @PostMapping
    public ResponseEntity<CategoriasResponseCreate> criarCategoria(@RequestBody CategoriasRequestCreate dto){
        return ResponseEntity.ok().body(new CategoriasResponseCreate()
                .toDto(categoriasService.criarCategoria(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasResponse> buscarCategoriaPorId(@PathVariable Long id){
        return categoriasService.buscarPorId(id).map(c-> new CategoriasResponse().toDto(c))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<CategoriasResponse>> bucarTodasCategorias(){
        return ResponseEntity.ok().body(categoriasService.buscarTodas().stream()
                .map(new CategoriasResponse()::toDto).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategorias(@PathVariable Long id){
        if(categoriasService.deletearCategoria(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoriasResponse> atualizarCategorias(@PathVariable Long id, @RequestBody CategoriasRequestUpdate dto){
        return categoriasService.atualizarCategoria(id, dto).map(c-> new CategoriasResponse().toDto(c))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
