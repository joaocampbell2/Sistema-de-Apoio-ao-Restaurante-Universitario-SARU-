package saru.saru_rest.exceptions;

public class TurnoNaoCompradoException extends RuntimeException {
    public TurnoNaoCompradoException() {
        super("Não existem refeições no turno informado");
    }
}
