package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.FuncionariosRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.FuncionariosResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.FuncionariosResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.FuncionariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("funcionarios")
public class ControllerFuncionarios {
    @Autowired
    private FuncionariosService funcionariosService;

    @PostMapping
    public ResponseEntity<FuncionariosResponseCreate> criarFuncionario(FuncionariosRequestCreate dto){
        return ResponseEntity.ok().body(
                new FuncionariosResponseCreate()
                        .toModel(funcionariosService.criarFuncionario(dto)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionariosResponse> buscarPorId(@PathVariable Long id){
        return funcionariosService.buscarFuncionarioPorId(id).map(new FuncionariosResponse()::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FuncionariosResponse>> buscarTodos(){
        return ResponseEntity.ok().body(funcionariosService.buscarTodos().stream()
                .map(new FuncionariosResponse()::toModel).collect(Collectors.toList()));
    }
}
