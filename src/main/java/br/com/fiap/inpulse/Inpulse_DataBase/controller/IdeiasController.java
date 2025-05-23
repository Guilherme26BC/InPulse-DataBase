package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.IdeiasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.IdeiasResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.service.IdeiasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ideias")
public class IdeiasController {

    @Autowired
    private IdeiasService ideiasService;
    @PostMapping
    public ResponseEntity<IdeiasResponseCreate> criarIdeias(@RequestBody IdeiasRequestCreate dto){
        return ResponseEntity.ok().body(new IdeiasResponseCreate().toDto(ideiasService.criarIdeia(dto)));
    }

}
