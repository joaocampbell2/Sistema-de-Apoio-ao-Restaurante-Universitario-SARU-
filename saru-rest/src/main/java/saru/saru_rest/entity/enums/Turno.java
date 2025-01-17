package saru.saru_rest.entity.enums;

public enum Turno {
    ALMOCO("Almoco"), JANTAR("Jantar");

    private String tipoRefeicao;

    Turno(String tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public String getTipoRefeicao() {
        return tipoRefeicao;
    }
}
