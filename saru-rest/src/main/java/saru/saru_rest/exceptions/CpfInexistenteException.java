package saru.saru_rest.exceptions;

public class CpfInexistenteException extends Exception {
    public CpfInexistenteException(String cpf) {
        super("CPF: " + cpf + " não encontrado");
    }
}
