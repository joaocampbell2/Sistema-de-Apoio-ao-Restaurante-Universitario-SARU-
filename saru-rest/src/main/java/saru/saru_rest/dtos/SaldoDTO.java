package saru.saru_rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaldoDTO {
    @JsonProperty(value = "valor")
    private float valor;

    public float getValor() {
        return valor;
    }
}
