package saru.saru_rest.exceptions;

public class TodasRefeicoesCompradasException extends Exception {
    public TodasRefeicoesCompradasException() {
        super("Todas as refeições do dia compradas");
    }
}
