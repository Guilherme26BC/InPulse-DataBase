package br.com.fiap.inpulse.Inpulse_DataBase.repository;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeiasRepository extends JpaRepository<Ideias, Long> {

    // Consulta otimizada para carregar ideias com todos os dados relacionados em
    // uma única consulta
    @Query("SELECT i FROM Ideias i LEFT JOIN FETCH i.funcionario LEFT JOIN FETCH i.programas LEFT JOIN FETCH i.categorias LEFT JOIN FETCH i.contribuicoes")
    List<Ideias> findAllWithDetails();

    // Você também pode otimizar a busca por funcionário
    @Query("SELECT i FROM Ideias i LEFT JOIN FETCH i.funcionario f WHERE f = :funcionarios")
    List<Ideias> findByFuncionario(Funcionarios funcionarios);

}