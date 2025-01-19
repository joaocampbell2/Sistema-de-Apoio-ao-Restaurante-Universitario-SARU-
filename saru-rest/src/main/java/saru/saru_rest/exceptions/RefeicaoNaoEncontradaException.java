package saru.saru_rest.exceptions;

public class RefeicaoNaoEncontradaException extends RuntimeException {
  public RefeicaoNaoEncontradaException() {
    super("Refeicao não encontrada");
  }
}
