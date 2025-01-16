package saru.saru_rest.entity.enums;

public enum Turno {
    almoco("almoco"), jantar("Jantar");

    private String tipoRefeicao;

    Turno(String tipoRefeicao) {
        this.tipoRefeicao = tipoRefeicao;
    }

    public String getTipoRefeicao() {
        return tipoRefeicao;
    }
}
