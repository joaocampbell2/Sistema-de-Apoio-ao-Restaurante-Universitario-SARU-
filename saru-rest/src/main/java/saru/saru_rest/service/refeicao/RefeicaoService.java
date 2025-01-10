package saru.saru_rest.service.refeicao;

import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.DataNaoPossuiComprasException;
import saru.saru_rest.exceptions.RefeicaoJaCompradaException;
import saru.saru_rest.exceptions.SaldoInsuficienteException;

import java.sql.Date;
import java.util.List;

public interface RefeicaoService {
    public void comprarRefeicao(RefeicaoDTO refeicao, String cpf) throws SaldoInsuficienteException, RefeicaoJaCompradaException;

    public List<RefeicaoEntity> verRefeicoes(RefeicaoDTO dataRefeicao) throws DataNaoPossuiComprasException;

    public List<RefeicaoEntity> verRefeicoes(Date dataRefeicao, Turno turno) throws DataNaoPossuiComprasException;

}
