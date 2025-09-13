package br.com.fiap.inpulse.Inpulse_DataBase.repository;


import br.com.fiap.inpulse.Inpulse_DataBase.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ItemRepository extends JpaRepository<Item, Long> {
}
