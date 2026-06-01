package com.fuelguide.api.posto.importacao;

import com.fuelguide.api.posto.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                String cidade = cols[2].trim();
                String estado = cols[1].trim();
                String cep = cols[9].trim();
                String numero = cols[6].trim();
                String bairro = cols[8].trim();

                PostoEntity posto = mapaPostos.computeIfAbsent(cnpj, c -> iPostoRepository.findByCnpj(c)
                                .orElseGet(() ->{
                    PostoEntity novo = new PostoEntity();
                    novo.setCnpj(c);
                    novo.setNome(nome);
                    novo.setEndereco(endereco);
                    novo.setCidade(cidade);
                    novo.setEstado(estado);
                    novo.setCep(cep);
                    novo.setNumero(numero);
                    novo.setBairro(bairro);
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
    public String importarCoordenadas() {
        int atualizados = 0;
        int naoEncontrados = 0;
        int erros = 0;

        try {
            InputStream is = getClass()
                    .getClassLoader()
                    .getResourceAsStream("anp/banco_de_postos.csv");

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, StandardCharsets.ISO_8859_1));

            String line;
            boolean firstLine = true;
            List<PostoEntity> paraAtualizar = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (firstLine) { firstLine = false; continue; }

                line = line.trim();
                if (line.isBlank()) continue;

                String[] cols = line.split(",");
                if (cols.length < 13) continue;

                try {
                    System.out.println(cols[4]);
                    String cnpj = cols[1].trim();
                    double lat  = Double.parseDouble(cols[5].trim());
                    double lon  = Double.parseDouble(cols[6].trim());

                    Optional<PostoEntity> postoOpt = iPostoRepository.findByCnpj(cnpj);

                    if (postoOpt.isEmpty()) {
                        naoEncontrados++;
                        continue;
                    }

                    PostoEntity posto = postoOpt.get();
                    posto.setLat(lat);
                    posto.setLon(lon);
                    posto.setFonteCoordenada("MANUAL");
                    paraAtualizar.add(posto);
                    atualizados++;

                } catch (NumberFormatException e) {
                    erros++;
                }
            }

            iPostoRepository.saveAll(paraAtualizar);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar coordenadas: " + e.getMessage(), e);
        }

        return String.format(
                "Atualizados: %d | Não encontrados no banco: %d | Erros de parsing: %d",
                atualizados, naoEncontrados, erros
        );
    }
}
