package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias.CategoriasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.CategoriasRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;
    @Autowired
    private IdeiasRepository ideiasRepository;

    public Categorias criarCategoria(CategoriasRequestCreate dto){
        return categoriasRepository.save(dto.toModel());
    }

    public Optional<Categorias> buscarPorId(Long id){
        return categoriasRepository.findById(id);
    }

    public List<Categorias> buscarTodas(){
        return categoriasRepository.findAll();
    }
}
