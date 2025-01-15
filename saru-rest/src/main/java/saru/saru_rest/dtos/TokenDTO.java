package saru.saru_rest.dtos;

import lombok.Getter;

@Getter
public class TokenDTO {

    String token;


    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
