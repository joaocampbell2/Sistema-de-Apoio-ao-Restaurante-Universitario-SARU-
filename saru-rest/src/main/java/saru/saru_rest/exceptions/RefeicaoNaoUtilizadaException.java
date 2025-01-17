package saru.saru_rest.exceptions;

public class RefeicaoNaoUtilizadaException extends RuntimeException {
    public RefeicaoNaoUtilizadaException() {
        super("Refeição ainda não utilizada");
    }
}
