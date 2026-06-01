package com.fuelguide.api.posto;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class GeocodingService {
    private final IPostoRepository iPostoRepository;

    public GeocodingService(IPostoRepository iPostoRepository){
        this.iPostoRepository = iPostoRepository;
    }

    public void buscarCidadesUnicas() throws Exception{
        List<Object[]> cidadesUnicas = iPostoRepository.buscarCidadesUnicas();

        for (Object[] linha : cidadesUnicas){
            String cidade = (String) linha[0];
            String estado = (String) linha[1];

            String url = "https://nominatim.openstreetmap.org/search?city="+ cidade + "&state=" + estado +
                    "&country=Brazil&format=json&limit=1";

            HttpHeaders headers = new HttpHeaders();
            headers.set("User-Agent", "FuelGuide/1.0");
            HttpEntity<String> entity = new HttpEntity<>(headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.getBody());
            JsonNode coordinates = root.get(0);

            double lan = coordinates.path("lat").asDouble();
            double lon = coordinates.path("lon").asDouble();

            iPostoRepository.atualizarCoordenadas(cidade, estado, lan, lon);

            Thread.sleep(1000);

            System.out.println(cidade + " - " + estado);
        }
    }
}
