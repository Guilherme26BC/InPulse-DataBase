package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.contribuicoes.requests.ContribuicoesRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Contribuicoes;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.ContribuicoesRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.FuncionariosRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.IdeiasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContribuicoesService {
    @Autowired
    private ContribuicoesRepository contribuicoesRepository;
    @Autowired
    private IdeiasRepository ideiasRepository;
    @Autowired
    private FuncionariosRepository funcionariosRepository;

    public Contribuicoes criarContribuicao(ContribuicoesRequestCreate dto){
        return contribuicoesRepository.save(dto.toModel(funcionariosRepository, ideiasRepository));
    }
}
