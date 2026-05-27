package com.fuelguide.api.posto.importacao;

import com.fuelguide.api.posto.ETipoCombustivel;
import com.fuelguide.api.posto.PostoEntity;
import com.fuelguide.api.posto.IPostoRepository;
import com.fuelguide.api.posto.PrecoCombustivel;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class ANPImportService {
    private final IPostoRepository IPostoRepository;

    public ANPImportService(IPostoRepository IPostoRepository){
        this.IPostoRepository = IPostoRepository;
    }

    public void importarCSV(){

        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("anp/ultimas-4-semanas-gasolina-etanol.csv");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.ISO_8859_1)
            );

            List<PostoEntity> batch = new ArrayList<>();

            String line;
            boolean firstLine = true;

            while((line = reader.readLine()) != null){

                if(firstLine){
                    firstLine = false;
                    continue;
                }

                String[] cols = line.split(";");

                if(cols.length < 13) continue;

                PostoEntity posto = new PostoEntity();

                posto.setNome(cols[3].trim());
                posto.setCnpj(cols[4].trim());
                posto.setEndereco(cols[5].trim());
                posto.setMunicipio(cols[2].trim());
                posto.setEstado(cols[1].trim());

                ETipoCombustivel tipo = mapearCombustivel(cols[10].trim());

                if (tipo == null)continue;

                String precoStr = cols[12].trim().replace(",",".");

                if(precoStr.isEmpty()) continue;

                Double preco = Double.parseDouble(precoStr);

                PrecoCombustivel precoCombustivel = new PrecoCombustivel();
                precoCombustivel.setTipo(tipo);
                precoCombustivel.setValor(preco);
                precoCombustivel.setPosto(posto);

                posto.setPrecos(List.of(precoCombustivel));

                batch.add(posto);

                if(batch.size() == 1000){
                    IPostoRepository.saveAll(batch);
                    batch.clear();
                }
            }
            if (!batch.isEmpty()){
                IPostoRepository.saveAll(batch);
            }
        } catch (Exception e){
            throw new RuntimeException("Erro ao importar CSV ANP", e);
        }

    }
    private ETipoCombustivel mapearCombustivel(String produto){
        return switch (produto.toUpperCase()){
            case "GASOLINA" -> ETipoCombustivel.GASOLINA;
            case "ETANOL" -> ETipoCombustivel.ETANOL;
            case "GASOLINA ADITIVADA" -> ETipoCombustivel.GASOLINA_ADITIVADA;
            case "DIESEL S10" -> ETipoCombustivel.DIESEL_S10;
            case "GNV" -> ETipoCombustivel.GNV;
            default -> null;
        };
    }
}
