package saru.saru_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saru.saru_rest.entity.AvisoEntity;
import saru.saru_rest.repository.AvisoRepository;

import java.util.List;

@Service
public class AvisoService {

    @Autowired
    private AvisoRepository avisoRepository;

    public List<AvisoEntity> buscarTodosAvisos() {
        return avisoRepository.findAll();
    }
}