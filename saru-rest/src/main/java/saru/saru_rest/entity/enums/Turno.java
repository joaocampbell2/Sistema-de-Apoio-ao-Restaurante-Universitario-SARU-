package saru.saru_rest.entity.enums;

public enum Turno {
    ALMOCO("ALMOCO"), JANTAR("JANTAR");

    private String tipoRefeicao;

    Turno(String tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public String getTipoRefeicao() {
        return tipoRefeicao;
    }
}
