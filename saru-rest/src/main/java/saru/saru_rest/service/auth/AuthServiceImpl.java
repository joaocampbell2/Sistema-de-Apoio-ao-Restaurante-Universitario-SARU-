package saru.saru_rest.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saru.saru_rest.dtos.ClienteDTO;
import saru.saru_rest.entity.ClienteEntity;
import saru.saru_rest.repository.ClienteRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private ClienteEntity cliente;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public void fazerCadastro(ClienteDTO cliente) {
        ClienteEntity novoCliente = new ClienteEntity(cliente);
        ClienteEntity savedEntity = clienteRepository.save(novoCliente);
        System.out.println(savedEntity);
    }
}
