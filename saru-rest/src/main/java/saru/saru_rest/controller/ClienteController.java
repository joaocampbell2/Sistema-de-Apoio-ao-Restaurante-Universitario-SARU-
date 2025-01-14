package saru.saru_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import saru.saru_rest.dtos.SaldoDTO;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.service.ClienteService;


@DeclareRoles({"ALUNO, PROFESSOR"})
@RolesAllowed({"ALUNO, PROFESSOR"})
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PutMapping(value="/adicionarSaldo")
    public ResponseEntity<String> adicionarSaldo(@RequestBody SaldoDTO valor){
        return ResponseEntity.ok().body(clienteService.adicionarSaldo((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), valor.getValor()));
    }

    @GetMapping(value= "/resgatarDados")
    public ResponseEntity<String> resgatarDados(){
        ClienteEntity cliente = clienteService.resgatarDados((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok(new String(
                '{' +
                    "cpf:" + '"' + cliente.getCpf() + '"' +
                        "nome:" + '"' + cliente.getNome() + '"' +
                        "saldo:" + '"' + cliente.getSaldo() + '"' +
                        "email:" + '"' + cliente.getEmail() + '"' +
                '}'));
    }
    @GetMapping(value= "/resgatarDados/saldo")
    public ResponseEntity<Float> resgatarDados(){
        ClienteEntity cliente = clienteService.resgatarDados((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return ResponseEntity.ok(cliente.getSaldo());
    }
}
