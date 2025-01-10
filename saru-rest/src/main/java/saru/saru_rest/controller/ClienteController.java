package saru.saru_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import saru.saru_rest.dtos.SaldoDTO;
import saru.saru_rest.security.JwtService;
import saru.saru_rest.service.ClienteService;


@DeclareRoles({"ALUNO, PROFESSOR"})
@RolesAllowed({"ALUNO, PROFESSOR"})
@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {
    private ClienteService clienteService;
    private JwtService jwtService;

    public ClienteController(ClienteService clienteService, JwtService jwtService) {
        this.clienteService = clienteService;
        this.jwtService = jwtService;
    }

    @PutMapping(value="/adicionarSaldo")
    public ResponseEntity<String> adicionarSaldo(@RequestBody SaldoDTO valor){
        return ResponseEntity.ok().body(clienteService.adicionarSaldo((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(), valor.getValor()));
    }
}
