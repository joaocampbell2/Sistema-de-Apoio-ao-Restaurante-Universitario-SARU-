package saru.saru_rest.controller;

import com.google.zxing.WriterException;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.exceptions.*;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.service.qrcode.QRCodeService;
import saru.saru_rest.service.refeicao.RefeicaoService;

import java.io.IOException;

@DeclareRoles({"ALUNO, PROFESSOR"})
@RolesAllowed({"ALUNO, PROFESSOR"})
@RestController
@RequestMapping(value = "/refeicao")
public class RefeicaoController {

    private final RefeicaoService refeicaoService;
    private final QRCodeService qrCodeService;
    private final RefeicaoRepository refeicaoRepository;


    public RefeicaoController(RefeicaoService refeicaoService, QRCodeService qrCodeService, RefeicaoRepository refeicaoRepository) {
        this.refeicaoService = refeicaoService;
        this.qrCodeService = qrCodeService;
        this.refeicaoRepository = refeicaoRepository;
    }

    @PostMapping(value = "/comprarRefeicao")
    public ResponseEntity<byte[]> comprarRefeicao(@RequestBody RefeicaoDTO refeicao) throws SaldoInsuficienteException, RefeicaoJaCompradaException {
        String userCpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(refeicaoService.comprarRefeicao(refeicao, userCpf));
    }

    @PostMapping(value = "/qrCode")

    public ResponseEntity<byte[]> qrCode(@RequestBody RefeicaoDTO refeicao) throws IOException, WriterException {

        RefeicaoEntity refeicaoEntity = refeicaoRepository.findByCpfClienteAndDataAndTurno((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), refeicao.getDataRefeicao(),refeicao.getTurno()).getFirst();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
                .body(qrCodeService.getQRCodeImage(refeicaoEntity));
    }

    @PutMapping(value="/alterarTurno")
    public ResponseEntity<String> alterarTurno(@RequestBody RefeicaoDTO refeicaoDTO) throws TodasRefeicoesCompradasException, TurnoJaCompradoException, SemRefeicoesCompradasException {

        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refeicaoService.alterarTurno(cpf, refeicaoDTO.getTurno());
        return ResponseEntity.ok().body("Turno alterado com sucesso");
    }

}
