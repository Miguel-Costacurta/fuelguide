package com.fuelguide.api.rota;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Service
public class ORSService {
    @Value("${ors.api.key}")
    private String apiKey;

    @Value("${ors.api.url}")
    private String apiUrl;

    public double[] geocodificarCidade(String cidade) throws Exception{

        RestTemplate restTemplate = new RestTemplate();

        String url = apiUrl + "/geocode/search?api_key=" + apiKey +
                "&text="+ cidade +",Brasil&size=1";

        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        System.out.println(response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode coordinates = root
                .path("features")
                .get(0)
                .path("geometry")
                .path("coordinates");

        double lng = coordinates.get(0).asDouble();
        double lat = coordinates.get(1).asDouble();

        System.out.println(lat + " " + lng);
        return new double[]{lng, lat};
    }



    public String obterLinestringDaRota(String origem, String destino) throws Exception{
        double[] vOrigem = geocodificarCidade(origem);
        double[] vDestino = geocodificarCidade(destino);

        String url = "https://api.openrouteservice.org/v2/directions/driving-car?api_key=" + apiKey +"&start="+ vOrigem[0] +"," + vOrigem[1] +
        "&end=" + vDestino[0] + "," + vDestino[1];

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        System.out.println(response.getBody());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode coordinates = root
                .path("features")
                .get(0)
                .path("geometry")
                .path("coordinates");

        StringBuilder sb = new StringBuilder("LINESTRING(");
        for(int i = 0; i < coordinates.size(); i++){
            JsonNode ponto = coordinates.get(i);

            sb.append(ponto.get(0).asDouble())
                    .append(" ")
                    .append(ponto.get(1).asDouble());
            if(i < coordinates.size() - 1) sb.append(", ");
        }
        sb.append(")");

        System.out.println(sb);
        return sb.toString();
    }

    public String reverseGeocode(double lat, double lng) throws Exception{
        String url = "https://api.openrouteservice.org/geocode/reverse?api_key="+ apiKey + "&point.lon=" + lng +
                "&point.lat=" + lat + "&size=1";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println(response);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode jsonResponse = root
                .path("features")
                .get(0)
                .path("properties")
                .path("locality");

        String cidade = jsonResponse.asText();

        return cidade;
    }
}
