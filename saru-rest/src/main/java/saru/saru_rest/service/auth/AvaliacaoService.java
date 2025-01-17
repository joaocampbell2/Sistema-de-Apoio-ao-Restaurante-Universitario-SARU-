package saru.saru_rest.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saru.saru_rest.dtos.AvaliacaoDTO;
import saru.saru_rest.entity.AvaliacaoEntity;
import saru.saru_rest.entity.AvisoEntity;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.exceptions.*;
import saru.saru_rest.repository.AvaliacaoRepository;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.entity.enums.Turno;


import java.sql.Date;
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
    public String criarAvaliacao(AvaliacaoDTO avaliacaoDTO) throws SemRefeicoesCompradasException, TurnoNaoCompradoException {
        Date data = avaliacaoDTO.getData();
        Turno turno = avaliacaoDTO.getTurno();
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
            verificaRefeicaoExisteEUtilizadaEFeedbackJaEnviado(cpf, data, turno);
        }catch (Exception e) {
            throw e;
        }
        AvaliacaoEntity avaliacao = new AvaliacaoEntity(
                avaliacaoDTO.getNota(),
                avaliacaoDTO.getFeedback(),
                this.getIdByCpfClienteAndAndDataAndAndTurno(cpf, data, turno)
        );

         avaliacaoRepository.save(avaliacao);

       return "Feedback enviado!";
    }


    private boolean verificaRefeicaoExisteEUtilizadaEFeedbackJaEnviado(String cpf, Date data, Turno turno) throws DataNaoPossuiComprasException{


        if (refeicaoRepository.existsByCpfClienteAndDataAndTurno(cpf, data, turno)) {
            List<RefeicaoEntity> refeicao = refeicaoRepository.findByCpfClienteAndDataAndTurno(cpf, data, turno);
            boolean utilizado = refeicao.get(0).isUtilizado();
            if (utilizado) {
                if (avaliacaoRepository.existsByIdRefeicao(refeicao.get(0).getIdRefeicao())) {
                    throw new RefeicaoJaPossuiFeedbackException();
                }
                return true;
            }
            throw new RefeicaoNaoUtilizadaException();
        }
        throw new DataNaoPossuiComprasException();
    }

    private int getIdByCpfClienteAndAndDataAndAndTurno(String cpf, Date data, Turno turno){
        List<RefeicaoEntity> refeicao = refeicaoRepository.findByCpfClienteAndDataAndTurno(cpf, data,turno);
        return refeicao.getFirst().getId();
    }

}

