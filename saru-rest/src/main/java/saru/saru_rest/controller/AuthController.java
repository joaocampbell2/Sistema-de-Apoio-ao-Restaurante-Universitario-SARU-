package saru.saru_rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.CadastroClienteDTO;
import saru.saru_rest.dtos.CadastroFuncionarioDTO;
import saru.saru_rest.dtos.LoginDTO;
import saru.saru_rest.dtos.TokenDTO;
import saru.saru_rest.entity.FuncionarioEntity;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.ImpossivelCadastrarException;
import saru.saru_rest.exceptions.SenhaIncorretaException;
import saru.saru_rest.exceptions.UsuarioJaCadastradoException;
import saru.saru_rest.repository.FuncionarioRepository;
import saru.saru_rest.service.auth.AuthService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final FuncionarioRepository funcionarioRepository;
    private AuthService authService;

    public AuthController(AuthService authService, FuncionarioRepository funcionarioRepository) {
        this.authService = authService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException {
        return ResponseEntity.ok(authService.fazerLogin(login));
    }
    @PostMapping(value = "/cadastrarCliente")
    public ResponseEntity<String> cadastrarCliente(@RequestBody CadastroClienteDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException {
        return ResponseEntity.ok().body(authService.fazerCadastro(cadastro));
    }
    @PostMapping(value = "/cadastrarFuncionario")

    public ResponseEntity<String> cadastrarFuncionario(@RequestBody CadastroFuncionarioDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException {
        return ResponseEntity.ok().body(authService.fazerCadastro(cadastro));
    }

    @GetMapping(value="/verificaEhFuncionario")
    public ResponseEntity<Boolean> verificaSeEhFuncionario(){
        Optional<FuncionarioEntity> entidade = funcionarioRepository.findById((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if (!entidade.isEmpty()){
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

}
