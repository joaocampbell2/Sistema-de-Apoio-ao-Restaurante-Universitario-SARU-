package saru.saru_rest.service;

import org.springframework.stereotype.Service;
import saru.saru_rest.entity.LogEntity;
import saru.saru_rest.repository.LogsRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service

public class LogService {

    private final LogsRepository logRepository;

    LogService(LogsRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<LogEntity> getLogs() {
        return logRepository.findAll();
    }

    public void criarLog(String cpf, String tipoUsuario,String descricao, String status , String ipOrigem, String device){
        Instant timestamp = Instant.now();
        String id = timestamp+ "_" + UUID.randomUUID().toString();
        LogEntity log = new LogEntity(id,cpf, tipoUsuario,descricao, status ,ipOrigem, device);
        this.logRepository.save(log);
    }


}
