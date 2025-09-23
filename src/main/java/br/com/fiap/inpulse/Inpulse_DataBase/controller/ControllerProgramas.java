package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdateFuncionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdateIdeias;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses.ProgramasResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses.ProgramasResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.ProgramasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programas")
public class ControllerProgramas {

    private final ProgramasService programasService;

    public ControllerProgramas(ProgramasService programasService) {
        this.programasService = programasService;
    }

    @PostMapping
    public ResponseEntity<ProgramasResponseCreate> criarProgramas(@RequestBody ProgramasRequestCreate dto) {
        return ResponseEntity.ok(new ProgramasResponseCreate().toDto(programasService.criarPrograma(dto)));
    }

    @GetMapping
    public ResponseEntity<List<ProgramasResponse>> buscarTodos() {
        return ResponseEntity.ok(programasService.buscarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgramasResponse> buscarPorId(@PathVariable Long id) {
        return programasService.buscarPorId(id)
                .map(new ProgramasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPrograma(@PathVariable Long id) {
        if (programasService.deletarPrograma(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgramasResponse> atualizarPrograma(@PathVariable Long id, @RequestBody ProgramasRequestUpdate dto) {
        return programasService.atualizarPrograma(id, dto)
                .map(new ProgramasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/ideias/{id}")
    public ResponseEntity<ProgramasResponse> atualizarProgramaIdeia(@PathVariable Long id, @RequestBody ProgramasRequestUpdateIdeias dto) {
        return programasService.atualizarProgramaIdeia(id, dto)
                .map(new ProgramasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<ProgramasResponse> atualizarProgramasFuncionarios(@PathVariable Long id, @RequestBody ProgramasRequestUpdateFuncionarios dto) {
        return programasService.atualizarProgramaFuncionario(id, dto)
                .map(new ProgramasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}