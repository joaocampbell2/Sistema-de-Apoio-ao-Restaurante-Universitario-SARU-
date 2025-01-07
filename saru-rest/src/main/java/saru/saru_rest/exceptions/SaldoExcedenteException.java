package saru.saru_rest.exceptions;

public class SaldoExcedenteException extends Exception {
    public SaldoExcedenteException() {
        super("Saldo igual ou próximo a 500");
    }
}
