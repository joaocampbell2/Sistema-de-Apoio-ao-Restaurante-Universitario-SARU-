package saru.saru_rest.entity;

import jakarta.persistence.*;
import lombok.Data;
import saru.saru_rest.dtos.CadastroClienteDTO;
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


    public ClienteEntity(CadastroClienteDTO cadastro) {
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

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public void setTipoCliente(Clientes tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
