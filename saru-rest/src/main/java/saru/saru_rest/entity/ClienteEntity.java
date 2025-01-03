package saru.saru_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import saru.saru_rest.entity.enums.Clientes;

@Data
@Entity

public class ClienteEntity {
    @Id
    private String cpf;
    private String nome;
    private String email;
    private float saldo;
    private Clientes tipoCliente;
}
