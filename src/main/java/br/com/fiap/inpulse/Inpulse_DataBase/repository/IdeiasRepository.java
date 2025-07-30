package br.com.fiap.inpulse.Inpulse_DataBase.repository;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IdeiasRepository extends JpaRepository<Ideias, Long> {
    public List<Ideias> findByFuncionario(Funcionarios funcionarios);

}
