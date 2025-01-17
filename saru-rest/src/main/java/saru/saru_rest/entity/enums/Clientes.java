package saru.saru_rest.entity.enums;

public enum Clientes {
    aluno("Aluno"), professor("Professor"), visitante("Visitante");

    private String tipoCliente;

    Clientes(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

}
