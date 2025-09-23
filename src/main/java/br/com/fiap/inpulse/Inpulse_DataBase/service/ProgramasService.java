package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdateFuncionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.requests.ProgramasRequestUpdateIdeias;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses.ProgramasResponse;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ProgramasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProgramasService {
    @Autowired
    private ProgramasRepository programasRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;
    @Autowired
    private IdeiasRepository ideiasRepositorys;
    @Autowired
    private FuncionariosService funcionariosService;
    @Autowired
    private IdeiasService ideiasService;

    public Programas criarPrograma(ProgramasRequestCreate dto){
        return programasRepository.save(dto.toModel());
    }

    public List<ProgramasResponse> buscarTodas() {
        List<Programas> programas = programasRepository.findAllWithDetails();
        List<ProgramasResponse> dtos = new ArrayList<>();
        for (Programas p : programas) {
            ProgramasResponse dto = new ProgramasResponse();
            dto.setPrograma_id(p.getPrograma_id());
            dto.setNome_programa(p.getNome_programa());
            dto.setDataInicio(p.getDataInicio());
            dto.setDataFim(p.getDataFim());
            dto.setFuncionarios_nome(
                p.getFuncionarios().stream().map(Funcionarios::getNome).distinct().toList()
            );
            dto.setIdeias_nome(
                p.getIdeias().stream().map(Ideias::getNome).distinct().toList()
            );
            dtos.add(dto);
        }
        return dtos;
    }
    
    public Optional<Programas> buscarPorId(Long id) {
        return programasRepository.findById(id);
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
                .map(p-> programasRepository.save(dto.toModel(p, funcionariosRepository)));
    }
//    public Optional<Programas> atualizarProgramaIdeia(Long id, ProgramasRequestUpdateIdeias dto){
//        ideiasService.atualizarPrograma(id, dto.getIdeias_id());
//        return programasRepository.findById(id)
//                .map(p-> programasRepository.save(dto.toModel(p,ideiasRepositorys)));
//    }
    public Optional<Programas> atualizarProgramaIdeia(Long id, ProgramasRequestUpdateIdeias dto){
        ideiasService.atualizarPrograma(id,dto.getIdeias_id());
        return programasRepository.findById(id)
                .map(p -> programasRepository.save(dto.toModel(p,ideiasRepositorys)));
    }
    public Optional<Programas> atualizarProgramaFuncionario(Long id, ProgramasRequestUpdateFuncionarios dto){
          funcionariosService.atualizarPrograma(id, dto.getFuncionarios_id());
        return programasRepository.findById(id)
                .map(p -> programasRepository.save(dto.toModel(p,funcionariosRepository)));
    }
}
