package com.fuelguide.api.posto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PostoModel {

    @NotBlank(message = "Nome do posto é obrigatório!")
    private String nome;

    @NotBlank(message = "Cidade é obrigatório!")
    private String cidade;

    @NotNull(message = "Preço da gasolina é obrigatório!")
    @Positive(message = "Preço deve ser maior que zero")
    private Double preçoGasolina;

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

    public Double getPreçoGasolina() {
        return preçoGasolina;
    }

    public void setPreçoGasolina(Double preçoGasolina) {
        this.preçoGasolina = preçoGasolina;
    }

}
