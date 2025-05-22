package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.FuncionariosRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionariosService {

    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Funcionarios criarFuncionario(FuncionariosRequestCreate dto){
        return funcionariosRepository.save(dto.toModel());
    }

    public Optional<Funcionarios> buscarFuncionarioPorId(Long id){
        return funcionariosRepository.findById(id);
    }

    public List<Funcionarios> buscarTodos(){
        return funcionariosRepository.findAll();
    }
}
