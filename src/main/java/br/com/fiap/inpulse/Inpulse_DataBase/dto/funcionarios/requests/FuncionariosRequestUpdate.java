package br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Selos;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.SelosRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
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

        List<Selos> selosList = this.getSelos_id().stream().map(s -> {
            Selos selosaux = selosRepository.findById(s)
                    .orElseThrow(()-> new RuntimeException("Sello não encontrado" + s));

            pontosAux.add(selosaux.getPontos());
            moedasAux.add(selosaux.getMoedas());
            return selosaux;
        }).collect(Collectors.toList());
        funcionarios.setPontos(pontosAux);
        funcionarios.setMoedas(moedasAux);

        if(funcionarios.getPontos().compareTo(new BigInteger("3000")) >= 0){
           tier = "prata";
        }

        if(funcionarios.getPontos().compareTo(new BigInteger("6000")) >= 0){
            tier = "ouro";
        }

        funcionarios.setTier(tier);
        funcionarios.setSelos(selosList);
        return funcionarios;
    }

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
