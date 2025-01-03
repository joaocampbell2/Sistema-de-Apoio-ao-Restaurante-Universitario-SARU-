package saru.saru_rest.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import saru.saru_rest.entity.enums.Clientes;

@Getter


public class ClienteDTO {
    @JsonProperty("cpf")
    private String cpf;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("email")
    private String email;
    @JsonProperty("saldo")
    private float saldo;
    @JsonProperty("tipoCliente")
    private Clientes tipoCliente;


    @Override
    public String toString() {
        return "ClienteDTO{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", saldo=" + saldo +
                ", tipoCliente=" + tipoCliente +
                '}';
    }


    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public float getSaldo() {
        return saldo;
    }

    public Clientes getTipoCliente() {
        return tipoCliente;
    }
}

