package saru.saru_rest.service.auth;

import org.springframework.stereotype.Service;
import saru.saru_rest.entity.ClienteEntity;

@Service
public class AuthServiceImpl implements AuthService {

    private ClienteEntity cliente;

    private void fazerCadastro() {
        cliente = new ClienteEntity();
    }




}
