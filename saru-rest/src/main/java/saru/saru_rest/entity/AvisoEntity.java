package saru.saru_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.springframework.lang.Nullable;
import saru.saru_rest.dtos.AvisoDTO;

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
    @Nullable
    private String imagem;

    public AvisoEntity(AvisoDTO aviso, String cpf, int idAviso) {
        this.texto = aviso.getTexto();
        this.cpfFuncionario = cpf;
        this.data = aviso.getData();
        this.imagem = aviso.getImagem();
        this.idAviso = idAviso;
    }

    public AvisoEntity() {

    }

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
