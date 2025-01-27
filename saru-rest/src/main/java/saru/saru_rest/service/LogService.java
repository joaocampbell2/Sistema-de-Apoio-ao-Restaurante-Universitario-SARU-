package saru.saru_rest.service;

import org.springframework.stereotype.Service;
import saru.saru_rest.entity.LogEntity;
import saru.saru_rest.repository.LogsRepository;

import java.util.List;
@Service

public class LogService {

    private final LogsRepository logRepository;

    LogService(LogsRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<LogEntity> getLogs() {
        return logRepository.findAll();
    }
}
