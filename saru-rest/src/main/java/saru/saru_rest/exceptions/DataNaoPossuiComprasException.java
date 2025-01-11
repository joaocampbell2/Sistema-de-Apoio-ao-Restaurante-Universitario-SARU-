package saru.saru_rest.exceptions;

public class DataNaoPossuiComprasException extends RuntimeException {
    public DataNaoPossuiComprasException() {
        super("A data informada não possui compras");
    }
}
