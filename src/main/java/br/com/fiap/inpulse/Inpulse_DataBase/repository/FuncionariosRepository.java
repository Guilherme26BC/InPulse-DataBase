package br.com.fiap.inpulse.Inpulse_DataBase.repository;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Funcionarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuncionariosRepository extends JpaRepository<Funcionarios, Long> {
    @Query("SELECT f FROM Funcionarios f LEFT JOIN FETCH f.ideias")
    List<Funcionarios> findAllWithIdeias();

    Optional<Funcionarios> findByEmail(String email);

    @Query("SELECT f FROM Funcionarios f LEFT JOIN FETCH f.ideias LEFT JOIN FETCH f.programas LEFT JOIN FETCH f.itens LEFT JOIN FETCH f.selos")
    List<Funcionarios> findAllWithDetails();
}
