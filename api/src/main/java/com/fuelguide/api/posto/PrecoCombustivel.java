package com.fuelguide.api.posto;

import jakarta.persistence.*;
import org.hibernate.dialect.type.PostgreSQLOrdinalEnumJdbcType;

import java.util.List;

@Entity
public class PrecoCombustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ETipoCombustivel tipo;

    @ManyToOne
    @JoinColumn(name = "posto_id")
    private PostoEntity posto;

    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ETipoCombustivel getTipo() {
        return tipo;
    }

    public void setTipo(ETipoCombustivel tipo) {
        this.tipo = tipo;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setPosto(PostoEntity posto) {
        this.posto = posto;
    }
    public PostoEntity getPosto(){
        return posto;
    }
}
