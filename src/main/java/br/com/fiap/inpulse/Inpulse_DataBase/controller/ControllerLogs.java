package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.logs.LogsRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.logs.LogsResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Logs;
import br.com.fiap.inpulse.Inpulse_DataBase.model.LogsStatus;
import br.com.fiap.inpulse.Inpulse_DataBase.service.LogsService;
import org.apache.coyote.Response;
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
    public ResponseEntity<LogsResponse> criarLog(@RequestBody LogsRequestCreate dto,  LogsStatus status){
        return ResponseEntity.ok().body(new LogsResponse().toDto(logsService.criarLogs(dto, status)));
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        if(logsService.deletarLog(id)){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
