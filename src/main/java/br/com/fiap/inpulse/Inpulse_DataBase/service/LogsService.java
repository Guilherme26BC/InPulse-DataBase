package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.logs.LogsRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Logs;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.LogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LogsService {

    @Autowired
    private LogsRepository logsRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Logs criarLogs(LogsRequestCreate dto){
        return logsRepository.save(dto.toModel(funcionariosRepository));
    }

    public List<Logs> buscarTodos(){
        return logsRepository.findAll();
    }
    public Optional<Logs> buscarPorId(Long id){
        return logsRepository.findById(id);
    }
}
