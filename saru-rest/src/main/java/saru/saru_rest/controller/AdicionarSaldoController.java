package saru.saru_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.persistence.PostUpdate;
import saru.saru_rest.service.AdicionarSaldoService;
import java.lang.Object;


@DeclareRoles({"ALUNO, PROFESSOR"})
@RolesAllowed({"ALUNO, PROFESSOR"})
public class AdicionarSaldoController {
    private AdicionarSaldoService adicionarSaldoService;

    
    @PutMapping(value="/adicionarSaldo")
    public ResponseEntity<String> adicionarSaldo(RequestBody Jw){
        adicionarSaldoService.adicionarSaldo(null);
        return ResponseEntity.ok().body(null);
    }
}
