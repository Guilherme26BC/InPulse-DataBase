package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.requests.MissoesRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.requests.MissoesRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Missoes;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.MissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {
    @Autowired
    private MissoesRepository missoesRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Missoes criarMissao(MissoesRequestCreate dto){
        return missoesRepository.save(dto.toModel());
    }

    public Optional<Missoes>buscarporId(Long id){
        return missoesRepository.findById(id);
    }
    public List<Missoes> buscarTodas(){
        return missoesRepository.findAll();
    }

    public Missoes atualizarFuncionario(Long idF, Long idM){
        Missoes missao = missoesRepository.findById(idM).orElseThrow(() -> new RuntimeException("Missão não encontrada"));
        Funcionarios funcionarios = funcionariosRepository.findById(idF).orElseThrow(()-> new RuntimeException("Funcionario não encontrado"));
        missao.getFuncionarios().add(funcionarios);
        return missoesRepository.save(missao);
    }
    public Optional<Missoes> atualizarMissao(Long id, MissoesRequestUpdate dto){
    return missoesRepository.findById(id)
            .map(m-> missoesRepository.save(dto.toModel(m)));
    }
    public boolean deleteItem(Long id){
        if(missoesRepository.existsById(id)){
            missoesRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
