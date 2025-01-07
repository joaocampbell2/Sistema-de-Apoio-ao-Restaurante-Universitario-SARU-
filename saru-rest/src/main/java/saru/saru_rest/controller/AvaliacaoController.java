package saru.saru_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.AvaliacaoDTO;
import saru.saru_rest.entity.AvaliacaoEntity;
import saru.saru_rest.exceptions.SemRefeicoesCompradasException;
import saru.saru_rest.security.JwtService;
import saru.saru_rest.service.auth.AvaliacaoService;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController

public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private JwtService jwtService;

    @PostMapping (value = "/avaliacoes")
    public ResponseEntity<String> criarAvaliacao(@RequestHeader (value ="Authorization") String jw,
                                                          @RequestBody AvaliacaoDTO avaliacaoDTO) throws SemRefeicoesCompradasException {

        avaliacaoService.criarAvaliacao(avaliacaoDTO, jwtService.pegarCpfDoToken(jw));
        return ResponseEntity.ok().body("Avaliação enviada com sucesso");
    }
}
