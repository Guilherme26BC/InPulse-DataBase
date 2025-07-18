package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests.FuncionariosRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests.FuncionariosRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.SelosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionariosService {

    @Autowired
    private FuncionariosRepository funcionariosRepository;
    @Autowired
    private SelosRepository selosRepository;
    public Funcionarios criarFuncionario(FuncionariosRequestCreate dto){
        return funcionariosRepository.save(dto.toModel());
    }

    public Optional<Funcionarios> buscarFuncionarioPorId(Long id){
        return funcionariosRepository.findById(id);
    }

    public List<Funcionarios> buscarTodos(){
        return funcionariosRepository.findAll();
    }

    public boolean deleteFuncionario(Long id){
        if(funcionariosRepository.existsById(id)){
            funcionariosRepository.deleteById(id);
            return true;
        }else
            return false;
    }

    public Optional<Funcionarios> atualizarFuncionarios(Long id, FuncionariosRequestUpdate dto){
        return funcionariosRepository.findById(id)
                .map( f -> funcionariosRepository.save(dto.toModel(f, selosRepository)));
    }
    public Optional<Funcionarios> buscarFuncionarioPorEmail(String email){
        return funcionariosRepository.findByEmail(email);
    }
}
