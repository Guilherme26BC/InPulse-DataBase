package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Missoes;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.MissoesRepository;
import br.com.fiap.inpulse.Inpulse_DataBase.service.MissoesService;

public class FuncionariosRequestUpdateMissoes {
    private Long missao_id;

    public Funcionarios toModel (Funcionarios funcionarios, MissoesRepository missoesRepository, MissoesService missoesService){
       Missoes missoes =  missoesRepository.findById(this.getMissao_id())
               .orElseThrow(()-> new RuntimeException());

        funcionarios.getMissoes().add(missoes);
        funcionarios.setMoedas(funcionarios.getMoedas().add(missoes.getMoedas()));
        funcionarios.setPontos(funcionarios.getPontos().add(missoes.getPontos()));
        missoesService.atualizarFuncionario(funcionarios.getFuncionario_id(),this.getMissao_id());
        return funcionarios;
    }
    public Long getMissao_id() {
        return missao_id;
    }

    public void setMissao_id(Long missao_id) {
        this.missao_id = missao_id;
    }
}
