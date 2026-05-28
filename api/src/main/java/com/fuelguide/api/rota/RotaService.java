package com.fuelguide.api.rota;

import com.fuelguide.api.posto.IPostoRepository;
import com.fuelguide.api.posto.PostoEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Math.*;

@Service
public class RotaService {
    private final ORSService orsService;
    private final IPostoRepository iPostoRepository;

    public RotaService(ORSService orsService, IPostoRepository iPostoRepository){
        this.orsService = orsService;
        this.iPostoRepository = iPostoRepository;
    }
    public RotaResponse calcularRota (RotaRequest rotaRequest) throws Exception {
        String linestring = orsService.obterLinestringDaRota(rotaRequest.getCidadeOrigem(), rotaRequest.getCidadeDestino());
        List<PostoEntity> postos = iPostoRepository.buscarPostosPorRota(linestring, 5000.0);

        System.out.println(postos.size());
        return new RotaResponse();
    }
    private double calcularDistanciaKm(double latO, double lonO, double latD, double lonD ){
        double r = 6371.0;
        double dLat = Math.toRadians(latD - latO);
        double dLon = Math.toRadians(lonD - lonO);
        double a = Math.pow(sin(dLat/2),2) + Math.cos(Math.toRadians(latO)) * Math.cos(Math.toRadians(latD)) * Math.pow(sin(dLon/2),2);
        double c = 2 * Math.atan2(sqrt(a), Math.sqrt(1-a));

        return r * c;
    }
}
