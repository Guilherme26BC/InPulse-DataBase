//package br.com.fiap.inpulse.Inpulse_DataBase.dto.missoes.requests;
//
//import br.com.fiap.inpulse.Inpulse_DataBase.model.Missao;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//
//public class missaoRequestCreate {
//    private String nome;
//    private String descricao;
//    private BigInteger pontos;
//    private BigDecimal moedas;
//
//    public Missao toModel(){
//        Missao missao = new Missao();
//        missao.setNome(this.getNome());
//        missao.setDescricao(this.getDescricao());
//        missao.setPontos(this.getPontos());
//        missao.setMoedas(this.getMoedas());
//        return missao;
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
//}
