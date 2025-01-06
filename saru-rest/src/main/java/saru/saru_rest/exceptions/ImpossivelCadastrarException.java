package saru.saru_rest.exceptions;

public class ImpossivelCadastrarException extends Exception {
    public ImpossivelCadastrarException() {
        super("Não foi possível realizar o cadastro");
    }

}
