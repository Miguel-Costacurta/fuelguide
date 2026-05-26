package com.fuelguide.api.posto;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PostoController {
    private final PostoService postoService;

    public PostoController(PostoService postoService){
        this.postoService = postoService;
    }

    @GetMapping("/postos")
    public ResponseEntity<List<PostoModel>> listarPostos(){
        List<PostoModel> postoModel = postoService.listarPostos();

        return ResponseEntity.ok().body(postoModel);
    }

    @PostMapping("/postos")
    public ResponseEntity<PostoModel> salvarPosto(@RequestBody PostoModel postoModel){
        PostoModel salvo = postoService.salvarPosto(postoModel);
        return ResponseEntity.status(201).body(salvo);
    }
}
