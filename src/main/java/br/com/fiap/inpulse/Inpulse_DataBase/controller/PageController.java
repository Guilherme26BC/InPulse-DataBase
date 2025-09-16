package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageController {

    @GetMapping("/ideias")
    public String ideiasPage() {
        return "ideias"; // O Spring Boot procurará por 'ideias.html' na pasta 'templates'
    }

    @GetMapping("/login")
    public String loginPage() {
        return "index"; // O Spring Boot procurará por 'index.html'
    }

    @GetMapping("/usuarios")
    public String funcionariosPage() {
        return "funcionarios";
    }
    
}