package saru.saru_rest.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import saru.saru_rest.entity.LogEntity;


@Repository
public interface LogsRepository extends MongoRepository<LogEntity, String> {
}
