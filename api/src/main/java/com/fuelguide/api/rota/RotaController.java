package com.fuelguide.api.rota;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/teste-geocode")
    public ResponseEntity<String> testeGeocode() throws Exception {
        double[] coords = orsService.geocodificarCidade("Maringá");
        return ResponseEntity.ok("testando");
    }

    @GetMapping("/teste-geocode2")
    public ResponseEntity<String> testeGeocode2() throws Exception {
        String coords = orsService.obterLinestringDaRota("Maringá", "Curitiba");
        return ResponseEntity.ok("testando");
    }
}
