package saru.saru_rest.entity;

import jakarta.persistence.*;
import lombok.Data;
import saru.saru_rest.dtos.ClienteDTO;
import saru.saru_rest.entity.enums.Clientes;

@Data
@Entity
@Table(name = "CLIENTE")

public class ClienteEntity {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private float saldo;
    @Enumerated(EnumType.STRING)
    private Clientes tipoCliente;



    public ClienteEntity(ClienteDTO usuario) {
        this.cpf =  usuario.getCpf();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.saldo = usuario.getSaldo();
        this.tipoCliente = usuario.getTipoCliente();

    }
    public ClienteEntity() {}

    @Override
    public String toString() {
        return "ClienteEntity{" +
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
