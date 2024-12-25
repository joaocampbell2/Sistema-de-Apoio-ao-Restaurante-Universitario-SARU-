package saru.saru_rest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity

public class Cliente {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private float saldo;
    private Clientes tipoCliente;
}
