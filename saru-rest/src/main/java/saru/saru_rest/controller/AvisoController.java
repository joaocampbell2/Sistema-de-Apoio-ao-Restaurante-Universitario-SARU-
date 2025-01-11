package saru.saru_rest.controller;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
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

    private AvisoService avisoService;

    public AvisoController(AvisoService avisoService) {
        this.avisoService = avisoService;
    }

    @GetMapping(value = "/buscarAvisos")
    public ResponseEntity<List<AvisoDTO>> listarAvisos() {
        List<AvisoDTO> avisos = avisoService.buscarTodosAvisos();
        return ResponseEntity.ok(avisos);
    }

    @PostMapping(value = "/publicarAvisos")
    public ResponseEntity<String> publicarAviso(@RequestBody AvisoDTO aviso) {
        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(avisoService.publicarAviso(cpf, aviso));
    }
}