package saru.saru_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.CadastroDTO;
import saru.saru_rest.dtos.ClienteDTO;
import saru.saru_rest.dtos.LoginDTO;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.ImpossivelCadastrarException;
import saru.saru_rest.exceptions.SenhaIncorretaException;
import saru.saru_rest.exceptions.UsuarioJaCadastradoException;
import saru.saru_rest.service.auth.AuthService;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException {
        return ResponseEntity.ok(authService.fazerLogin(login));
    }
    @PostMapping(value = "/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody CadastroDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException {
        return ResponseEntity.ok().body(authService.fazerCadastro(cadastro));
    }
}
