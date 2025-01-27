package saru.saru_rest.controller;

import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import saru.saru_rest.dtos.AvisoDTO;
import saru.saru_rest.service.AvisoService;

import java.util.List;

@RestController
@DeclareRoles({"FUNCIONARIO"})
@RolesAllowed({"FUNCIONARIO"})
@RequestMapping(value = "/aviso")
public class AvisoController {

    private static final Logger logger = LoggerFactory.getLogger(AvisoController.class);

    private AvisoService avisoService;

    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    @GetMapping(value = "/buscarAvisos")
    public ResponseEntity<List<AvisoDTO>> listarAvisos() {
        logger.info("Recebendo requisição para listar todos os avisos");
        List<AvisoDTO> avisos = avisoService.buscarTodosAvisos();
        logger.info("Requisição para listar avisos processada com sucesso, retornando {} avisos", avisos.size());
        return ResponseEntity.ok(avisos);
    }

    @PostMapping(value = "/publicarAvisos")
    public ResponseEntity<String> publicarAviso(@RequestBody AvisoDTO aviso) {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("Funcionário com CPF {} solicitou a publicação de um novo aviso", cpf);
        String response = avisoService.publicarAviso(cpf, aviso);
        logger.info("Aviso publicado com sucesso para o funcionário com CPF {}", cpf);
        return ResponseEntity.ok(response);
    }
}
