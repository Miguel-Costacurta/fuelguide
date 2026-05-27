package com.fuelguide.api.posto.importacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/anp")
public class ANPImportController {
    private final ANPImportService anpImportService;

    public ANPImportController(ANPImportService anpImportService){
        this.anpImportService = anpImportService;
    }

    @GetMapping("/importar")
    public ResponseEntity<String> importar(){
        anpImportService.importarCSV();
        return ResponseEntity.ok("Importação Concluída!");
    }
}
