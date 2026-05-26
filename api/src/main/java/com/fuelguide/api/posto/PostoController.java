package com.fuelguide.api.posto;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostoController {
    private final PostoService postoService;

    public PostoController(PostoService postoService){
        this.postoService = postoService;
    }

    @GetMapping("/postos")
    public ResponseEntity<List<PostoEntity>> listarPostos(){
        List<PostoEntity> postoEntity = postoService.listarPostos();

        return ResponseEntity.ok().body(postoEntity);
    }

    @GetMapping("/postos/cidade/{cidade}")
    public ResponseEntity<List<PostoEntity>> buscarPorCidade(@PathVariable String cidade){
        return ResponseEntity.status(200).body(postoService.buscarPorCidade(cidade));
    }

    @PostMapping("/postos")
    public ResponseEntity<PostoEntity> salvarPosto(@RequestBody PostoEntity postoEntity){
        PostoEntity salvo = postoService.salvarPosto(postoEntity);
        return ResponseEntity.status(201).body(salvo);
    }

    @PostMapping("/importar")
    public ResponseEntity<List<PostoEntity>> importarPostos(@RequestBody List<PostoEntity> postos){
        List<PostoEntity> salvos = postoService.importarPostos(postos);

        return ResponseEntity.status(201).body(salvos);
    }

    @GetMapping("/postos/menor-preco/{tipo}")
    public ResponseEntity<List<PostoEntity>> menorPreco(@PathVariable ETipoCombustivel tipo){
        return ResponseEntity.status(200).body(postoService.buscarMaisBaratoPorCombustivel(tipo));
    }
}
