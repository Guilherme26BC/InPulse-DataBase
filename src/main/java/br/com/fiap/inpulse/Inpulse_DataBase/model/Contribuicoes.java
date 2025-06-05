package br.com.fiap.inpulse.Inpulse_DataBase.model;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.funcionarios.requests.FuncionariosRequestUpdate;
import jakarta.persistence.*;

@Entity
public class Contribuicoes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comentario;
    @ManyToOne
    @JoinColumn(name ="funcionarios_id")
    private Funcionarios funcionario;
    @ManyToOne
    @JoinColumn(name ="ideia_id")
    private Ideias ideia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Funcionarios getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionarios funcionario) {
        this.funcionario = funcionario;
    }

    public Ideias getIdeia() {
        return ideia;
    }

    public void setIdeia(Ideias ideia) {
        this.ideia = ideia;
    }
}
