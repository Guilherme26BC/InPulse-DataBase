package br.com.fiap.inpulse.Inpulse_DataBase.repository;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Programas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramasRepository extends JpaRepository<Programas, Long> {
}
