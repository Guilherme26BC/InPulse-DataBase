package br.com.fiap.inpulse.Inpulse_DataBase.repository;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepository extends JpaRepository<Logs, Long> {
}
