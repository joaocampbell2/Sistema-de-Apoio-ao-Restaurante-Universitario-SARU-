package saru.saru_rest.exceptions;

public class SenhaIncorretaException extends Exception {

    public SenhaIncorretaException() {
        super("Senha incorreta!");
    }
}
