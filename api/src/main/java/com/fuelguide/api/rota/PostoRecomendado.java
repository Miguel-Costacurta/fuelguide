package com.fuelguide.api.rota;

public class PostoRecomendado {
    private String nome;
    private String cidade;
    private String estado;
    private String combustivel;
    private double preco;
    private double kmDaOrigem;
    private double custoEstimado;
    private double nivelAoChegar;
    private double score;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getKmDaOrigem() {
        return kmDaOrigem;
    }

    public void setKmDaOrigem(double kmDaOrigem) {
        this.kmDaOrigem = kmDaOrigem;
    }

    public double getCustoEstimado() {
        return custoEstimado;
    }

    public void setCustoEstimado(double custoEstimado) {
        this.custoEstimado = custoEstimado;
    }

    public double getNivelAoChegar() {
        return nivelAoChegar;
    }

    public void setNivelAoChegar(double nivelAoChegar) {
        this.nivelAoChegar = nivelAoChegar;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
