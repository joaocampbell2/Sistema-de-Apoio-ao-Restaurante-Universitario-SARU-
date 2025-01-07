package saru.saru_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.PostUpdate;
import saru.saru_rest.dtos.SaldoDTO;
import saru.saru_rest.service.AdicionarSaldoService;
import java.lang.Object;
import saru.saru_rest.security.JwtService;


@DeclareRoles({"ALUNO, PROFESSOR"})
@RolesAllowed({"ALUNO, PROFESSOR"})
@RestController
public class AdicionarSaldoController {
    private AdicionarSaldoService adicionarSaldoService;
    private JwtService jwtService;

    public AdicionarSaldoController(AdicionarSaldoService adicionarSaldoService, JwtService jwtService) {
        this.adicionarSaldoService = adicionarSaldoService;
        this.jwtService = jwtService;
    }

    @PutMapping(value="/adicionarSaldo")
    public ResponseEntity<String> adicionarSaldo(@RequestHeader(value="Authorization")String Jw, @RequestBody SaldoDTO valor){
        adicionarSaldoService.adicionarSaldo(jwtService.pegarCpfDoToken(Jw), valor.getValor());
        return ResponseEntity.ok().body("Saldo Adicionado com sucesso10");
    }
}
