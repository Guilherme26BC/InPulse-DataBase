package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.selos.SelosRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.selos.SelosResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.selos.SelosResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;
import br.com.fiap.inpulse.Inpulse_DataBase.service.SelosService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("selos")
public class ControllerSelos {
    @Autowired
    private SelosService selosService;

    @PostMapping
    public ResponseEntity<SelosResponseCreate> criarSelos(@RequestBody SelosRequestCreate dto){
        return ResponseEntity.ok().body(new SelosResponseCreate().toDto(selosService.criarSelos(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SelosResponse> buscarSeloPorId(@PathVariable Long id){
        return selosService.buscarSeloPorId(id).map(new SelosResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SelosResponse>> buscarTodos(){
        return ResponseEntity.ok().body(selosService.buscarTodos().stream()
                .map(s->new SelosResponse().toDto(s))
                .collect(Collectors.toList()));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSelos(@PathVariable Long id){
        if(selosService.deletarSelos(id)){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
