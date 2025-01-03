package saru.saru_rest.entity;

import saru.saru_rest.entity.enums.Turno;

import java.sql.Date;

public class RefeicaoEntity {
    private int id;
    private String cpfCliente;
    private Date data;
    private Turno turno;
    private String token;

}
