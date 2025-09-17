package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.requests.MissoesRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Missoes;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.MissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    @Autowired
    private MissoesRepository missoesRepository;

    public Missoes criarMissao(MissoesRequestCreate dto){
        return missoesRepository.save(dto.toModel());
    }

    public Optional<Missoes>buscarporId(Long id){
        return missoesRepository.findById(id);
    }
    public List<Missoes> buscarTodas(){
        return missoesRepository.findAll();
    }
}
