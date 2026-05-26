package com.fuelguide.api.posto;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostoService {
    private final PostoRepository postoRepository;

    public PostoService(PostoRepository postoRepository){
        this.postoRepository = postoRepository;
    }

    public List<PostoModel> listarPostos(){
        return postoRepository.findAll();
    }

    public PostoModel salvarPosto(PostoModel postoModel){
        return postoRepository.save(postoModel);
    }
}
