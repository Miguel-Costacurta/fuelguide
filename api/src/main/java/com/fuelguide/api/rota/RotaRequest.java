package com.fuelguide.api.rota;

public class RotaRequest {
    private String cidadeOrigem;
    private String cidadeDestino;
    private String combustivel;
    private double autonomiaKm;
    private double capacidadeLitros;
    private double nivelAtualPct;

    public String getCidadeOrigem() {
        return cidadeOrigem;
    }

    public void setCidadeOrigem(String cidadeOrigem) {
        this.cidadeOrigem = cidadeOrigem;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public double getAutonomiaKm() {
        return autonomiaKm;
    }

    public void setAutonomiaKm(double autonomiaKm) {
        this.autonomiaKm = autonomiaKm;
    }

    public double getCapacidadeLitros() {
        return capacidadeLitros;
    }

    public void setCapacidadeLitros(double capacidadeLitros) {
        this.capacidadeLitros = capacidadeLitros;
    }

    public double getNivelAtualPct() {
        return nivelAtualPct;
    }

    public void setNivelAtualPct(double nivelAtualPct) {
        this.nivelAtualPct = nivelAtualPct;
    }
}
