package com.fuelguide.api.posto.importacao;

import com.fuelguide.api.posto.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class ANPImportService {
    private final IPostoRepository iPostoRepository;

    public ANPImportService(IPostoRepository iPostoRepository){
        this.iPostoRepository = iPostoRepository;
    }

    public void importarCSV(){

        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("anp/ultimas-4-semanas-gasolina-etanol.csv");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.ISO_8859_1)
            );

            Map<String, PostoEntity> mapaPostos = new HashMap<>();

            String line;
            boolean firstLine = true;

            while((line = reader.readLine()) != null){

                if(firstLine){
                    firstLine = false;
                    continue;
                }

                String[] cols = line.split(";");
                if(cols.length < 13) continue;

                ETipoCombustivel tipo = mapearCombustivel(cols[10].trim());
                if(tipo == null) continue;

                String precoStr = cols[12].trim().replace(",",".");
                if(precoStr.isEmpty()) continue;
                Double preco = Double.parseDouble(precoStr);

                String cnpj = cols[4].trim();
                String nome = cols[3].trim();
                String endereco = cols[5].trim();
                String municipio = cols[2].trim();
                String estado = cols[1].trim();

                PostoEntity posto = mapaPostos.computeIfAbsent(cnpj, c -> iPostoRepository.findByCnpj(c)
                                .orElseGet(() ->{
                    PostoEntity novo = new PostoEntity();
                    novo.setCnpj(c);
                    novo.setNome(nome);
                    novo.setEndereco(endereco);
                    novo.setMunicipio(municipio);
                    novo.setEstado(estado);
                    return novo;
                })
                );

                boolean jaExiste = posto.getPrecos().stream().anyMatch(p -> p.getTipo() == tipo);

                if(!jaExiste){
                    PrecoCombustivel precoCombustivel = new PrecoCombustivel();
                    precoCombustivel.setTipo(tipo);
                    precoCombustivel.setValor(preco);
                    precoCombustivel.setPosto(posto);
                    posto.getPrecos().add(precoCombustivel);
                }
            }
            iPostoRepository.saveAll(mapaPostos.values());
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
