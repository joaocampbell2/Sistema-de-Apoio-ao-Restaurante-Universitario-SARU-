package saru.saru_rest.service.refeicao;

import com.google.zxing.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.AlterarTurnoDTO;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.*;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.service.LogService;
import saru.saru_rest.service.qrcode.QRCodeService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@Service

public class RefeicaoServiceImpl implements RefeicaoService {

    private final ClienteRepository clienteRepository;
    private final RefeicaoRepository refeicaoRepository;
    private final QRCodeService qrCodeService;
    private LogService logService;

    private static final Logger logger = LoggerFactory.getLogger(RefeicaoServiceImpl.class);

    public RefeicaoServiceImpl(ClienteRepository clienteRepository, RefeicaoRepository refeicaoRepository, QRCodeService qrCodeService, LogService logService) {
        this.clienteRepository = clienteRepository;
        this.refeicaoRepository = refeicaoRepository;
        this.logService = logService;
        this.qrCodeService = qrCodeService;
    }

    public byte[] comprarRefeicao(RefeicaoDTO refeicao, String cpf) throws SaldoInsuficienteException, RefeicaoJaCompradaException, IOException, WriterException {

        ClienteEntity cliente = clienteRepository.findById(cpf).get();
        float saldo = cliente.getSaldo();

        if(saldo < 2){
            logger.error("Saldo insuficiente para o cliente: {}. Refeição não comprada.", cpf);
            logService.criarLog(cpf,"cliente","Comprar refeicao","error","","Computador");
            throw new SaldoInsuficienteException();
        }

        if (!refeicaoRepository.findByCpfClienteAndDataAndTurno(cpf, refeicao.getDataRefeicao(), refeicao.getTurno()).isEmpty()){
            logger.error("Refeição já comprada para o cliente: {} na data: {} e turno: {}.", cpf, refeicao.getDataRefeicao(), refeicao.getTurno());
            logService.criarLog(cpf,"cliente","Comprar refeicao","error","","Computador");
            throw new RefeicaoJaCompradaException();
        }

        RefeicaoEntity refeicaoEntity = new RefeicaoEntity(cpf, refeicao.getDataRefeicao(), refeicao.getTurno());
        refeicaoRepository.save(refeicaoEntity);
        logger.info("Refeição comprada com sucesso para o cliente: {}. Refeição ID: {}", cpf, refeicaoEntity.getIdRefeicao());
        logService.criarLog(cpf,"cliente","Refeicao comprada:" + refeicao,"sucess","","Computador");
        cliente.setSaldo(cliente.getSaldo() - 2);
        clienteRepository.save(cliente);
        return qrCodeService.getQRCodeImage(refeicaoEntity);
    }

    public String alterarTurno(String cpf, Turno turno){
        List<RefeicaoEntity> refeicoes = refeicaoRepository.findByCpfCliente(cpf);
        try {
            if (clienteRepository.existsById(cpf) && verificaTurno(refeicoes, turno) && verificaSemRefeicoesCompradas(refeicoes) && verificaTodasRefeicoesCompradas(refeicoes)) {
                refeicoes.get(0).setTurno(turno);
                refeicoes.get(0).generateNewToken();
                refeicaoRepository.save(refeicoes.get(0));
                logger.info("Turno alterado com sucesso para o cliente: {}. Novo turno: {}", cpf, turno);
                logService.criarLog(cpf,"cliente","Turno alterado:" + turno,"sucess","","Computador");
                return "Turno alterado com sucesso";
            }
        } catch (Exception e) {
            logger.error("Erro ao alterar turno para o cliente: {}. Erro: {}", cpf, e.getMessage());
            logService.criarLog(cpf,"cliente","Alterar turno","error","","Computador");
        }
        return "Erro ao alterar turno";
    }

    public boolean verificaTurno(List<RefeicaoEntity> refeicoes, Turno turno) throws TurnoJaCompradoException{
        if (refeicoes.getFirst().getTurno().equals(turno)){
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


    public List<RefeicaoEntity> verRefeicoes(RefeicaoDTO dataRefeicao) throws DataNaoPossuiComprasException {
        List<RefeicaoEntity> refeicao = refeicaoRepository.findByDataAndTurno(dataRefeicao.getDataRefeicao(), dataRefeicao.getTurno());
        try {
            if (verificaRefeiçoesDataExistem(refeicao)){
                logger.info("Refeições encontradas para a data: {} e turno: {}.", dataRefeicao.getDataRefeicao(), dataRefeicao.getTurno());
                logService.criarLog((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),"Funcionario","Refeicoes resgatadas com sucesso","sucess","","Computador");
                return refeicao;
            }
        } catch (Exception e) {
            logger.warn("Nenhuma refeição encontrada para a data: {} e turno: {}.", dataRefeicao.getDataRefeicao(), dataRefeicao.getTurno());
            logService.criarLog((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),"Funcionario","Ver refeicoes","error","","Computador");
            return null;
        }
        return null;
    }
    
    public List<RefeicaoEntity> verRefeicoes(Date dataRefeicao, Turno turno) throws DataNaoPossuiComprasException {
        List<RefeicaoEntity> refeicao = refeicaoRepository.findByDataAndTurno(dataRefeicao,turno);
        try {
            if (verificaRefeiçoesDataExistem(refeicao)){
                return refeicao;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    public Boolean verificaRefeiçoesDataExistem(List<RefeicaoEntity> refeicao) throws DataNaoPossuiComprasException, TurnoJaCompradoException, TodasRefeicoesCompradasException, SemRefeicoesCompradasException {
        if (refeicao.isEmpty()) {
            throw new DataNaoPossuiComprasException();
        }
        return true;
    }

    public String alterarTurno(AlterarTurnoDTO alterarTurnoDTO) throws TodasRefeicoesCompradasException {
        RefeicaoEntity refeicao = refeicaoRepository.findByidRefeicao(alterarTurnoDTO.getIdRefeicao());
        Turno novoTurno = null;
        try{
            switch (refeicao.getTurno()){
                case ALMOCO -> novoTurno = Turno.JANTAR;
                case JANTAR -> novoTurno = Turno.ALMOCO;
            }

            if (verificaTodasRefeicoesCompradasDoDia(refeicao, novoTurno)) {
                refeicao.setTurno(novoTurno);
                refeicao.generateNewToken();
                refeicaoRepository.save(refeicao);
                logger.info("Turno alterado com sucesso para o cliente: {}. Novo turno: {}", (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), refeicao.getTurno());
                logService.criarLog((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),"cliente","Turno alterado:" + refeicao.getTurno(),"sucess","","Computador");
                return "Turno alterado com sucesso";
            }
        }catch (Exception e){
            logger.error("Erro ao alterar turno para o cliente: {}. Erro: {}", (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), e.getMessage());
            logService.criarLog((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),"cliente","Alterar turno","error","","Computador");
        }
        return "Erro ao alterar turno";
    }

    private boolean verificaTodasRefeicoesCompradasDoDia(RefeicaoEntity refeicao, Turno novoTurno) throws TodasRefeicoesCompradasException {
        if (refeicaoRepository.existsByDataAndTurno(refeicao.getData(), novoTurno)){
            throw new TodasRefeicoesCompradasException();
        }
        return true;
    }
    private boolean verificaSemRefeicoesCompradas(List<RefeicaoEntity> refeicoes) throws SemRefeicoesCompradasException {
        if (refeicoes.isEmpty()) {
            throw new SemRefeicoesCompradasException();
        }
        return true;
    }

}
