package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdateFuncionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdateIdeias;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses.ProgramasResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses.ProgramasResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.ProgramasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/programas") // Adicionado o prefixo '/api'
public class ControllerProgramas {

    @Autowired
    private ProgramasService programasService;

    @PostMapping
    public ResponseEntity<ProgramasResponseCreate> criarProgramas(@RequestBody ProgramasRequestCreate dto){
        return ResponseEntity.ok().body(new ProgramasResponseCreate().toDto(programasService.criarPrograma(dto)));
    }

    @GetMapping
    public ResponseEntity<List<ProgramasResponse>> buscarTodos(){
        return ResponseEntity.ok().body(programasService.buscarTodas()
                .stream().map(new ProgramasResponse()::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramasResponse> buscarPorId(@PathVariable Long id){
        return programasService.buscarPorId(id).map(new ProgramasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrograma(@PathVariable Long id){
        if(programasService.deletarPrograma(id)){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProgramasResponse> atualizarPrograma(@PathVariable Long id, @RequestBody ProgramasRequestUpdate dto){
        return programasService.atualizarPrograma(id, dto)
                .map(new ProgramasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/ideias/{id}")
    public ResponseEntity<ProgramasResponse> atualizarProgramaIdeia(@PathVariable Long id, @RequestBody ProgramasRequestUpdateIdeias dto){
        return programasService.atualizarProgramaIdeia(id, dto)
                .map(new ProgramasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<ProgramasResponse> atualizarProgramasFuncionarios(@PathVariable Long id, @RequestBody ProgramasRequestUpdateFuncionarios dto){
        return programasService.atualizarProgramaFuncionario(id,dto)
                .map(new ProgramasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}