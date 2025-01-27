package saru.saru_rest.controller;

import com.google.zxing.WriterException;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saru.saru_rest.dtos.AlterarTurnoDTO;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.RefeicaoJaCompradaException;
import saru.saru_rest.exceptions.SaldoInsuficienteException;
import saru.saru_rest.exceptions.*;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.service.qrcode.QRCodeService;
import saru.saru_rest.service.refeicao.RefeicaoService;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@DeclareRoles({"FUNCIONARIO, ALUNO, PROFESSOR, VISITANTE"})
@RequestMapping(value = "/refeicao")
public class RefeicaoController {

    private static final Logger logger = LoggerFactory.getLogger(RefeicaoController.class);
    private final RefeicaoService refeicaoService;
    private final QRCodeService qrCodeService;
    private final RefeicaoRepository refeicaoRepository;

    public RefeicaoController(RefeicaoService refeicaoService, QRCodeService qrCodeService, RefeicaoRepository refeicaoRepository) {
        this.refeicaoService = refeicaoService;
        this.qrCodeService = qrCodeService;
        this.refeicaoRepository = refeicaoRepository;
    }

    @PostMapping(value = "/comprarRefeicao", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> comprarRefeicao(@RequestBody RefeicaoDTO refeicao) throws SaldoInsuficienteException, RefeicaoJaCompradaException, IOException, WriterException {
        String userCpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Refeição sendo comprada por: {}", userCpf);
        try {
            return ResponseEntity.ok(refeicaoService.comprarRefeicao(refeicao, userCpf));
        } catch (Exception e) {
            logger.error("Erro ao comprar refeição: {}", e.getMessage());
            throw e;
        }
    }

    @PostMapping(value = "/qrCode")
    public ResponseEntity<byte[]> qrCode(@RequestBody RefeicaoDTO refeicao) throws IOException, WriterException {
        logger.info("Gerando QR Code para refeição, CPF: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        RefeicaoEntity refeicaoEntity = refeicaoRepository.findByCpfClienteAndDataAndTurno((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), refeicao.getDataRefeicao(), refeicao.getTurno()).getFirst();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
                .body(qrCodeService.getQRCodeImage(refeicaoEntity));
    }

    @RolesAllowed("FUNCIONARIO")
    @GetMapping(value = "/verRefeicoes/{dataRefeicao}/{turno}")
    public ResponseEntity<Integer> verRefeicoes(@PathVariable("dataRefeicao") Date dataRefeicao, @PathVariable("turno") Turno turno) {
        logger.info("Visualizando refeições para a data: {} e turno: {}", dataRefeicao, turno);
        List<RefeicaoEntity> refeicao = refeicaoService.verRefeicoes(dataRefeicao, turno);
        if (refeicao.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(refeicao.size());
    }

    @RolesAllowed({"ALUNO", "PROFESSOR"})
    @GetMapping(value = "/minhasRefeicoes")
    public ResponseEntity<List<RefeicaoEntity>> resgatarRefeicoes() {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Resgatando refeições para o CPF: {}", cpf);
        List<RefeicaoEntity> refeicoes = refeicaoRepository.findByCpfCliente(cpf);
        if (refeicoes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(refeicoes);
    }

    @PutMapping(value = "/alterarTurno")
    public ResponseEntity<String> alterarTurno(@RequestBody AlterarTurnoDTO alterarTurnoDTO) throws TodasRefeicoesCompradasException, TurnoJaCompradoException, SemRefeicoesCompradasException {
        logger.info("Alterando turno para refeição com dados: {}", alterarTurnoDTO);
        try {
            return ResponseEntity.ok().body(refeicaoService.alterarTurno(alterarTurnoDTO));
        } catch (Exception e) {
            logger.error("Erro ao alterar turno: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping(value = "/getQrCode/{idRefeicao}")
    public ResponseEntity<byte[]> getQRCode(@PathVariable("idRefeicao") int idRefeicao) {
        logger.info("Obtendo QR Code para refeição com ID: {}", idRefeicao);
        try {
            return ResponseEntity.ok(qrCodeService.getQRCodeImage(refeicaoRepository.findByidRefeicao(idRefeicao)));
        } catch (Exception e) {
            logger.error("Erro ao obter QR Code: {}", e.getMessage());
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping(value = "/validarQrCode/{qrCode}")
    public ResponseEntity<Boolean> validarQrCode(@PathVariable String qrCode) {
        logger.info("Validando QR Code: {}", qrCode);
        if (qrCodeService.validarQrCode(qrCode)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.noContent().build();
    }
}
