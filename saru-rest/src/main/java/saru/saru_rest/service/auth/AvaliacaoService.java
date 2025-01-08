package saru.saru_rest.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saru.saru_rest.dtos.AvaliacaoDTO;
import saru.saru_rest.entity.AvaliacaoEntity;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.exceptions.SemRefeicoesCompradasException;
import saru.saru_rest.exceptions.TurnoNaoCompradoException;
import saru.saru_rest.repository.AvaliacaoRepository;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.entity.enums.Turno;


import java.util.List;

@Service
public class AvaliacaoService {


    private AvaliacaoRepository avaliacaoRepository;

    private ClienteRepository clienteRepository;

    private RefeicaoRepository refeicaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, ClienteRepository clienteRepository, RefeicaoRepository refeicaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.clienteRepository = clienteRepository;
        this.refeicaoRepository = refeicaoRepository;
    }

    @Transactional
    public String criarAvaliacao(AvaliacaoDTO avaliacaoDTO, String cpf) throws SemRefeicoesCompradasException, TurnoNaoCompradoException {

        try {
            if (clienteRepository.existsById(cpf)) {
                throw new RuntimeException("Cliente não encontrado");
            }
        } catch (Exception e){

        }

        Turno turno = avaliacaoDTO.getTurno();
        RefeicaoEntity refeicao = verificaRefeicaoJaUtilizada(cpf, turno);

        try {
            if (refeicao == null) {
                throw new TurnoNaoCompradoException();
            }
        } catch (Exception e){}


        AvaliacaoEntity avaliacao = new AvaliacaoEntity(
                avaliacaoDTO.getNota(),
                avaliacaoDTO.getFeedback(),
                refeicao.getId_refeicao());


         avaliacaoRepository.save(avaliacao);

       return "Feedback enviado!";
    }

    private RefeicaoEntity verificaRefeicaoJaUtilizada(String cpf, Turno turno) throws SemRefeicoesCompradasException{
        List<RefeicaoEntity> refeicao = refeicaoRepository.findByCpfCliente(cpf);

        if (refeicao.isEmpty()) {
            throw new SemRefeicoesCompradasException();
        }

        for (RefeicaoEntity refeicaoEntity : refeicao) {
            if (refeicaoEntity.getTurno().equals(turno)) {
                    return refeicaoEntity;
            }
        }

        return null;
    }
}
