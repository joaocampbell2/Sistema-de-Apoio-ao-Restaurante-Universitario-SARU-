package saru.saru_rest.entity;

import jakarta.persistence.*;
import lombok.Data;
import saru.saru_rest.entity.enums.Turno;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "refeicao")
public class RefeicaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String cpfCliente;
    private Date data;
    private Turno turno;
    @Column(unique = true, nullable = false)
    private String token;

    public RefeicaoEntity(String cpfCliente, Date data, Turno turno) {
        this.cpfCliente = cpfCliente;
        this.data = data;
        this.turno = turno;
    }

    public RefeicaoEntity(Integer id, String cpfCliente, Date data, Turno turno, String token) {
        this.id = id;
        this.cpfCliente = cpfCliente;
        this.data = data;
        this.turno = turno;
        this.token = token;
    }

    public RefeicaoEntity() {

    }

    @PrePersist
    public void generateToken() {
        if (this.token == null || this.token.isEmpty()) {
            this.token = UUID.randomUUID().toString(); // Gerar um token único
        }
    }

}
