package saru.saru_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import saru.saru_rest.dtos.CadastroFuncionarioDTO;
@Data
@Entity
@Table(name = "funcionario")
public class FuncionarioEntity {
    private String nome;
    @Id
    private String cpf;
    private String cargo;
    private String senha;
    public FuncionarioEntity(CadastroFuncionarioDTO cadastro) {
        this.nome = cadastro.getNome();
        this.cpf = cadastro.getCpf();
        this.cargo = cadastro.getCargo();
        this.senha = cadastro.getSenha();
    }

    public FuncionarioEntity() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
