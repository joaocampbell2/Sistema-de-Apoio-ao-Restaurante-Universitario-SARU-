package saru.saru_rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import saru.saru_rest.entity.enums.Turno;

import java.sql.Date;

public class RefeicaoDTO {
    @JsonProperty(value = "dataRefeicao")
    private Date dataRefeicao;
    @JsonProperty(value = "turno")
    private Turno turno;


    public Date getDataRefeicao() {
        return dataRefeicao;
    }

    public Turno getTurno() {
        return turno;
    }

    @Override
    public String toString() {
        return "RefeicaoDTO{" +
                "dataRefeicao=" + dataRefeicao +
                ", turno=" + turno +
                '}';
    }
}
