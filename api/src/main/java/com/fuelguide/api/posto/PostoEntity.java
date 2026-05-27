package com.fuelguide.api.posto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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

    @OneToMany(mappedBy = "posto", cascade = CascadeType.ALL)
    private List<PrecoCombustivel> precos;

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

    public void setMunicipio(String cidade) {
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

    public void setPrecos(List<PrecoCombustivel> precos) {
        this.precos = precos;
    }
}
