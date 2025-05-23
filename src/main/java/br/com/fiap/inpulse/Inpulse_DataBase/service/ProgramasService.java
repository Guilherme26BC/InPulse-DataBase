package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.ProgramasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ProgramasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProgramasService {
    @Autowired
    private ProgramasRepository programasRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Programas criarPrograma(ProgramasRequestCreate dto){
        return programasRepository.save(dto.toModel());
    }

    public Optional<Programas> buscarPorId(Long id){
        return programasRepository.findById(id);
    }

    public List<Programas> buscarTodas(){
        return programasRepository.findAll();
    }
}
