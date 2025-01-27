package saru.saru_rest.service.auth;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saru.saru_rest.dtos.AvaliacaoDTO;
import saru.saru_rest.entity.AvaliacaoEntity;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.exceptions.*;
import saru.saru_rest.repository.AvaliacaoRepository;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.entity.enums.Turno;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

@Service
public class AvaliacaoService {

    private static final Logger logger = LoggerFactory.getLogger(AvaliacaoService.class);

    private AvaliacaoRepository avaliacaoRepository;
    private RefeicaoRepository refeicaoRepository;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, RefeicaoRepository refeicaoRepository) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.refeicaoRepository = refeicaoRepository;
    }

    @Transactional
    public String criarAvaliacao(AvaliacaoDTO avaliacaoDTO) throws SemRefeicoesCompradasException, TurnoNaoCompradoException {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Iniciando processo de criação de avaliação para CPF: {} na data: {} e turno: {}", cpf, avaliacaoDTO.getData(), avaliacaoDTO.getTurno());

        try {
            verificaRefeicaoExisteEUtilizadaEFeedbackJaEnviado(cpf, avaliacaoDTO);
            logger.info("Refeição validada para avaliação do CPF: {}", cpf);
        } catch (Exception e) {
            logger.warn("Erro ao validar refeição para CPF: {}. Mensagem: {}", cpf, e.getMessage());
            throw e;
        }

        AvaliacaoEntity avaliacao = new AvaliacaoEntity(
                avaliacaoDTO.getNota(),
                avaliacaoDTO.getFeedback(),
                this.getIdByCpfClienteAndAndDataAndAndTurno(cpf, avaliacaoDTO.getData(), avaliacaoDTO.getTurno())
        );

        avaliacaoRepository.save(avaliacao);
        logger.info("Avaliação salva com sucesso para CPF: {}", cpf);

        return "Feedback enviado!";
    }

    private boolean verificaRefeicaoExisteEUtilizadaEFeedbackJaEnviado(String cpf, AvaliacaoDTO avaliacaoDTO) throws DataNaoPossuiComprasException {
        logger.info("Validando refeição para CPF: {}, data: {}, turno: {}", cpf, avaliacaoDTO.getData(), avaliacaoDTO.getTurno());

        if (refeicaoRepository.existsByCpfClienteAndDataAndTurno(cpf, avaliacaoDTO.getData(), avaliacaoDTO.getTurno())) {
            List<RefeicaoEntity> refeicao = refeicaoRepository.findByCpfClienteAndDataAndTurno(cpf, avaliacaoDTO.getData(), avaliacaoDTO.getTurno());
            boolean utilizado = refeicao.get(0).isUtilizado();

            if (utilizado) {
                if (avaliacaoRepository.existsByIdRefeicao(refeicao.get(0).getIdRefeicao())) {
                    logger.warn("Refeição já possui avaliação para CPF: {}", cpf);
                    throw new RefeicaoJaPossuiFeedbackException();
                }
                return true;
            }
            logger.warn("Refeição não foi utilizada para CPF: {}", cpf);
            throw new RefeicaoNaoUtilizadaException();
        }
        logger.warn("Data não possui compras registradas para CPF: {}", cpf);
        throw new DataNaoPossuiComprasException();
    }

    private int getIdByCpfClienteAndAndDataAndAndTurno(String cpf, Date data, Turno turno) {
        logger.info("Obtendo ID da refeição para CPF: {}, data: {}, turno: {}", cpf, data, turno);
        List<RefeicaoEntity> refeicao = refeicaoRepository.findByCpfClienteAndDataAndTurno(cpf, data, turno);
        int id = refeicao.get(0).getId();
        logger.info("ID da refeição obtido: {} para CPF: {}", id, cpf);
        return id;
    }
}

