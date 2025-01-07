package saru.saru_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import saru.saru_rest.entity.AvisoEntity;

public interface AvisoRepository extends JpaRepository<AvisoEntity, Integer> {
    
}