package saru.saru_rest.service.auth;

import jakarta.transaction.Transactional;
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

    private FuncionarioRepository funcionarioRepository;
    private ClienteRepository clienteRepository;
    private JwtService jwtService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(ClienteRepository clienteRepository, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder, FuncionarioRepository funcionarioRepository) {
        this.clienteRepository = clienteRepository;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public String pegarCPF(String token) {
        return jwtService.pegarCpfDoToken(token);
    }
    @Cacheable("usuarios")
    public TokenDTO fazerLogin(LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException {



        try {
            Optional<ClienteEntity> entidade = clienteRepository.findById(login.getCpf());
            if(entidade.isEmpty()) {
                throw new CpfInexistenteException(login.getCpf());
            }
            ClienteEntity cliente = entidade.get();
            if(bCryptPasswordEncoder.matches(login.getSenha(), cliente.getSenha())){
                return new TokenDTO(jwtService.gerarTokenAluno(login.getCpf()));
            }
            throw new SenhaIncorretaException();
        } catch (Exception e) {
            try {
                Optional<FuncionarioEntity> entidade = funcionarioRepository.findById(login.getCpf());
                if(entidade.isEmpty()) {
                    throw new CpfInexistenteException(login.getCpf());
                }
                FuncionarioEntity funcionario = entidade.get();
                if(login.getSenha().equals(funcionario.getSenha())){
                    return new TokenDTO(jwtService.gerarTokenFuncionario(login.getCpf()));
                }
                throw new SenhaIncorretaException();
            } catch (Exception ex) {
                throw new CpfInexistenteException(login.getCpf());
            }
        }

    }

    private String hashSenha(String senha) {
        return bCryptPasswordEncoder.encode(senha);
    }

    public TokenDTO fazerCadastro(CadastroClienteDTO cadastro) throws ImpossivelCadastrarException, UsuarioJaCadastradoException {
        if(cadastro.getCpf().length() != 11){
            throw new ImpossivelCadastrarException();
        }
        Optional<ClienteEntity> entidade = clienteRepository.findById(cadastro.getCpf());
        if(entidade.isPresent()){
            throw new UsuarioJaCadastradoException(cadastro.getCpf());
        }

        ClienteEntity novoCliente = new ClienteEntity(cadastro);
        novoCliente.setSenha(hashSenha(novoCliente.getSenha()));
        clienteRepository.save(novoCliente);
        return new TokenDTO(jwtService.gerarTokenAluno(novoCliente.getCpf()));

    }


    public TokenDTO fazerCadastro(CadastroFuncionarioDTO cadastro) throws ImpossivelCadastrarException, UsuarioJaCadastradoException {
        if(cadastro.getCpf().length() != 11){
            throw new ImpossivelCadastrarException();
        }
        Optional<FuncionarioEntity> entidade = funcionarioRepository.findById(cadastro.getCpf());
        if(entidade.isPresent()){
            throw new UsuarioJaCadastradoException(cadastro.getCpf());
        }
        FuncionarioEntity novoFuncionario = new FuncionarioEntity(cadastro);
        novoFuncionario.setSenha(hashSenha(novoFuncionario.getSenha()));
        funcionarioRepository.save(novoFuncionario);
        return new TokenDTO(jwtService.gerarTokenAluno(novoFuncionario.getCpf()));
    }


}
