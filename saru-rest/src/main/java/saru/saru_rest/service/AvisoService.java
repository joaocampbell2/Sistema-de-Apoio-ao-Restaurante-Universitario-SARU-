package saru.saru_rest.service;

import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.AvisoDTO;
import saru.saru_rest.entity.AvisoEntity;
import saru.saru_rest.repository.AvisoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvisoService {

    private AvisoRepository avisoRepository;

    public AvisoService(AvisoRepository avisoRepository) {
        this.avisoRepository = avisoRepository;
    }

    public List<AvisoDTO> buscarTodosAvisos() {
        List<AvisoDTO> avisos = new ArrayList<>();
        for (AvisoEntity aviso : avisoRepository.findAll()) {
            avisos.add(new AvisoDTO(aviso));
        }
        return avisos;
    }
    public String publicarAviso(String cpf, AvisoDTO aviso) {
        AvisoEntity avisoEntity = new AvisoEntity(aviso, cpf);
        avisoRepository.save(avisoEntity);
        return "Aviso cadastrado com sucesso!";
    }
}