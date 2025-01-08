package saru.saru_rest.exceptions;

public class SemRefeicoesCompradasException extends Exception {
    public SemRefeicoesCompradasException() {
        super("Não possui refeições compradas!");
    }
}
