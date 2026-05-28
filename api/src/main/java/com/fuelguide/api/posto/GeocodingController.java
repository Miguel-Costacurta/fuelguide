package com.fuelguide.api.posto;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geocoding")
public class GeocodingController {
    private final GeocodingService geocodingService;

    public GeocodingController(GeocodingService geocodingService){
        this.geocodingService = geocodingService;
    }

    @PostMapping("/geocodificar-postos")
    public ResponseEntity<String> geocodificarPostos() throws Exception{
        geocodingService.buscarCidadesUnicas();
        return ResponseEntity.ok("Geocodificação iniciada");
    }
}
