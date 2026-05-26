package com.fuelguide.api.posto.importacao;

import com.fuelguide.api.posto.PostoModel;
import com.fuelguide.api.posto.PostoRepository;
import org.hibernate.sql.ast.tree.expression.Star;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
public class ANPImportService {
    private final PostoRepository postoRepository;

    public ANPImportService(PostoRepository postoRepository){
        this.postoRepository = postoRepository;
    }

    public void importarCSV(){

        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("anp/revendas_lpc_2026-05-17_2026-05-23.xlsx");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.UTF_8)
            );

            String line;
            boolean firstLine = true;

            while((line = reader.readLine()) != null){

                if(firstLine){
                    firstLine = false;
                    continue;
                }

                String[] cols = line.split(";");

                PostoModel posto = new PostoModel();

                posto.setNome(cols[3]);
                posto.setCnpj(cols[1]);
                posto.setEndereco(cols[4]);
                posto.setMunicipio(cols[9]);
                posto.setEstado(cols[10]);

                postoRepository.save(posto);
            }
        } catch (Exception e){
            throw new RuntimeException("Erro ao importar CSV ANP", e);
        }

    }
}
