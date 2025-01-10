package saru.saru_rest.service.refeicao;

import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.*;

import java.sql.Date;

public interface RefeicaoService {
    public void comprarRefeicao(RefeicaoDTO refeicao, String cpf) throws SaldoInsuficienteException, RefeicaoJaCompradaException;

    public String alterarTurno(String cpf, Turno turno, Date data) throws SemRefeicoesCompradasException, TodasRefeicoesCompradasException, TurnoJaCompradoException;
}
