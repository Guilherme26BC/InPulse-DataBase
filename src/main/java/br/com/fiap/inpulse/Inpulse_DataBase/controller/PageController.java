package br.com.fiap.inpulse.Inpulse_DataBase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


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

    @GetMapping("/programas")
    public String programasPage() {
        return "programas"; // O Spring Boot procurará por 'programas.html'
    }

    @GetMapping("/usuarios")
    public String funcionariosPage() {
        return "funcionarios";
    }
    
    @GetMapping("/itens")
    public String itensPage() {
        return "itens";
    }
    
}