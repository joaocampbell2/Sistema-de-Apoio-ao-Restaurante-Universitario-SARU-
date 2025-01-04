package saru.saru_rest.service.auth;

import saru.saru_rest.dtos.ClienteDTO;

public interface AuthService {

    public String fazerCadastro(ClienteDTO cliente);


    public String pegarCPF(String token);

}
