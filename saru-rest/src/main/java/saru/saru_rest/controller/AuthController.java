package saru.saru_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.CadastroClienteDTO;
import saru.saru_rest.dtos.CadastroFuncionarioDTO;
import saru.saru_rest.dtos.LoginDTO;
import saru.saru_rest.dtos.ResponseDTO;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.ImpossivelCadastrarException;
import saru.saru_rest.exceptions.SenhaIncorretaException;
import saru.saru_rest.exceptions.UsuarioJaCadastradoException;
import saru.saru_rest.service.auth.AuthService;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException {
        return ResponseEntity.ok(authService.fazerLogin(login));
    }
    @PostMapping(value = "/cadastrarCliente")
    public ResponseEntity<ResponseDTO> cadastrarCliente(@RequestBody CadastroClienteDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException {
        authService.fazerCadastro(cadastro);
        return ResponseEntity.ok().body(new ResponseDTO("Cliente Cadastrado!"));
    }
    @PostMapping(value = "/cadastrarFuncionario")

    public ResponseEntity<ResponseDTO> cadastrarFuncionario(@RequestBody CadastroFuncionarioDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException {
        authService.fazerCadastro(cadastro);
        return ResponseEntity.ok().body(new ResponseDTO("Funcionario cadastrado"));
    }


}
