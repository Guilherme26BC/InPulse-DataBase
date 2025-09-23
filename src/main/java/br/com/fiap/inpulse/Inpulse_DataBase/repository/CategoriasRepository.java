package br.com.fiap.inpulse.Inpulse_DataBase.repository;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Long> {
    
    // Consulta otimizada para carregar categorias com suas ideias
    @Query("SELECT c FROM Categorias c LEFT JOIN FETCH c.ideias")
    List<Categorias> findAllWithDetails();

    Optional<Categorias> findById(Long id);
}