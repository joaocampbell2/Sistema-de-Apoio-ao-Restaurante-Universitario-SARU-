package saru.saru_rest.exceptions;

public class NaoFoiPossivelAdicionarSaldo extends RuntimeException {
    public NaoFoiPossivelAdicionarSaldo() {
        super("Não foi possível adicionar saldo");
    }
}
