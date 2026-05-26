package com.fuelguide.api.posto;

import org.springframework.stereotype.Service;

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

        List<PostoEntity> postos = iPostoRepository.findAll();

        return postos.stream()
                .filter(p -> p.getPrecos().stream()
                        .anyMatch(preco -> preco.getTipo() == tipo))
                .sorted((p1,p2) -> {
                    double v1 = p1.getPrecos().stream()
                            .filter(preco -> preco.getTipo() == tipo)
                            .findFirst().get().getValor();
                    double v2 = p2.getPrecos().stream()
                            .filter(preco -> preco.getTipo() == tipo)
                            .findFirst().get().getValor();
                    return Double.compare(v1,v2);

                })
                .toList();
    }
}
