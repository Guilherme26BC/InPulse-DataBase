package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests.IdeiasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests.IdeiasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests.IdeiasRequestUpdateStatus;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.responses.IdeiasResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.responses.IdeiasResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.IdeiasService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ideias")
public class ControllerIdeias {

    private final IdeiasService ideiasService;

    public ControllerIdeias(IdeiasService ideiasService) {
        this.ideiasService = ideiasService;
    }

    @PostMapping
    public ResponseEntity<IdeiasResponseCreate> criarIdeias(@RequestBody IdeiasRequestCreate dto) {
        return ResponseEntity.ok().body(new IdeiasResponseCreate().toDto(ideiasService.criarIdeia(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IdeiasResponse> buscarIdeiaPorId(@PathVariable Long id) {
        return ideiasService.buscarIdeiaPorId(id).map(new IdeiasResponse()::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<IdeiasResponse>> buscarTodas() {
        return ResponseEntity.ok().body(ideiasService.buscarTodas().stream()
                .map(i -> new IdeiasResponse().toDto(i)).collect(Collectors.toList()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarIdeia(@PathVariable Long id) {
        if (ideiasService.deletarIdeia(id)) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<IdeiasResponse> atualizarIdeia(@PathVariable Long id, @RequestBody IdeiasRequestUpdate dto) {
        return ideiasService.atualizarIdeias(id, dto).map(i -> new IdeiasResponse().toDto(i))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<List<IdeiasResponse>> buscarIdeiaFuncionario(@PathVariable Long id) {
        return ResponseEntity.ok().body(ideiasService.buscarPorFuncionario(id).stream()
                .map(i -> new IdeiasResponse().toDto(i)).collect(Collectors.toList()));
    }

    @PutMapping("/{id}/status") // Novo endpoint
    public ResponseEntity<IdeiasResponse> atualizarStatusIdeia(@PathVariable Long id,
            @RequestBody IdeiasRequestUpdateStatus dto) {
        return ideiasService.atualizarStatus(id, dto)
                .map(i -> new IdeiasResponse().toDto(i))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}