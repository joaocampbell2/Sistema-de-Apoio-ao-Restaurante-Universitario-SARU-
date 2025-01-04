package saru.saru_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.ClienteDTO;
import saru.saru_rest.service.auth.AuthService;

@RestController
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping(value = "/login")
    public String login(@RequestHeader("Authorization")  String token) {
        token = token.substring(7);
        return authService.pegarCPF(token);
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody ClienteDTO cliente) {
        return ResponseEntity.ok().body(authService.fazerCadastro(cliente));
    }


}
