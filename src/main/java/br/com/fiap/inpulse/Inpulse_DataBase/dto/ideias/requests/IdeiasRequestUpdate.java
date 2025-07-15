package br.com.fiap.inpulse.Inpulse_DataBase.dto.ideias.requests;

import br.com.fiap.inpulse.Inpulse_DataBase.model.Categorias;
import br.com.fiap.inpulse.Inpulse_DataBase.model.Ideias;
import br.com.fiap.inpulse.Inpulse_DataBase.repository.CategoriasRepository;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

public class IdeiasRequestUpdate {
    private BigInteger curtidas;

    public Ideias toModel(Ideias ideias){
        BigInteger curtAtuais = ideias.getCurtidas();
        curtAtuais = ideias.getCurtidas().add(this.getCurtidas());

        ideias.setCurtidas(curtAtuais);

        return ideias;
    }

    public BigInteger getCurtidas() {
        return curtidas;
    }

    public void setCurtidas(BigInteger curtidas) {
        this.curtidas = curtidas;
    }

}
