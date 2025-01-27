package saru.saru_rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import saru.saru_rest.dtos.LogDTO;
import saru.saru_rest.entity.LogEntity;
import saru.saru_rest.service.LogService;

import java.util.ArrayList;
import java.util.List;
@RestController
public class LogController {

    private final LogService logService;

    LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping(value = "/logs")
    public ResponseEntity<List<LogDTO>> getLogs() {
        List<LogEntity >logs = logService.getLogs();
        List<LogDTO> logsDTO = new ArrayList<>();
        for (LogEntity log : logs) {
            logsDTO.add(new LogDTO(log));
        }
        return ResponseEntity.status(HttpStatus.OK).body(logsDTO);

    }


}
