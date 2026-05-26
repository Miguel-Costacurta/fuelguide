package com.fuelguide.api.posto;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PostoService {
    private final IPostoRepository iPostoRepository;

    public PostoService(IPostoRepository IPostoRepository){
        this.iPostoRepository = IPostoRepository;
    }

    public List<PostoEntity> listarPostos(){
        return iPostoRepository.findAll();
    }

    public PostoEntity salvarPosto(PostoEntity postoEntity){
        return iPostoRepository.save(postoEntity);
    }

    public List<PostoEntity> importarPostos(List<PostoEntity> postos){
        return iPostoRepository.saveAll(postos);
    }

    public List<PostoEntity> buscarPorCidade(String cidade){
        return iPostoRepository.findByCidade(cidade);
    }

    public List<PostoEntity> buscarMaisBaratoPorCombustivel(ETipoCombustivel tipo){
        return iPostoRepository.findAll().stream()
                .filter(p ->p.getPrecos() != null && !p.getPrecos().isEmpty())
                .filter(p -> p.getPrecos().stream().anyMatch(x -> x.getTipo() == tipo))
                .sorted(compararPorPreco(tipo))
                .toList();
    }

    public PostoEntity buscarPostoMaisBarato(String cidade, ETipoCombustivel tipo){
        return iPostoRepository.findByCidade(cidade).stream()
                .filter(p-> p.getPrecos() != null && !p.getPrecos().isEmpty())
                .min(compararPorPreco(tipo))
                .orElse(null);
    }

//-------------- HELPERS -----------------------
    private double getPreco(PostoEntity posto, ETipoCombustivel tipo){
        return posto.getPrecos().stream()
                .filter(p -> p.getTipo() == tipo)
                .map(PrecoCombustivel::getValor)
                .findFirst()
                .orElse(Double.MAX_VALUE);
    }
    private Comparator<PostoEntity> compararPorPreco(ETipoCombustivel tipo){
        return Comparator.comparing(posto -> getPreco(posto, tipo));
    }
}
