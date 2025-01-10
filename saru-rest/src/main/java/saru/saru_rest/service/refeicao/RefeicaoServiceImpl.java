package saru.saru_rest.service.refeicao;

import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.*;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.service.QRCodeService.QRCodeService;

import java.sql.Date;
import java.util.List;

@Service

public class RefeicaoServiceImpl implements RefeicaoService {

    private ClienteRepository clienteRepository;
    private RefeicaoRepository refeicaoRepository;

    public RefeicaoServiceImpl(ClienteRepository clienteRepository, RefeicaoRepository refeicaoRepository) {
        this.clienteRepository = clienteRepository;
        this.refeicaoRepository = refeicaoRepository;
    }

    public void comprarRefeicao(RefeicaoDTO refeicao,String cpf) throws SaldoInsuficienteException, RefeicaoJaCompradaException {
        float saldo = clienteRepository.findById(cpf).get().getSaldo();

        if(saldo < 2){
            throw new SaldoInsuficienteException();
        }

        if (!refeicaoRepository.findByCpfClienteAndDataAndTurno(cpf,refeicao.getDataRefeicao(),refeicao.getTurno()).isEmpty()){
            throw new RefeicaoJaCompradaException();
        }



        RefeicaoEntity refeicaoEntity = new RefeicaoEntity(cpf,refeicao.getDataRefeicao(),refeicao.getTurno());
        refeicaoRepository.save(refeicaoEntity);


        try {
            byte[] qrCodeData = QRCodeService.getQRCodeImage(refeicaoEntity);
            refeicaoEntity.setQrCodeData(qrCodeData);
            refeicaoRepository.save(refeicaoEntity);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar QR Code: " + e.getMessage());
        }

    }

    public String alterarTurno(String cpf, Turno turno, Date data) throws SemRefeicoesCompradasException, TodasRefeicoesCompradasException, TurnoJaCompradoException {
        List<RefeicaoEntity> refeicoes= refeicaoRepository.findByCpfClienteAndData(cpf, data);
        try{
            if (clienteRepository.existsById(cpf) && verificaTurno(refeicoes, turno) && verificaSemRefeicoesCompradas(refeicoes) && verificaTodasRefeicoesCompradasDoDia(refeicoes)) {
                refeicoes.getFirst().setTurno(turno);
                refeicoes.getFirst().generateNewToken();
                refeicaoRepository.save(refeicoes.getFirst());

                return "Turno alterado com sucesso";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Erro ao alterar turno";
    }
    public boolean verificaTurno(List<RefeicaoEntity> refeicoes, Turno turno) throws TurnoJaCompradoException{
        if (refeicoes.get(0).getTurno().equals(turno)){
            throw new TurnoJaCompradoException();
        }
        return true;
    }
    public boolean verificaTodasRefeicoesCompradasDoDia(List<RefeicaoEntity> refeicoes) throws TodasRefeicoesCompradasException {
        if (refeicoes.size() >= 2){
            throw new TodasRefeicoesCompradasException();
        }
        return true;
    }
    public boolean verificaSemRefeicoesCompradas(List<RefeicaoEntity> refeicoes) throws SemRefeicoesCompradasException {
        if (refeicoes.isEmpty()) {
            throw new SemRefeicoesCompradasException();
        }
        return true;
    }

}
