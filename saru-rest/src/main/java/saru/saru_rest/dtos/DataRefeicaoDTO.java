package saru.saru_rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import saru.saru_rest.entity.enums.Turno;

import java.sql.Date;

public class DataRefeicaoDTO {
    @JsonProperty("data")
    Date dataRefeicao;
    @JsonProperty("turno")
    Turno turno;

    public Date getDataRefeicao() {
        return dataRefeicao;
    }

    public Turno getTurno() {
        return turno;
    }
}
