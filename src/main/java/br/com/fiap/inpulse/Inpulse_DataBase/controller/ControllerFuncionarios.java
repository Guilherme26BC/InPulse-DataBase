package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests.FuncionariosRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests.FuncionariosRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests.FuncionariosRequestUpdateImagem;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests.FuncionariosRequestUpdateSenha;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.responses.FuncionariosResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.responses.FuncionariosResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
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
    public ResponseEntity<FuncionariosResponseCreate> criarFuncionario(@RequestBody FuncionariosRequestCreate dto){
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
                .map(f->new FuncionariosResponse().toModel(f)).collect(Collectors.toList()));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<FuncionariosResponse> buscarPorEmail(@PathVariable String email){
        return funcionariosService.buscarFuncionarioPorEmail(email).map(f-> new FuncionariosResponse().toModel(f))
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id){
        if(funcionariosService.deleteFuncionario(id)){
            return ResponseEntity.status(204).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<FuncionariosResponse> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionariosRequestUpdate dto){
        return funcionariosService.atualizarFuncionarios(id, dto)
                .map(f -> new FuncionariosResponse().toModel(f))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/imagem/{id}")
    public ResponseEntity<FuncionariosResponse> atualizarImagemFuncionario(@PathVariable Long id, @RequestBody FuncionariosRequestUpdateImagem dto){
        return funcionariosService.atualizarImagem(id, dto)
                .map(f-> new FuncionariosResponse().toModel(f))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("senha/{id}")
    public ResponseEntity<FuncionariosResponse> atualizarSenhaFuncionario(@PathVariable Long id, @RequestBody FuncionariosRequestUpdateSenha dto){
        return funcionariosService.atualizarSenha(id,dto)
                .map(f-> new FuncionariosResponse().toModel(f))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
