package saru.saru_rest.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import saru.saru_rest.entity.enums.Turno;


    public class AvaliacaoDTO {

        @JsonProperty(value = "nota")
        private int nota;
        @JsonProperty(value = "feedback")
        private String feedback;
        @JsonProperty(value = "turno")
        private Turno turno;


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

    }


