package saru.saru_rest.service;

import org.springframework.stereotype.Service;

import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.exceptions.SemRefeicoesCompradasException;
import saru.saru_rest.exceptions.TodasRefeicoesCompradasException;
import saru.saru_rest.exceptions.TurnoJaCompradoException;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.repository.RefeicaoRepository;

import java.util.List;

@Service
public class AlterarTurnoService {
    private ClienteRepository clienteRepository;
    private RefeicaoRepository refeicaoRepository;

    public AlterarTurnoService(ClienteRepository clienteRepository, RefeicaoRepository refeicaoRepository) {
        this.clienteRepository = clienteRepository;
        this.refeicaoRepository = refeicaoRepository;
    }

    public String alterarTurno(String cpf, Turno turno) throws SemRefeicoesCompradasException, TodasRefeicoesCompradasException, TurnoJaCompradoException {
        List<RefeicaoEntity> refeicoes= refeicaoRepository.findByCpf(cpf);

        try{
            if (clienteRepository.existsById(cpf) && verificaTurno(refeicoes, turno) && verificaSemRefeicoesCompradas(refeicoes) && verificaTodasRefeicoesCompradas(refeicoes)) {
                refeicoes.get(0).setTurno(turno);
                return "Turno alterado com sucesso";
            }
        }catch (Exception e){
            System.out.println("Erro ao alterar turno");
        }
        return "Erro ao alterar turno";
    }
    public boolean verificaTurno(List<RefeicaoEntity> refeicoes, Turno turno) throws TurnoJaCompradoException{
        if (refeicoes.get(0).getTurno().equals(turno)){
            throw new TurnoJaCompradoException();
        }
        return true;
    }
    public boolean verificaTodasRefeicoesCompradas(List<RefeicaoEntity> refeicoes) throws TodasRefeicoesCompradasException {
        if (refeicoes.size() >= 2){
            throw new TodasRefeicoesCompradasException();
        }
        return true;
    }
    public boolean verificaSemRefeicoesCompradas(List<RefeicaoEntity> refeicoes) throws SemRefeicoesCompradasException {
        if (refeicoes.isEmpty()) {
            throw new SemRefeicoesCompradasException();
        }
        return true;
    }
}
