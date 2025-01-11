package saru.saru_rest.service.refeicao;

import com.google.zxing.WriterException;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.*;

import java.io.IOException;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.exceptions.DataNaoPossuiComprasException;
import saru.saru_rest.exceptions.RefeicaoJaCompradaException;
import saru.saru_rest.exceptions.SaldoInsuficienteException;
import saru.saru_rest.exceptions.*;

import java.sql.Date;

import java.util.List;

public interface RefeicaoService {
     byte[] comprarRefeicao(RefeicaoDTO refeicao, String cpf) throws SaldoInsuficienteException, RefeicaoJaCompradaException, IOException, WriterException;
     String alterarTurno(String cpf, Turno turno) throws SemRefeicoesCompradasException, TodasRefeicoesCompradasException, TurnoJaCompradoException;

    List<RefeicaoEntity> verRefeicoes(RefeicaoDTO dataRefeicao) throws DataNaoPossuiComprasException;

    List<RefeicaoEntity> verRefeicoes(Date dataRefeicao, Turno turno) throws DataNaoPossuiComprasException;

    String alterarTurno(String cpf, Turno turno, Date data) throws SemRefeicoesCompradasException, TodasRefeicoesCompradasException, TurnoJaCompradoException;
}
