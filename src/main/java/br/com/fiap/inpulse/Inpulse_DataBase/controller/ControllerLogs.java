package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.logs.LogsRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.logs.LogsResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Logs;
import br.com.fiap.inpulse.Inpulse_DataBase.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("logs")
public class ControllerLogs {

    @Autowired
    private LogsService logsService;
    @PostMapping
    public ResponseEntity<LogsResponse> criarLog(@RequestBody LogsRequestCreate dto){
        return ResponseEntity.ok().body(new LogsResponse().toDto(logsService.criarLogs(dto)));
    }
    @GetMapping
    public ResponseEntity<List<LogsResponse>> buscarTodos(){
        return ResponseEntity.ok().body(logsService.buscarTodos().stream()
                .map(l->new LogsResponse().toDto(l)).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogsResponse> buscarPorId(@PathVariable Long id){
        return logsService.buscarPorId(id).map(l-> new LogsResponse().toDto(l))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
