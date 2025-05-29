package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;
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
    @Autowired
    private IdeiasRepository ideiasRepositorys;

    public Programas criarPrograma(ProgramasRequestCreate dto){
        return programasRepository.save(dto.toModel());
    }

    public Optional<Programas> buscarPorId(Long id){
        return programasRepository.findById(id);
    }

    public List<Programas> buscarTodas(){
        return programasRepository.findAll();
    }

    public boolean deletarPrograma(Long id){
        if(programasRepository.existsById(id)){
            programasRepository.deleteById(id);
            return true;
        }
        else{
            return false;
        }
    }
    public Optional<Programas> atualizarPrograma(Long id, ProgramasRequestUpdate dto){
        return programasRepository.findById(id)
                .map(p-> programasRepository.save(dto.toModel(p, funcionariosRepository, ideiasRepositorys)));
    }
}
