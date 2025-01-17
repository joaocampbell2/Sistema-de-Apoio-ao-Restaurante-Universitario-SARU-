package saru.saru_rest.exceptions;

public class RefeicaoJaPossuiFeedbackException extends RuntimeException {
    public RefeicaoJaPossuiFeedbackException() {
        super("Você ja enviou feedback para essa refeição!");
    }
}
