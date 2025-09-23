package br.com.fiap.inpulse.Inpulse_DataBase.repository;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProgramasRepository extends JpaRepository<Programas, Long> {
    
    // Consulta otimizada para carregar programas com suas listas de funcionários e ideias
    @Query("SELECT p FROM Programas p")
    List<Programas> findAllWithDetails();

    Optional<Programas> findById(Long id);
}
