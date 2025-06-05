package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.contribuicoes.requests.ContribuicoesRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.contribuicoes.responses.ContribuicoesResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.service.ContribuicoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contribuicoes")
public class ControllerContribuicoes {
    @Autowired
    private ContribuicoesService contribuicoesService;

    @PostMapping
    public ResponseEntity<ContribuicoesResponse> criar(@RequestBody ContribuicoesRequestCreate dto){
    return ResponseEntity.ok().body(new ContribuicoesResponse().toDto(contribuicoesService.criarContribuicao(dto)));
    }
}
