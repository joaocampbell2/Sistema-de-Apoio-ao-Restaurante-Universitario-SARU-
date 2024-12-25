package saru.saru_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saru.saru_rest.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
