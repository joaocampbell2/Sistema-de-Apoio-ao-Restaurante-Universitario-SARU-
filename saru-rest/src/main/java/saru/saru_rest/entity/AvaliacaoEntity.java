package saru.saru_rest.entity;
import jakarta.persistence.*;
import lombok.Data;
import saru.saru_rest.entity.enums.Turno;

@Data
@Entity
@Table(name = "AVALIACAO")

public class AvaliacaoEntity {

    @Id
    private int idAvaliacao;
    private int nota;
    private String feedback;
    private int idRefeicao;
    @Enumerated(EnumType.STRING)
    private Turno turno;

    public AvaliacaoEntity(int idAvaliacao, int nota, String feedback, int idRefeicao) {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.feedback = feedback;
        this.idRefeicao = idRefeicao;
    }

    public AvaliacaoEntity(int nota, String feedback, Turno turno) {
        this.nota = nota;
        this.feedback = feedback;
        this.turno = turno;
    }

    public AvaliacaoEntity() {

    }
}
