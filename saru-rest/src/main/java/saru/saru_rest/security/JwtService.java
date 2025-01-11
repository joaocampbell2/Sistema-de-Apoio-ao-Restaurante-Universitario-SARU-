package saru.saru_rest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JwtService {

    @Value("${private_key}")
    private String PRIVATE_KEY;
    
    public String gerarTokenAluno(String cpf) {
        Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
        return JWT.create().withSubject(cpf).withClaim("role", "ALUNO" ).sign(algorithm);
    }

    public String gerarTokenFuncionario(String cpf) {
        Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
        return JWT.create().withSubject(cpf).withClaim("role", "FUNCIONARIO" ).sign(algorithm);
    }

    public String pegarCpfDoToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
        return JWT.require(algorithm).build().verify(token).getSubject();
    }


}
