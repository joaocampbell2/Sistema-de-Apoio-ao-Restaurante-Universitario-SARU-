package saru.saru_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.entity.enums.Turno;

import java.sql.Date;
import java.util.List;

public interface RefeicaoRepository extends JpaRepository<RefeicaoEntity,Integer> {

    List<RefeicaoEntity> findByCpfClienteAndDataAndTurno(String cpf, Date data, Turno turno);


    List<RefeicaoEntity> findByCpfCliente(String cpf);

    List<RefeicaoEntity> findByTurno(Turno turno);

    List<RefeicaoEntity> findByDataAndTurno(Date data, Turno turno);

    List<RefeicaoEntity> findByCpfClienteAndData(String cpf, Date data);

    boolean existsByCpfClienteAndDataAndTurno(String cpf, Date data, Turno turno);

    RefeicaoEntity findByidRefeicao(int idRefeicao);

    boolean existsByDataAndTurno(Date data, Turno novoTurno);
}
