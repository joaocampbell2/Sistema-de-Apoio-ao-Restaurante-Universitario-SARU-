package saru.saru_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;


@Entity
@Data
@Table(name = "aviso")

public class AvisoEntity {
    @Id
    private int idAviso;
    private String texto;
    private String cpfFuncionario;
    private Date data;
    private String imagem;


    public int getIdAviso() {
        return idAviso;
    }

    public String getTexto() {
        return texto;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public Date getData() {
        return data;
    }

    public String getImagem() {
        return imagem;
    }
}
