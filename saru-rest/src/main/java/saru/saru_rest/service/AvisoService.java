package saru.saru_rest.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.AvisoDTO;
import saru.saru_rest.entity.AvisoEntity;
import saru.saru_rest.repository.AvisoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AvisoService {

    private static final Logger logger = LoggerFactory.getLogger(AvisoService.class);

    private AvisoRepository avisoRepository;

    private LogService logService;

    public AvisoService(AvisoRepository avisoRepository, LogService logService) {
        this.avisoRepository = avisoRepository;
        this.logService = logService;
    }

    public List<AvisoDTO> buscarTodosAvisos() {
        logger.info("Buscando todos os avisos cadastrados");
        List<AvisoDTO> avisos = new ArrayList<>();
        for (AvisoEntity aviso : avisoRepository.findAll()) {
            avisos.add(new AvisoDTO(aviso));
        }
        logger.info("Encontrados {} avisos", avisos.size());
        logService.criarLog((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),"cliente","Buscar avisos","sucess","","Computador");

        return avisos;
    }

    public String publicarAviso(String cpf, AvisoDTO aviso) {
        logger.info("Recebendo requisição para publicar aviso de funcionário com CPF {}", cpf);
        List<AvisoEntity> avisos = avisoRepository.findAll();
        int idAvisos = avisos.size();
        AvisoEntity avisoEntity = new AvisoEntity(aviso, cpf, idAvisos);
        
        avisoRepository.save(avisoEntity);
        logger.info("Aviso com ID {} publicado com sucesso para o funcionário com CPF {}", idAvisos, cpf);
        logService.criarLog(cpf,"funcionario","Criar novo aviso","sucess","","Computador");
        return "Aviso cadastrado com sucesso!";
    }
}
