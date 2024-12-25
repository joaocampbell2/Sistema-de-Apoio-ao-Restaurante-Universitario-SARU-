package saru.saru_rest.service.auth;

import org.springframework.stereotype.Service;
import saru.saru_rest.model.Cliente;

@Service
public class AuthServiceImpl implements AuthService {

    private Cliente cliente;

    private void fazerCadastro() {
        cliente = new Cliente();
    }




}
