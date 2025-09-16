package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests.IdeiasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests.IdeiasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests.IdeiasRequestUpdateStatus;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.CategoriasRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ProgramasRepository;
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
    @Autowired
    private ProgramasRepository programasRepository;

    public Ideias criarIdeia(IdeiasRequestCreate dto) {
        return ideiasRepository.save(dto.toModel(funcionariosRepository, categoriasRepository));
    }

    public Optional<Ideias> buscarIdeiaPorId(Long id) {
        return ideiasRepository.findById(id);
    }

    public List<Ideias> buscarTodas() {
        return ideiasRepository.findAll();
    }

    public boolean deletarIdeia(Long id) {
        if (ideiasRepository.existsById(id)) {
            ideiasRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public Optional<Ideias> atualizarIdeias(Long id, IdeiasRequestUpdate dto) {
        return ideiasRepository.findById(id)
                .map(i -> ideiasRepository.save(dto.toModel(i)));
    }

    public List<Ideias> buscarPorFuncionario(Long id) {
        return ideiasRepository.findByFuncionario(funcionariosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionario inexistente: " + id)));
    }

    public Ideias atualizarPrograma(Long idPrograma, Long idIdeias) {
        Programas programas = programasRepository.findById(idPrograma).orElseThrow(() -> new RuntimeException());
        Ideias ids = ideiasRepository.findById(idIdeias).orElseThrow();
        ids.getProgramas().add(programas);
        return ideiasRepository.save(ids);
    }

    public Optional<Ideias> atualizarStatusIdeia(Long id, String novoStatus) {
        return ideiasRepository.findById(id)
                .map(ideia -> {
                    ideia.setStatus(novoStatus);
                    return ideiasRepository.save(ideia);
                });
    }

    public Optional<Ideias> atualizarStatus(Long id, IdeiasRequestUpdateStatus dto) {
        return ideiasRepository.findById(id)
                .map(ideia -> ideiasRepository.save(dto.toModel(ideia)));
    }
}
