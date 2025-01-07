package saru.saru_rest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.ClienteDTO;

@Service
public class JwtService {

    private String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCtyQhS9lOgdhM9" +
            "/Zib4V72MfMgx8VlRPiAUMXS0jIgdhSFVeOK/kkB7v5IU5K8NWL1rfbJLquVox19" +
            "AoQlwMlkOp0An5s71dGMGmo0QAEQmCXA517Gjr/WRzd3P6CvRC7KbSxzhxl2BHjg" +
            "jws9r6JnNuSvgY/RnnWSI8UXdp6UDUq7LtwlgeG20RNqoxPFwzlFF16CLtmxlh2f" +
            "jlz9kSMpsiPEwUio+fR1n1rias76igg+6YkuYgK4wyvKxY3n+mgzDb3sTC9lsufG" +
            "oYfwrMzYBDdKLaN7/AZJDNipyigwg2JacLXzHdeuxL0wKwcb2nZ5IC2lWJBZD/gS" +
            "wCW0LkTrAgMBAAECggEBAIE2cBH/RL6SDEyTOdzs/QZgrT06S/K4DWyaofvWfZ7V" +
            "LPzWl5DI8M+XO0nbmcaGi0XQSyXapYlk9o/0Rv0rEpKLYiEMiwc6O8vH+B9RV3lX" +
            "Jvr/DODlUo2n9f2cOYUXVRL9iYd8c9nkCVwt/1WmOUI09mPoGV7qStCV9/0yHUZG" +
            "QdPX4nfwKvT0s3cWPfbt4FlNLgRkHNTnCjmBsLo9Byu1Np0HLB7Bgez0o2rrFp0m" +
            "Ibmhu00+47g7TuZvjoApNmpjtvHtaapU3HTlWFZF13rTB/Z+vJrR0Fbp+e3YiMIx" +
            "BZ39b2TWdJa/IagGSIPviNDZqFM5r3KXDoUDpL0bcgECgYEA2+/EiiB65WwEw3+0" +
            "o+UMfQ4EfjtgwaLW9A31gWjI/wZXyslKBY1V4wmaFRSll1jCwqBcknDujbXdpfX0" +
            "/o9OvnH993RmEOyNUPtxk635U+zV+JsMP7D87RFodOX0eOGSC3aRVDeZtuBDjc8t" +
            "tMiiFe9CsXA+udaWmNohXS28veECgYEAykf7HrPwZo6iky/EK84oo2FTeoP9CMDu" +
            "J9e5Kp4VAVWDRZhUn8BS5/J6yM+rZz/zk6B3QDZmS/7TKRsNuoMJmxvq6JvE2uQ+" +
            "upfm9sNndAovXXEEoCOEsVv8OfJj4IKhG0xB0/S6rLHSNx3eguYyN0OJi/FW2a5x" +
            "zQWrYgMPJEsCgYBvvsjrLUmD23goJHCRfUT1HPQndt/YQnZYlecREz5cZMqiW9di" +
            "LsdejXheEZl45j4+SN///6UVFzxAoFEULZfip2RUIC3jl2zNopO4ZwJxI7orkAuP" +
            "KMu5X8jk7BlkeaeMb3ezhHRzOEraeFNOsUm5P1FxeghEv8TQcLPW9fAZwQKBgQC6" +
            "6D/1YrajTPCTp/NPtrJ+JJQKBKNtzzc/wWjrJB6smOMvD05CPmdcwwUJQDpUsRbU" +
            "NdV6k8l6Nv/OX50T1biuheFNBYGj8Hq1114mbO/OnQqfAKsRfUBC0YgB0yLOdGWA" +
            "37mDrAJBSB3Bp+vOb/2+ar1RfbKOeU3V74MokUMQeQKBgFoS8ejByiHrQx3sucBm" +
            "/A0DgoQoroJwbZuYK79DXO7RPYjz/dNbkuXeCN02rGHhuUvUNhwEK5JVRIZG/zA7" +
            "X/aF0riJEnWy03q/bJGa9C7iMWXTA9GiOt4rPUGu9GRFVRdfYRG4MiE2cnOQaWxN" +
            "//ALRmbJPLCqzBQ5hksGusoO";
    
    public String gerarTokenAluno(String cpf) {

        Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
        return JWT.create().withSubject(cpf).withClaim("role", "ALUNO" ).sign(algorithm);

    }

    public String pegarCpfDoToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
        token = token.substring(7);
        System.out.println(JWT.require(algorithm).build().verify(token).getSubject());
        return JWT.require(algorithm).build().verify(token).getSubject();


    }

}
