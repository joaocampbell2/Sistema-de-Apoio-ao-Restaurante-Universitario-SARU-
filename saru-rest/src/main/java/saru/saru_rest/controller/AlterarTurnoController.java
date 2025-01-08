package saru.saru_rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import saru.saru_rest.dtos.ClienteDTO;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.dtos.SaldoDTO;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.SemRefeicoesCompradasException;
import saru.saru_rest.exceptions.TodasRefeicoesCompradasException;
import saru.saru_rest.exceptions.TurnoJaCompradoException;
import saru.saru_rest.security.JwtService;
import saru.saru_rest.service.AlterarTurnoService;

@DeclareRoles({"ALUNO, PROFESSOR"})
@RolesAllowed({"ALUNO, PROFESSOR"})
@RestController
public class AlterarTurnoController {
    AlterarTurnoService alterarTurnoService;
    JwtService jwtService;

    public AlterarTurnoController(AlterarTurnoService alterarTurnoService, JwtService jwtService) {
        this.alterarTurnoService = alterarTurnoService;
        this.jwtService = jwtService;
    }

    @PutMapping(value="/alterarTurno")
    public ResponseEntity<String> alterarTurno(@RequestBody RefeicaoDTO refeicaoDTO) throws TodasRefeicoesCompradasException, TurnoJaCompradoException, SemRefeicoesCompradasException {

        String cpf = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        alterarTurnoService.alterarTurno(cpf, refeicaoDTO.getTurno());
        return ResponseEntity.ok().body("Turno alterado com sucesso");
    }
}
