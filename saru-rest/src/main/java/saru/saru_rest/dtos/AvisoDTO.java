package saru.saru_rest.dtos;

import saru.saru_rest.entity.AvisoEntity;

import java.sql.Date;

public class AvisoDTO {
    private String texto;
    private Date data;
    private String imagem;

    public AvisoDTO(AvisoEntity aviso) {
        this.texto = aviso.getTexto();
        this.data = aviso.getData();
        this.imagem = aviso.getImagem();
    }

    public String getTexto() {
        return texto;
    }

    public Date getData() {
        return data;
    }

    public String getImagem() {
        return imagem;
    }


}
