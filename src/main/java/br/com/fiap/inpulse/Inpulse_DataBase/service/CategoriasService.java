package br.com.fiap.inpulse.Inpulse_DataBase.service;

import br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias.requests.CategoriasRequestCreate;
import br.com.fiap.inpulse.Inpulse_DataBase.dto.categorias.requests.CategoriasRequestUpdate;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository categoriasRepository;

    public Categorias criarCategoria(CategoriasRequestCreate dto){
        return categoriasRepository.save(dto.toModel());
    }

    public List<Categorias> buscarTodas() {
        return categoriasRepository.findAllWithDetails();
    }
    
    public Optional<Categorias> buscarPorId(Long id) {
        return categoriasRepository.findById(id);
    }

    public boolean deletearCategoria(Long id){
        if (categoriasRepository.existsById(id)){
            categoriasRepository.deleteById(id);
            return true;
        }else
            return false;
    }
    public Optional<Categorias> atualizarCategoria(Long id, CategoriasRequestUpdate dto){
        return categoriasRepository.findById(id).map(c ->
                categoriasRepository.save(dto.toModel(c)));
    }
}
