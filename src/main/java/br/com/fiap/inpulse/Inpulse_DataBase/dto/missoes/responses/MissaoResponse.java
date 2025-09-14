//package br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.responses;
//
//import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
//import br.com.fiap.inpulse.Inpulse_DataBase.model.Missao;
//import br.com.fiap.inpulse.Inpulse_DataBase.repository.MissaoRepository;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.List;
//
//public class MissaoResponse {
//    private Long id;
//    private String nome;
//    private String descricao;
//    private BigInteger pontos;
//    private BigDecimal moedas;
//    private List<Funcionarios> funcionarios;
//
//    public MissaoResponse toDto(Missao missao){
//        this.setId(missao.getId());
//        this.setNome(missao.getNome());
//        this.setDescricao(missao.getDescricao());
//        this.setPontos(missao.getPontos());
//        this.setMoedas(missao.getMoedas());
//        return this;
//    }
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getDescricao() {
//        return descricao;
//    }
//
//    public void setDescricao(String descricao) {
//        this.descricao = descricao;
//    }
//
//    public BigInteger getPontos() {
//        return pontos;
//    }
//
//    public void setPontos(BigInteger pontos) {
//        this.pontos = pontos;
//    }
//
//    public BigDecimal getMoedas() {
//        return moedas;
//    }
//
//    public void setMoedas(BigDecimal moedas) {
//        this.moedas = moedas;
//    }
//
//    public List<Funcionarios> getFuncionarios() {
//        return funcionarios;
//    }
//
//    public void setFuncionarios(List<Funcionarios> funcionarios) {
//        this.funcionarios = funcionarios;
//    }
//}
