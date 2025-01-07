package saru.saru_rest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import saru.saru_rest.entity.AvisoEntity;
import saru.saru_rest.service.AvisoService;

import java.util.List;

@RestController
public class AvisoController {

    @Autowired
    private AvisoService avisoService;

    @GetMapping(value = "/avisos")
    public ResponseEntity<List<AvisoEntity>> listarAvisos() {
        List<AvisoEntity> avisos = avisoService.buscarTodosAvisos();
        return ResponseEntity.ok(avisos);
    }
}