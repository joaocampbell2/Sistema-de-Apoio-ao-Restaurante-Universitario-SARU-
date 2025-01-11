package saru.saru_rest.service.refeicao;

import com.google.zxing.WriterException;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.*;

import java.io.IOException;

public interface RefeicaoService {
     byte[] comprarRefeicao(RefeicaoDTO refeicao, String cpf) throws SaldoInsuficienteException, RefeicaoJaCompradaException, IOException, WriterException;
     String alterarTurno(String cpf, Turno turno) throws SemRefeicoesCompradasException, TodasRefeicoesCompradasException, TurnoJaCompradoException;
}
