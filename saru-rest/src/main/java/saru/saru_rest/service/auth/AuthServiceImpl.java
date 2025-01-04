package saru.saru_rest.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saru.saru_rest.dtos.ClienteDTO;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.security.JwtService;

@Service
public class AuthServiceImpl implements AuthService {

    private ClienteEntity cliente;
    @Autowired
    private ClienteRepository clienteRepository;


    @Autowired
    private JwtService jwtService;

    @Transactional
    public String fazerCadastro(ClienteDTO cliente) {
        ClienteEntity novoCliente = new ClienteEntity(cliente);
        ClienteEntity savedEntity = clienteRepository.save(novoCliente);
        return jwtService.gerarTokenCliente(cliente);

    }

    public String pegarCPF(String token) {
        return jwtService.pegarCpfDoToken(token);
    }
}
