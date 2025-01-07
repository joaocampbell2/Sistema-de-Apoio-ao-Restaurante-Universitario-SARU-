package saru.saru_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.PostUpdate;
import saru.saru_rest.service.AdicionarSaldoService;
import java.lang.Object;
import saru.saru_rest.security.JwtService;


@DeclareRoles({"ALUNO, PROFESSOR"})
@RolesAllowed({"ALUNO, PROFESSOR"})
public class AdicionarSaldoController {
    private AdicionarSaldoService adicionarSaldoService;
    private JwtService jwtService;

    @PutMapping(value="/adicionarSaldo")
    public ResponseEntity<String> adicionarSaldo(@RequestHeader(value="Authorization")String Jw, @RequestBody float valor){
        adicionarSaldoService.adicionarSaldo(jwtService.pegarCpfDoToken(Jw), valor);
        return ResponseEntity.ok().body(null);
    }
}
