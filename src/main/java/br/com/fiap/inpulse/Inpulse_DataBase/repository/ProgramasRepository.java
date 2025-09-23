package br.com.fiap.inpulse.Inpulse_DataBase.repository;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProgramasRepository extends JpaRepository<Programas, Long> {
    
    // Consulta otimizada para carregar programas com suas listas de funcionários e ideias
    @Query("SELECT p FROM Programas p LEFT JOIN FETCH p.funcionarios LEFT JOIN FETCH p.ideias")
    List<Programas> findAllWithDetails();

    Optional<Programas> findById(Long id);
}
