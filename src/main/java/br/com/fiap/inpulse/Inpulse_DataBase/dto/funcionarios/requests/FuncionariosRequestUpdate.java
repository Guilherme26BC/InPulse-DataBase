package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.SelosRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FuncionariosRequestUpdate {
    private BigInteger pontos;
    private BigDecimal moedas;
    private boolean modo_anonimo;
    private List<Long> selos_id;

    public Funcionarios toModel(Funcionarios funcionarios, SelosRepository selosRepository){
        BigInteger pontosAux = funcionarios.getPontos();
        BigDecimal moedasAux = funcionarios.getMoedas();
        String tier = funcionarios.getTier();
        Set<Selos> selosList = funcionarios.getSelos();

        // Adiciona novos selos, se houver
        if(!this.getSelos_id().isEmpty()) {
            selosList.addAll(this.getSelos_id().stream().map(s -> {
                Selos selo = selosRepository.findById(s)
                        .orElseThrow(() -> new RuntimeException("Selo não encontrado: " + s));
                return selo;
            }).collect(Collectors.toList()));
        }

        // Atualiza os pontos e moedas com base nos selos
        for(Selos selo : selosList){
            pontosAux = pontosAux.add(selo.getPontos());
            moedasAux = moedasAux.add(selo.getMoedas());
        }

        // Atualiza os pontos, caso a entrada seja diferente de -1
        if(this.getPontos() != null && this.getPontos().compareTo(BigInteger.valueOf(-1)) > 0){
            pontosAux = pontosAux.add(this.getPontos());
        }

        // Atualiza as moedas, caso a entrada seja diferente de -1
        if(this.getMoedas() != null && this.getMoedas().compareTo(new BigDecimal(-1)) > 0){
            moedasAux = moedasAux.add(this.getMoedas());
        }

        // Define os novos valores de pontos e moedas no funcionário
        funcionarios.setPontos(pontosAux);
        funcionarios.setMoedas(moedasAux);

        // Atualiza o tier com base nos pontos
        if(funcionarios.getPontos().compareTo(BigInteger.valueOf(10)) >= 0){
            tier = "Ouro";
        } else if(funcionarios.getPontos().compareTo(BigInteger.valueOf(5)) >= 0){
            tier = "Prata";
        }

        funcionarios.setTier(tier);
        funcionarios.setModo_anonimo(isModo_anonimo());
        funcionarios.setSelos(selosList);

        return funcionarios;
    }

    // Getters e Setters
    public BigInteger getPontos() {
        return pontos;
    }

    public void setPontos(BigInteger pontos) {
        this.pontos = pontos;
    }

    public BigDecimal getMoedas() {
        return moedas;
    }

    public void setMoedas(BigDecimal moedas) {
        this.moedas = moedas;
    }

    public boolean isModo_anonimo() {
        return modo_anonimo;
    }

    public void setModo_anonimo(boolean modo_anonimo) {
        this.modo_anonimo = modo_anonimo;
    }

    public List<Long> getSelos_id() {
        return selos_id;
    }

    public void setSelos_id(List<Long> selos_id) {
        this.selos_id = selos_id;
    }
}
