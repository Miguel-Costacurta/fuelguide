package com.fuelguide.api.posto.importacao;

import org.hibernate.query.NativeQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/importarCoordenadas")
    public ResponseEntity<String> importarCoordenadas(){
        String resultado = anpImportService.importarCoordenadas();
        return ResponseEntity.ok("Importação de coordendas feitas com sucesso!" + resultado);
    }
}
