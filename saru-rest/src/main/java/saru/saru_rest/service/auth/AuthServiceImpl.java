package saru.saru_rest.service.auth;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import saru.saru_rest.dtos.CadastroDTO;
import saru.saru_rest.dtos.LoginDTO;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.SenhaIncorretaException;
import saru.saru_rest.exceptions.UsuarioJaCadastradoException;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.security.JwtService;

import java.util.Optional;
@Service
public class AuthServiceImpl implements AuthService {

    private ClienteEntity cliente;
    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


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
        else {
            throw new SenhaIncorretaException();
        }
    }

    public String fazerCadastro(CadastroDTO cadastro) throws UsuarioJaCadastradoException {
        Optional<ClienteEntity> entidade = clienteRepository.findById(cadastro.getCpf());
        if(entidade.isPresent()){
            throw new UsuarioJaCadastradoException(cadastro.getCpf());
        }
        else {
            ClienteEntity novoCliente = new ClienteEntity(cadastro);
            novoCliente.setSenha(hashSenha(novoCliente.getSenha()));
            clienteRepository.save(novoCliente);
            return jwtService.gerarTokenAluno(novoCliente.getCpf());
        }

    }

    private String hashSenha(String senha) {
        return bCryptPasswordEncoder.encode(senha);
    }




}
