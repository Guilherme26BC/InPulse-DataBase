package br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.responses;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.contribuicoes.responses.ContribuicoesResponseIdeias;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.responses.FuncionariosResponseIdeias;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.programas.responses.ProgramasResponseIF;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class IdeiasResponse {
    private Long ideia_id;
    private String nome;
    private String problema;
    private String descricao;
    private String imagem;
    private LocalDate data;
    private BigInteger curtidas;
    private String status; // Novo campo
    private FuncionariosResponseIdeias funcionario_nome;
    private List<ProgramasResponseIF> programas_nome;
    private List<String> categoriasIcone;
    private List<ContribuicoesResponseIdeias> contribuicoes;

    public IdeiasResponse toDto(Ideias ideias) {
        this.setIdeia_id(ideias.getIdeia_id());
        this.setNome(ideias.getNome());
        this.setProblema(ideias.getProblema());
        this.setDescricao(ideias.getDescricao());
        this.setImagem(ideias.getImagem());
        this.setData(ideias.getData());
        this.setCurtidas(ideias.getCurtidas());
        this.setStatus(ideias.getStatus() != null ? ideias.getStatus() : "nova");

        this.setFuncionario_nome(new FuncionariosResponseIdeias().toDto(ideias.getFuncionario()));

        List<ProgramasResponseIF> nomesProgramas = ideias.getProgramas().stream()
                .map(p -> new ProgramasResponseIF().toDto(p))
                .collect(Collectors.toList());
        this.setProgramas_nome(nomesProgramas);

        List<String> iconesCategorias = ideias.getCategorias().stream()
                .map(c -> c.getIcone()).collect(Collectors.toList());
        this.setCategoriasIcone(iconesCategorias);

        List<ContribuicoesResponseIdeias> response = ideias.getContribuicoes().stream()
                .map(c -> new ContribuicoesResponseIdeias().toDto(c)).collect(Collectors.toList());
        this.setContribuicoes(response);
        return this;
    }

    public Long getIdeia_id() {
        return ideia_id;
    }

    public void setIdeia_id(Long ideia_id) {
        this.ideia_id = ideia_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public BigInteger getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(BigInteger curtidas) {
        this.curtidas = curtidas;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FuncionariosResponseIdeias getFuncionario_nome() {
        return funcionario_nome;
    }

    public void setFuncionario_nome(FuncionariosResponseIdeias funcionario_nome) {
        this.funcionario_nome = funcionario_nome;
    }

    public List<ProgramasResponseIF> getProgramas_nome() {
        return programas_nome;
    }

    public void setProgramas_nome(List<ProgramasResponseIF> programas_nome) {
        this.programas_nome = programas_nome;
    }

    public List<String> getCategoriasIcone() {
        return categoriasIcone;
    }

    public void setCategoriasIcone(List<String> categoriasIcone) {
        this.categoriasIcone = categoriasIcone;
    }

    public List<ContribuicoesResponseIdeias> getContribuicoes() {
        return contribuicoes;
    }

    public void setContribuicoes(List<ContribuicoesResponseIdeias> contribuicoes) {
        this.contribuicoes = contribuicoes;
    }
}