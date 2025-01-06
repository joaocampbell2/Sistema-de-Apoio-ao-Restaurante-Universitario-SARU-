package saru.saru_rest.service.auth;

import saru.saru_rest.dtos.CadastroDTO;
import saru.saru_rest.dtos.ClienteDTO;
import saru.saru_rest.dtos.LoginDTO;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.SenhaIncorretaException;
import saru.saru_rest.exceptions.UsuarioJaCadastradoException;

public interface AuthService {


    public String fazerCadastro(CadastroDTO cadastro) throws UsuarioJaCadastradoException;

    public String fazerLogin(LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException;

}
