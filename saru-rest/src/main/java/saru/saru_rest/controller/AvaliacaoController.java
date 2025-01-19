package saru.saru_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.AvaliacaoDTO;
import saru.saru_rest.entity.AvaliacaoEntity;
import saru.saru_rest.exceptions.DataNaoPossuiComprasException;
import saru.saru_rest.exceptions.SemRefeicoesCompradasException;
import saru.saru_rest.security.JwtService;
import saru.saru_rest.service.auth.AvaliacaoService;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController

public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping (value = "/publicarAvaliacoes")
    public ResponseEntity<String> criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) throws SemRefeicoesCompradasException {
        try{
            avaliacaoService.criarAvaliacao(avaliacaoDTO);
            return ResponseEntity.ok().body("Muito obrigado pelo feedback!");
        }
        catch(DataNaoPossuiComprasException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data não possui compras pelo cliente");
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }
}
