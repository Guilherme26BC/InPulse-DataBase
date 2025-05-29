package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses.ProgramasResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses.ProgramasResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.ProgramasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("programas")
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
                .stream().map(p->new ProgramasResponse().toDto(p))
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramasResponse> buscarPorId(@PathVariable Long id){
        return programasService.buscarPorId(id).map(p ->new ProgramasResponse().toDto(p))
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
                .map(p-> new ProgramasResponse().toDto(p))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
