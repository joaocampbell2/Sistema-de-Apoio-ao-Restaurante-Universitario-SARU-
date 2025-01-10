package saru.saru_rest.controller;

import com.google.zxing.WriterException;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.exceptions.RefeicaoJaCompradaException;
import saru.saru_rest.exceptions.SaldoInsuficienteException;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.service.QRCodeService.QRCodeService;
import saru.saru_rest.service.refeicao.RefeicaoService;

import java.io.IOException;
import java.util.List;


@RestController
@DeclareRoles({"FUNCIONARIO, ALUNO, PROFESSOR, VISITANTE"})
@RequestMapping(value = "/refeicao")
public class RefeicaoController {
    @Autowired
    private RefeicaoService refeicaoService;
    @Autowired
    private QRCodeService qrCodeService;

    @Autowired
    private RefeicaoRepository refeicaoRepository;

    @PostMapping(value = "/comprarRefeicao")
    public ResponseEntity<String> comprarRefeicao(@RequestBody RefeicaoDTO refeicao) throws SaldoInsuficienteException, RefeicaoJaCompradaException {
        String userCpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refeicaoService.comprarRefeicao(refeicao, userCpf);
        return ResponseEntity.ok("Refeição comprada");
    }

    @PostMapping(value = "/qrCode")

    public ResponseEntity<byte[]> qrCode(@RequestBody RefeicaoDTO refeicao) throws RefeicaoJaCompradaException, IOException, WriterException {

        System.out.println(refeicao);
        RefeicaoEntity refeicaoEntity = refeicaoRepository.findByCpfClienteAndDataAndTurno((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), refeicao.getDataRefeicao(),refeicao.getTurno()).getFirst();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
                .body(qrCodeService.getQRCodeImage(refeicaoEntity));
    }

    @RolesAllowed("FUNCIONARIO")
    @GetMapping(value="/verRefeicoes/{dataRefeicao}")
    public ResponseEntity<String> verRefeicoes(@RequestParam("dataRefeicao") RefeicaoDTO dataRefeicao){
        List<RefeicaoEntity> refeicao = refeicaoService.verRefeicoes(dataRefeicao);
        if(refeicao.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        String numeroRefeicoes = ("O numero de refeições compradas do dia" + dataRefeicao.getDataRefeicao() + "no turno" + dataRefeicao.getTurno() + "foi de:" + refeicao.size() );
        return ResponseEntity.ok(numeroRefeicoes);
    }

}
