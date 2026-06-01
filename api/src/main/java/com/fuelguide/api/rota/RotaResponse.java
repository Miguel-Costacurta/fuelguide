package com.fuelguide.api.rota;

import com.fuelguide.api.posto.PostoModel;

import java.util.List;

public class RotaResponse {
    private double distanciaTotalKm;
    private List<PostoRecomendado> paradas;
    private double custoTotal;
    private double nivelFinalPct;
    private List<String> alertas;
    private List<PostoRecomendado> postosNaRota;


    public double getDistanciaTotalKm() {
        return distanciaTotalKm;
    }

    public void setDistanciaTotalKm(double distanciaTotalKm) {
        this.distanciaTotalKm = distanciaTotalKm;
    }

    public List<PostoRecomendado> getParadas() {
        return paradas;
    }

    public void setParadas(List<PostoRecomendado> paradas) {
        this.paradas = paradas;
    }

    public double getCustoTotal() {
        return custoTotal;
    }

    public void setCustoTotal(double custoTotal) {
        this.custoTotal = custoTotal;
    }

    public double getNivelFinalPct() {
        return nivelFinalPct;
    }

    public void setNivelFinalPct(double nivelFinalPct) {
        this.nivelFinalPct = nivelFinalPct;
    }

    public List<String> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<String> alertas) {
        this.alertas = alertas;
    }

    public List<PostoRecomendado> getPostosNaRota(){
        return postosNaRota;
    }

    public void setPostosNaRota(List<PostoRecomendado> postosNaRota){
        this.postosNaRota = postosNaRota;
    }
}
