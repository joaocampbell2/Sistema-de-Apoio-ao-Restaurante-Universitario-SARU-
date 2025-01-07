package saru.saru_rest.exceptions;

public class RefeicaoJaCompradaException extends Exception {
    public RefeicaoJaCompradaException(){
        super("Essa refeição já foi comprada");
    }
}
