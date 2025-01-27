package saru.saru_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.AvaliacaoDTO;
import saru.saru_rest.exceptions.DataNaoPossuiComprasException;
import saru.saru_rest.service.auth.AvaliacaoService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController

public class AvaliacaoController {

     private static final Logger logger = LoggerFactory.getLogger(AvaliacaoController.class);

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping(value = "/publicarAvaliacoes")
    public ResponseEntity<String> criarAvaliacao(@RequestBody AvaliacaoDTO avaliacaoDTO) {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Recebida solicitação de avaliação do cliente CPF: {} com nota: {}", cpf, avaliacaoDTO.getNota());

        try {
            avaliacaoService.criarAvaliacao(avaliacaoDTO);
            logger.info("Avaliação criada com sucesso para CPF: {}", cpf);
            return ResponseEntity.ok().body("Muito obrigado pelo feedback!");
        } catch (DataNaoPossuiComprasException e) {
            logger.warn("Erro ao criar avaliação: Data não possui compras pelo cliente CPF: {}", cpf);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data não possui compras pelo cliente");
        } catch (Exception e) {
            logger.error("Erro inesperado ao criar avaliação para CPF: {}. Mensagem: {}", cpf, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
