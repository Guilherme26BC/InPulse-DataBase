package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests.*;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ItemRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ProgramasRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.SelosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionariosService {

    @Autowired
    private FuncionariosRepository funcionariosRepository;
    @Autowired
    private SelosRepository selosRepository;
    @Autowired
    private ProgramasRepository programasRepository;
    @Autowired
    private ItemRepository itemRepository;

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
    public Optional<Funcionarios> atualizarImagem(Long id, FuncionariosRequestUpdateImagem dto){
        return funcionariosRepository.findById(id).
                map(f -> funcionariosRepository.save(dto.toModel(f)));
    }
    public Optional<Funcionarios> atualizarSenha(Long id, FuncionariosRequestUpdateSenha dto){
        return funcionariosRepository.findById(id)
                .map(f -> funcionariosRepository.save(dto.toModel(f)));
    }
    public Optional<Funcionarios> buscarFuncionarioPorEmail(String email){
        return funcionariosRepository.findByEmail(email);
    }
    public Funcionarios atualizarPrograma(Long idPrograma, Long idFuncionario){
        Programas programa = programasRepository.findById(idPrograma).orElseThrow(() -> new RuntimeException());
        Funcionarios fun = funcionariosRepository.findById(idFuncionario).orElseThrow();
        fun.getProgramas().add(programa);
        return funcionariosRepository.save(fun);
    }
    public Optional<Funcionarios> atualizarItens(Long id, FuncionariosRequestUpdateItem dto){
        return funcionariosRepository.findById(id)
                .map(f -> funcionariosRepository.save(dto.toModel(f,itemRepository)));
    }
    public Optional<Funcionarios> login(FuncionariosRequestLogin dto) {
    Optional<Funcionarios> funcionario = funcionariosRepository.findByEmail(dto.getEmail());
    
    if (funcionario.isPresent() && funcionario.get().getSenha().equals(dto.getSenha())) {
        return funcionario;
    }
    
    return Optional.empty(); // Retorna vazio se o email ou a senha estiverem incorretos
}
}
