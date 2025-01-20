package saru.saru_rest.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import saru.saru_rest.entity.enums.Turno;

import java.sql.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "refeicao")
public class RefeicaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_refeicao")
    private Integer idRefeicao;
    private String cpfCliente;
    private Date data;
    @Enumerated(EnumType.STRING)
    private Turno turno;
    @Column(unique = true, nullable = false)
    private String token;
    private boolean utilizado;

    public RefeicaoEntity(String cpfCliente, Date data, Turno turno) {
        this.cpfCliente = cpfCliente;
        this.data = data;
        this.turno = turno;
    }

    public RefeicaoEntity(Integer id, String cpfCliente, Date data, Turno turno, String token) {
        this.idRefeicao = id;
        this.cpfCliente = cpfCliente;
        this.data = data;
        this.turno = turno;
        this.token = token;
    }

    public RefeicaoEntity() {

    }


    @Lob
    private byte[] qrCodeData;
    public byte[] getQrCodeData() {
        return qrCodeData;
    }
    public void setQrCodeData(byte[] qrCodeData) {
        this.qrCodeData = qrCodeData;
    }
    @PrePersist
    public void generateToken() {
        if (this.token == null || this.token.isEmpty()) {
            this.token = UUID.randomUUID().toString(); // Gerar um token único
        }
    }
    public void generateNewToken(){
        this.token = UUID.randomUUID().toString();
    }

    public void setId(Integer id) {
        this.idRefeicao = id;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getId() {
        return idRefeicao;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public Date getData() {
        return data;
    }

    public Turno getTurno() {
        return turno;
    }

    public String getToken() {
        return token;
    }

    public Integer getId_refeicao() {
        return idRefeicao;
    }

    public boolean isUtilizado() {
        return utilizado;
    }

    public Integer getIdRefeicao() {
        return idRefeicao;
    }

    public void setUtilizado(boolean utilizado) {
        this.utilizado = utilizado;
    }
}

