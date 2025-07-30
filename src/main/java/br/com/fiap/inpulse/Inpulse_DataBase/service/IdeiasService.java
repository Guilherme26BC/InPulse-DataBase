package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests.IdeiasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests.IdeiasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.CategoriasRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IdeiasService {

    @Autowired
    private IdeiasRepository ideiasRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;
    @Autowired
    private CategoriasRepository categoriasRepository;
    public Ideias criarIdeia(IdeiasRequestCreate dto){
        return ideiasRepository.save(dto.toModel(funcionariosRepository,categoriasRepository));
    }

    public Optional<Ideias> buscarIdeiaPorId(Long id){
        return ideiasRepository.findById(id);
    }

    public List<Ideias> buscarTodas(){
        return ideiasRepository.findAll();
    }
    public boolean deletarIdeia(Long id){
        if(ideiasRepository.existsById(id)){
            ideiasRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
    public Optional<Ideias> atualizarIdeias(Long id, IdeiasRequestUpdate dto){
        return ideiasRepository.findById(id)
                .map(i -> ideiasRepository.save(dto.toModel(i)));
    }

    public List<Ideias> buscarPorFuncionario(Long id){
        return ideiasRepository.findByFuncionario(funcionariosRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Funcionario inexistente: " + id)));
    }
}
