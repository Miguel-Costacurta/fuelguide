package com.fuelguide.api.rota;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rota")
public class RotaController {
    private final RotaService rotaService;
    private ORSService orsService;

    public RotaController(RotaService rotaService, ORSService orsService){
        this.rotaService = rotaService;
        this.orsService = orsService;
    }

    @PostMapping("/calcular")
    public ResponseEntity<RotaResponse> calcularRota(@RequestBody RotaRequest rotaRequest) throws Exception {
        return ResponseEntity.ok(rotaService.calcularRota(rotaRequest));
    }

    @GetMapping("/pontos-rota")
    public ResponseEntity<List<CoordenadaRota>> testeGeocode2(@RequestParam String origem, @RequestParam String destino) throws Exception {
        List<CoordenadaRota> coords = orsService.pontosDaRota(origem, destino);
        List<CoordenadaRota> aMostrar = orsService.pontosParaOMapa(coords);
        return ResponseEntity.ok(aMostrar);
    }
}
