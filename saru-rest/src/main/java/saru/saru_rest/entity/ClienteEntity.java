package saru.saru_rest.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import saru.saru_rest.dtos.CadastroDTO;
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
    private String senha;


    public ClienteEntity(CadastroDTO cadastro) {
        this.cpf =  cadastro.getCpf();
        this.nome = cadastro.getNome();
        this.email = cadastro.getEmail();
        this.saldo = 0.0F;
        this.tipoCliente = cadastro.getTipoCliente();
        this.senha = cadastro.getSenha();
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

    public String getSenha() {
        return senha;
    }
}
