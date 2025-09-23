package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.requests.MissoesRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.requests.MissoesRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.responses.MissoesResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.responses.MissoesResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.MissoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/missoes")
public class ControllerMissoes {

    @Autowired
    private MissoesService missoesService;

    @PostMapping
    public ResponseEntity<MissoesResponseCreate> criarMissao(@RequestBody MissoesRequestCreate dto){
        return ResponseEntity.ok().body(new MissoesResponseCreate().toDto(missoesService.criarMissao(dto)));
    }
    @GetMapping
    public ResponseEntity<List<MissoesResponse>> buscarTodas(){
        return ResponseEntity.ok().body(missoesService.buscarTodas().stream().map(
                m-> new MissoesResponse().toDto(m)).collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<MissoesResponse> buscarPorId(@PathVariable Long id){
        return missoesService.buscarporId(id).map(m-> new MissoesResponse().toDto(m))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<MissoesResponse> atualizarMissao(@PathVariable Long id, MissoesRequestUpdate dto){
        return missoesService.atualizarMissao(id,dto).map(m-> new MissoesResponse().toDto(m))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
   @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMissao(@PathVariable Long id){
        if(missoesService.deleteItem(id)){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
   }
}
