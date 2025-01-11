package saru.saru_rest.service.refeicao;

import com.google.zxing.WriterException;
import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.*;
import saru.saru_rest.repository.ClienteRepository;
import saru.saru_rest.repository.RefeicaoRepository;
import saru.saru_rest.service.qrcode.QRCodeService;

import java.io.IOException;
import java.util.List;

@Service

public class RefeicaoServiceImpl implements RefeicaoService {

    private final ClienteRepository clienteRepository;
    private final RefeicaoRepository refeicaoRepository;
    private final QRCodeService qrCodeService;

    public RefeicaoServiceImpl(ClienteRepository clienteRepository, RefeicaoRepository refeicaoRepository, QRCodeService qrCodeService) {
        this.clienteRepository = clienteRepository;
        this.refeicaoRepository = refeicaoRepository;
        this.qrCodeService = qrCodeService;
    }

    public byte[] comprarRefeicao(RefeicaoDTO refeicao, String cpf) throws SaldoInsuficienteException, RefeicaoJaCompradaException, IOException, WriterException {
        float saldo = clienteRepository.findById(cpf).get().getSaldo();

        if(saldo < 2){
            throw new SaldoInsuficienteException();
        }

        if (!refeicaoRepository.findByCpfClienteAndDataAndTurno(cpf,refeicao.getDataRefeicao(),refeicao.getTurno()).isEmpty()){
            throw new RefeicaoJaCompradaException();
        }

        RefeicaoEntity refeicaoEntity = new RefeicaoEntity(cpf,refeicao.getDataRefeicao(),refeicao.getTurno());
        refeicaoRepository.save(refeicaoEntity);

        return qrCodeService.getQRCodeImage(refeicaoEntity);

    }


    public String alterarTurno(String cpf, Turno turno){
        List<RefeicaoEntity> refeicoes= refeicaoRepository.findByCpfCliente(cpf);
        try{
            if (clienteRepository.existsById(cpf) && verificaTurno(refeicoes, turno) && verificaSemRefeicoesCompradas(refeicoes) && verificaTodasRefeicoesCompradas(refeicoes)) {
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
        if (refeicoes.getFirst().getTurno().equals(turno)){
            throw new TurnoJaCompradoException();
        }
        return true;
    }
    public boolean verificaTodasRefeicoesCompradas(List<RefeicaoEntity> refeicoes) throws TodasRefeicoesCompradasException {
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
