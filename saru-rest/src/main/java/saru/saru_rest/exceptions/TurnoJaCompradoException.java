package saru.saru_rest.exceptions;

public class TurnoJaCompradoException extends Exception {
    public TurnoJaCompradoException() {
        super("Turno já comprado");
    }
}
