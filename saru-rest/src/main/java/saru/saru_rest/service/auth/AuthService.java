package saru.saru_rest.service.auth;

import saru.saru_rest.dtos.CadastroClienteDTO;
import saru.saru_rest.dtos.CadastroFuncionarioDTO;
import saru.saru_rest.dtos.LoginDTO;
import saru.saru_rest.exceptions.CpfInexistenteException;
import saru.saru_rest.exceptions.ImpossivelCadastrarException;
import saru.saru_rest.exceptions.SenhaIncorretaException;
import saru.saru_rest.exceptions.UsuarioJaCadastradoException;

public interface AuthService {


    public String fazerCadastroAluno(CadastroClienteDTO cadastro) throws UsuarioJaCadastradoException, ImpossivelCadastrarException;

    public String fazerLogin(LoginDTO login) throws SenhaIncorretaException, CpfInexistenteException;

    public String fazerCadastroFuncionario(CadastroFuncionarioDTO cadastro) throws ImpossivelCadastrarException, UsuarioJaCadastradoException;
}
