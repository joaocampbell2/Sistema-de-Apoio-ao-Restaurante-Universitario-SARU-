package saru.saru_rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class LoginDTO {
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("senha")
    private String senha;

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }
}
