package br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias.responses.CategoriasResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.CategoriasRepository;

import java.util.List;
import java.util.stream.Collectors;

public class IdeiasRequestUpdate {
    private List<Long> categorias_id;

    public Ideias toModel(Ideias ideias, CategoriasRepository categoriasRepository){
        List<Categorias> categorias = categorias_id.stream().map(c->{
            Categorias cat = categoriasRepository.findById(c).orElseThrow(()-> new RuntimeException("Cat nao encontrada"));
            return cat;
         }).collect(Collectors.toList());
        ideias.setCategorias(categorias);
        return ideias;
    }

    public List<Long> getCategorias_id() {
        return categorias_id;
    }

    public void setCategorias_id(List<Long> categorias_id) {
        this.categorias_id = categorias_id;
    }
}
