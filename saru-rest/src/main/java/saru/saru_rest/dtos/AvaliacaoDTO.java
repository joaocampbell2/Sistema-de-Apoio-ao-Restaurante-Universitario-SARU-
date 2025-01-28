package saru.saru_rest.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import saru.saru_rest.entity.enums.Turno;

import java.sql.Date;


public class AvaliacaoDTO {

        @JsonProperty(value = "nota")
        private int nota;
        @JsonProperty(value = "feedback")
        private String feedback;
        @JsonProperty(value = "turno")
        private Turno turno;
        @JsonProperty(value="dataRefeicao")
        private Date data;

    public AvaliacaoDTO(int nota, String feedback, Turno turno, Date data) {
        this.nota = nota;
        this.feedback = feedback;
        this.turno = turno;
        this.data = data;
    }

    // Getters e Setters
        public int getNota() {
            return nota;
        }

        public String getFeedback() {
            return feedback;
        }

        public Turno getTurno() {
            return turno;
        }

    public Date getData() {
        return data;
    }

    @Override
    public String toString() {
        return "AvaliacaoDTO{" +
                "nota=" + nota +
                ", feedback='" + feedback + '\'' +
                ", turno=" + turno +
                ", data=" + data +
                '}';
    }
}


