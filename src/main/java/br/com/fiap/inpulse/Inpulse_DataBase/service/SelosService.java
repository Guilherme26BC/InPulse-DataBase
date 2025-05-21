package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.selos.SelosRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.SelosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SelosService {
    @Autowired
    private SelosRepository selosRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Selos criarSelos(SelosRequestCreate dto){
        return selosRepository.save(dto.toModel());
    }

    public Optional<Selos> buscarSeloPorId(Long id){
        return selosRepository.findById(id);
    }
    public List<Selos> buscarTodos(){
        return selosRepository.findAll();
    }
}
