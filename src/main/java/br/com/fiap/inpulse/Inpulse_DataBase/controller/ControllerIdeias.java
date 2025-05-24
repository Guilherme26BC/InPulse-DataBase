package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.IdeiasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.IdeiasResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.IdeiasResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.IdeiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("ideias")
public class ControllerIdeias {

    @Autowired
    private IdeiasService ideiasService;

    @PostMapping
    public ResponseEntity<IdeiasResponseCreate> criarIdeias(@RequestBody IdeiasRequestCreate dto){
        return ResponseEntity.ok().body(new IdeiasResponseCreate().toDto(ideiasService.criarIdeia(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdeiasResponse> buscarIdeiaPorId(@PathVariable Long id){
        return ideiasService.buscarIdeiaPorId(id).map(new IdeiasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping
    public ResponseEntity<List<IdeiasResponse>> buscarTodas(){
        return ResponseEntity.ok().body(ideiasService.buscarTodas().stream()
                .map(i-> new IdeiasResponse().toDto(i)).collect(Collectors.toList()));
    }
}
