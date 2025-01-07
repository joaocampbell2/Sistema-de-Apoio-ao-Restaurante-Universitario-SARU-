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
    private Boolean utilizado;

    public RefeicaoEntity(String cpfCliente, Date data, Turno turno) {
        this.cpfCliente = cpfCliente;
        this.data = data;
        this.turno = turno;
        this.utilizado = false;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getUtilizado() {
        return utilizado;
    }

    public void setUtilizado(Boolean utilizado) {
        this.utilizado = utilizado;
    }
}