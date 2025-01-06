package saru.saru_rest.exceptions;

public class UsuarioJaCadastradoException extends Exception {
    public UsuarioJaCadastradoException(String cpf) {
        super("Usuário com o CPF " + cpf + " já foi cadastrado.");
    }
}
