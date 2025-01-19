package saru.saru_rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AlterarTurnoDTO {
    @JsonProperty(value= "idRefeicao")
    private int idRefeicao;

    public int getIdRefeicao() {
        return idRefeicao;
    }
}
