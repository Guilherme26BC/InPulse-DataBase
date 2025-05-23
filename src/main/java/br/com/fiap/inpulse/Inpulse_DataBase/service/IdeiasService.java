package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.IdeiasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.IdeiasResponseCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdeiasService {

    @Autowired
    private IdeiasRepository ideiasRepository;

    public Ideias criarIdeia(IdeiasRequestCreate dto){
        return ideiasRepository.save(dto.toModel());
    }

    public Optional<Ideias> buscarIdeiaPorId(Long id){
        return ideiasRepository.findById(id);
    }

    public List<Ideias> buscarTodas(){
        return ideiasRepository.findAll();
    }
}
