package saru.saru_rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import saru.saru_rest.dtos.SaldoDTO;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.service.ClienteService;

@DeclareRoles({"ALUNO", "PROFESSOR"})
@RolesAllowed({"ALUNO", "PROFESSOR"})
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PutMapping(value="/adicionarSaldo")
    public ResponseEntity<String> adicionarSaldo(@RequestBody SaldoDTO valor){
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Recebendo requisição para adicionar saldo de valor {} para o cliente com CPF {}", valor.getValor(), cpf);
        try {
            String response = clienteService.adicionarSaldo(cpf, valor.getValor());
            logger.info("Saldo de {} adicionado com sucesso para o cliente com CPF {}", valor.getValor(), cpf);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            logger.error("Erro ao adicionar saldo de valor {} para o cliente com CPF {}: {}", valor.getValor(), cpf, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao adicionar saldo.");
        }
    }

    @CrossOrigin("localhost:4200")
    @GetMapping(value="/getSaldo")
    public ResponseEntity<Float> getSaldo() throws CpfInexistenteException {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Recebendo requisição para consultar saldo do cliente com CPF {}", cpf);
        try {
            float saldo = clienteService.getSaldo(cpf);
            logger.info("Consulta de saldo realizada com sucesso para o cliente com CPF {}: {}", cpf, saldo);
            return new ResponseEntity<>(saldo, HttpStatus.OK);
        } catch (CpfInexistenteException e) {
            logger.error("Cliente com CPF {} não encontrado.", cpf);
            throw e;
        }
    }
}
