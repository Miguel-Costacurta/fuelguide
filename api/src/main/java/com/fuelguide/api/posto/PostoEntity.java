package com.fuelguide.api.posto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tlb_postos")
public class PostoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "posto_seq")
    @SequenceGenerator(
            name = "posto_seq",
            sequenceName = "posto_seq",
            allocationSize = 1000
    )
    private Long id;

    private String cnpj;
    private String nome;
    private String endereco;
    private String cidade;
    private String estado;
    private Double lat;
    private Double lon;
    private String numero;
    private String bairro;
    private String cep;
    private String fonteCoordenada;

    @OneToMany(mappedBy = "posto", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PrecoCombustivel> precos = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public List<PrecoCombustivel> getPrecos() {
        return precos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public String getFonteCoordenada() {
        return fonteCoordenada;
    }

    public void setFonteCoordenada(String fonteCoordenada) {
        this.fonteCoordenada = fonteCoordenada;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setPrecos(List<PrecoCombustivel> precos) {
        this.precos = precos;
    }
}
