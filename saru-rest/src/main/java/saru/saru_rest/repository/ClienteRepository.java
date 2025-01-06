package saru.saru_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saru.saru_rest.entity.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
    public static String adicionarSaldo() {
            
            return "a"; 
        }
}
