package saru.saru_rest.entity.enums;

public enum Clientes {
    aluno("Aluno"), PROFESSOR("Professor"), VISITANTE("Visitante");

    private String tipoCliente;

    Clientes(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

}
