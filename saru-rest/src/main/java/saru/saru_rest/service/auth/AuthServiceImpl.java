package saru.saru_rest.service.auth;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.CadastroDTO;
import saru.saru_rest.dtos.LoginDTO;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.ImpossivelCadastrarException;
import saru.saru_rest.exceptions.SenhaIncorretaException;
import saru.saru_rest.exceptions.UsuarioJaCadastradoException;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.security.JwtService;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    private ClienteRepository clienteRepository;
    private JwtService jwtService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthServiceImpl(ClienteRepository clienteRepository, JwtService jwtService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clienteRepository = clienteRepository;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public String pegarCPF(String token) {
        return jwtService.pegarCpfDoToken(token);
    }

    public String fazerLogin(LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException {
        Optional<ClienteEntity> entidade = clienteRepository.findById(login.getCpf());
        if(entidade.isEmpty()) {
            throw new CpfInexistenteException(login.getCpf());
        }
        ClienteEntity cliente = entidade.get();
        if(bCryptPasswordEncoder.matches(login.getSenha(), cliente.getSenha())){
            return jwtService.gerarTokenAluno(login.getCpf());
        }

        throw new SenhaIncorretaException();

    }

    public String fazerCadastro(CadastroDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException {
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
        return jwtService.gerarTokenAluno(novoCliente.getCpf());
    }

    private String hashSenha(String senha) {
        return bCryptPasswordEncoder.encode(senha);
    }

}
