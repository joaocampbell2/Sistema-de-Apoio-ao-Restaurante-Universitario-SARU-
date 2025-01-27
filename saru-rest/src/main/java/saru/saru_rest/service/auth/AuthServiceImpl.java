package saru.saru_rest.service.auth;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.CadastroClienteDTO;
import saru.saru_rest.dtos.CadastroFuncionarioDTO;
import saru.saru_rest.dtos.LoginDTO;
import saru.saru_rest.dtos.TokenDTO;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.entity.FuncionarioEntity;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.ImpossivelCadastrarException;
import saru.saru_rest.exceptions.SenhaIncorretaException;
import saru.saru_rest.exceptions.UsuarioJaCadastradoException;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.repository.FuncionarioRepository;
import saru.saru_rest.security.JwtService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final FuncionarioRepository funcionarioRepository;
    private final ClienteRepository clienteRepository;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(ClienteRepository clienteRepository, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder, FuncionarioRepository funcionarioRepository) {
        this.clienteRepository = clienteRepository;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public String pegarCPF(String token) {
        logger.info("Pegando CPF a partir do token.");
        return jwtService.pegarCpfDoToken(token);
    }

    @Cacheable("usuarios")
    public TokenDTO fazerLogin(LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException {
        logger.info("Iniciando tentativa de login para o CPF: {}", login.getCpf());

        try {
            Optional<ClienteEntity> entidade = clienteRepository.findById(login.getCpf());
            if (entidade.isEmpty()) {
                logger.warn("CPF não encontrado no repositório de clientes: {}", login.getCpf());
                throw new CpfInexistenteException(login.getCpf());
            }

            ClienteEntity cliente = entidade.get();
            if (bCryptPasswordEncoder.matches(login.getSenha(), cliente.getSenha())) {
                logger.info("Login bem-sucedido para cliente com CPF: {}", login.getCpf());
                return new TokenDTO(jwtService.gerarTokenAluno(login.getCpf()));
            }

            logger.warn("Senha incorreta para cliente com CPF: {}", login.getCpf());
            throw new SenhaIncorretaException();

        } catch (CpfInexistenteException e) {
            logger.info("Tentando login como funcionário para o CPF: {}", login.getCpf());

            Optional<FuncionarioEntity> entidade = funcionarioRepository.findById(login.getCpf());
            if (entidade.isEmpty()) {
                logger.warn("CPF não encontrado no repositório de funcionários: {}", login.getCpf());
                throw new CpfInexistenteException(login.getCpf());
            }

            FuncionarioEntity funcionario = entidade.get();
            if (bCryptPasswordEncoder.matches(login.getSenha(), funcionario.getSenha())) {
                logger.info("Login bem-sucedido para funcionário com CPF: {}", login.getCpf());
                return new TokenDTO(jwtService.gerarTokenFuncionario(login.getCpf()));
            }

            logger.warn("Senha incorreta para funcionário com CPF: {}", login.getCpf());
            throw new SenhaIncorretaException();
        } catch (Exception ex) {
            logger.error("Erro durante o login para CPF: {}", login.getCpf(), ex);
            throw ex;
        }
    }

    private String hashSenha(String senha) {
        logger.debug("Gerando hash para a senha.");
        return bCryptPasswordEncoder.encode(senha);
    }

    public TokenDTO fazerCadastro(CadastroClienteDTO cadastro) throws ImpossivelCadastrarException, UsuarioJaCadastradoException {
        logger.info("Iniciando cadastro de cliente com CPF: {}", cadastro.getCpf());

        if (cadastro.getCpf().length() != 11) {
            logger.error("CPF inválido para cadastro de cliente: {}", cadastro.getCpf());
            throw new ImpossivelCadastrarException();
        }

        Optional<ClienteEntity> entidade = clienteRepository.findById(cadastro.getCpf());
        if (entidade.isPresent()) {
            logger.warn("Cliente já cadastrado com o CPF: {}", cadastro.getCpf());
            throw new UsuarioJaCadastradoException(cadastro.getCpf());
        }

        ClienteEntity novoCliente = new ClienteEntity(cadastro);
        novoCliente.setSenha(hashSenha(novoCliente.getSenha()));
        clienteRepository.save(novoCliente);
        logger.info("Cadastro de cliente concluído com sucesso. CPF: {}", cadastro.getCpf());
        return new TokenDTO(jwtService.gerarTokenAluno(novoCliente.getCpf()));
    }

    public TokenDTO fazerCadastro(CadastroFuncionarioDTO cadastro) throws ImpossivelCadastrarException, UsuarioJaCadastradoException {
        logger.info("Iniciando cadastro de funcionário com CPF: {}", cadastro.getCpf());

        if (cadastro.getCpf().length() != 11) {
            logger.error("CPF inválido para cadastro de funcionário: {}", cadastro.getCpf());
            throw new ImpossivelCadastrarException();
        }

        Optional<FuncionarioEntity> entidade = funcionarioRepository.findById(cadastro.getCpf());
        if (entidade.isPresent()) {
            logger.warn("Funcionário já cadastrado com o CPF: {}", cadastro.getCpf());
            throw new UsuarioJaCadastradoException(cadastro.getCpf());
        }

        FuncionarioEntity novoFuncionario = new FuncionarioEntity(cadastro);
        novoFuncionario.setSenha(hashSenha(novoFuncionario.getSenha()));
        funcionarioRepository.save(novoFuncionario);
        logger.info("Cadastro de funcionário concluído com sucesso. CPF: {}", cadastro.getCpf());
        return new TokenDTO(jwtService.gerarTokenAluno(novoFuncionario.getCpf()));
    }
}
