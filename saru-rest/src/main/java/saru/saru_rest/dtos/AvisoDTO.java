package saru.saru_rest.dtos;

import jakarta.annotation.Nullable;
import saru.saru_rest.entity.AvisoEntity;

import java.sql.Date;

public class AvisoDTO {
    private String texto;
    private Date data;
    @Nullable
    private String imagem;

    public AvisoDTO(AvisoEntity aviso) {
        this.texto = aviso.getTexto();
        this.data = aviso.getData();
        this.imagem = aviso.getImagem();
    }

    public AvisoDTO(){}

    public String getTexto() {
        return texto;
    }

    public Date getData() {
        return data;
    }

    public String getImagem() {
        return imagem;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setImagem(@Nullable String imagem) {
        this.imagem = imagem;
    }
}
