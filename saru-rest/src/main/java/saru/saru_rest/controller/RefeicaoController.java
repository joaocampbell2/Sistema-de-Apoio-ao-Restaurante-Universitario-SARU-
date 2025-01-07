package saru.saru_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.exceptions.RefeicaoJaCompradaException;
import saru.saru_rest.exceptions.SaldoInsuficienteException;
import saru.saru_rest.service.refeicao.RefeicaoService;


@RestController
@RequestMapping(value = "/refeicao")
public class RefeicaoController {
    @Autowired
    private RefeicaoService refeicaoService;


    @PostMapping(value = "/comprarRefeicao")
    public ResponseEntity<String> comprarRefeicao(@RequestBody RefeicaoDTO refeicao) throws SaldoInsuficienteException, RefeicaoJaCompradaException {
        String userCpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        refeicaoService.comprarRefeicao(refeicao, userCpf);
        return ResponseEntity.ok("Refeição comprada");
    }
}
