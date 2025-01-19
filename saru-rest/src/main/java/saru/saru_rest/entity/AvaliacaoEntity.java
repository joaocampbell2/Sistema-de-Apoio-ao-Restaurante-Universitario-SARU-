package saru.saru_rest.entity;
import jakarta.persistence.*;
import lombok.Data;
import saru.saru_rest.entity.enums.Turno;

@Data
@Entity
@Table(name = "avaliacao")

public class AvaliacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAvaliacao;
    private int nota;
    private String feedback;
    private int idRefeicao;

    public AvaliacaoEntity(int idAvaliacao, int nota, String feedback, int idRefeicao) {
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.feedback = feedback;
        this.idRefeicao = idRefeicao;
    }


    public AvaliacaoEntity() {

    }

    public AvaliacaoEntity(int nota, String feedback, int idRefeicao) {
        this.nota = nota;
        this.feedback = feedback;
        this.idRefeicao = idRefeicao;
    }
}
