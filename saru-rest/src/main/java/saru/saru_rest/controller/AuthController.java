package saru.saru_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.service.auth.AuthService;

@RestController
public class AuthController {

    private AuthService authService;


    @PostMapping(value = "/login")
    public String login() {
        System.out.println("teste");
        return "teste";
    }

    @PostMapping(value = "/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestParam ClienteEntity cliente) {

    }


}
