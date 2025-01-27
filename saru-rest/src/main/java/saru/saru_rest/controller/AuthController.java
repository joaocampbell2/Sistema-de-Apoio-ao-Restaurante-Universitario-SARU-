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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final FuncionarioRepository funcionarioRepository;
    private AuthService authService;

    public AuthController(AuthService authService, FuncionarioRepository funcionarioRepository) {
        this.authService = authService;
        this.funcionarioRepository = funcionarioRepository;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException {
        logger.info("Tentativa de login iniciada para o CPF: {}", login.getCpf());
        try {
            TokenDTO token = authService.fazerLogin(login);
            logger.info("Login bem-sucedido para o CPF: {}", login.getCpf());
            return ResponseEntity.ok(token);
        } catch (SenhaIncorretaException | CpfInexistenteException e) {
            logger.error("Erro ao realizar login para o CPF: {}. Motivo: {}", login.getCpf(), e.getMessage());
            throw e;
        }
    }

    @PostMapping(value = "/cadastrarCliente")
    public ResponseEntity<TokenDTO> cadastrarCliente(@RequestBody CadastroClienteDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException {
        logger.info("Tentativa de cadastro de cliente com CPF: {}", cadastro.getCpf());
        try {
            TokenDTO token = authService.fazerCadastro(cadastro);
            logger.info("Cadastro de cliente bem-sucedido para o CPF: {}", cadastro.getCpf());
            return ResponseEntity.ok().body(token);
        } catch (UsuarioJaCadastradoException | ImpossivelCadastrarException e) {
            logger.error("Erro ao cadastrar cliente com CPF: {}. Motivo: {}", cadastro.getCpf(), e.getMessage());
            throw e;
        }
    }

    @PostMapping(value = "/cadastrarFuncionario")
    public ResponseEntity<TokenDTO> cadastrarFuncionario(@RequestBody CadastroFuncionarioDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException {
        logger.info("Tentativa de cadastro de funcionário com CPF: {}", cadastro.getCpf());
        try {
            TokenDTO token = authService.fazerCadastro(cadastro);
            logger.info("Cadastro de funcionário bem-sucedido para o CPF: {}", cadastro.getCpf());
            return ResponseEntity.ok().body(token);
        } catch (UsuarioJaCadastradoException | ImpossivelCadastrarException e) {
            logger.error("Erro ao cadastrar funcionário com CPF: {}. Motivo: {}", cadastro.getCpf(), e.getMessage());
            throw e;
        }
    }

    @GetMapping(value="/verificaEhFuncionario")
    public ResponseEntity<Boolean> verificaSeEhFuncionario() {
        String userCpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Verificação de acesso iniciada para o CPF: {}", userCpf);
        Optional<FuncionarioEntity> entidade = funcionarioRepository.findById(userCpf);
        if (entidade.isPresent()) {
            logger.info("Verificação bem-sucedida. CPF: {} é funcionário.", userCpf);
            return ResponseEntity.ok(true);
        }
        logger.warn("Verificação falhou. CPF: {} não encontrado como funcionário.", userCpf);
        return ResponseEntity.ok(false);
    }

}
