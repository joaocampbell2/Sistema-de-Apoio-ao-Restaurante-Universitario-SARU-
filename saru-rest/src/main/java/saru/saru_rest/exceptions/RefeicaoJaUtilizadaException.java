package saru.saru_rest.exceptions;

public class RefeicaoJaUtilizadaException extends RuntimeException {
  public RefeicaoJaUtilizadaException() {
    super("Reficao ja utilizada");
  }
}
