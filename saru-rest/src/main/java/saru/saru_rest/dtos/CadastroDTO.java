package saru.saru_rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import saru.saru_rest.entity.enums.Clientes;

public class CadastroDTO {
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("email")
    private String email;
    @JsonProperty("senha")
    private String senha;
    @JsonProperty("tipoCliente")
    private Clientes tipoCliente;


    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public Clientes getTipoCliente() {
        return tipoCliente;
    }
}
