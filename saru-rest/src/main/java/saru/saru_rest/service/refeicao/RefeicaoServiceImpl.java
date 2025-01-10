package saru.saru_rest.service.refeicao;

import org.springframework.stereotype.Service;
import saru.saru_rest.dtos.RefeicaoDTO;
import saru.saru_rest.entity.RefeicaoEntity;
import saru.saru_rest.entity.enums.Turno;
import saru.saru_rest.exceptions.DataNaoPossuiComprasException;
import saru.saru_rest.exceptions.RefeicaoJaCompradaException;
import saru.saru_rest.exceptions.SaldoInsuficienteException;
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

    @Override
    public List<RefeicaoEntity> verRefeicoes(RefeicaoDTO dataRefeicao) throws DataNaoPossuiComprasException {
        List<RefeicaoEntity> refeicao = refeicaoRepository.findByDataAndTurno(dataRefeicao.getDataRefeicao(),dataRefeicao.getTurno());
        try {
            if (verificaRefeiçoesDataExistem(refeicao)){
                return refeicao;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    @Override
    public List<RefeicaoEntity> verRefeicoes(Date dataRefeicao, Turno turno) throws DataNaoPossuiComprasException {
        List<RefeicaoEntity> refeicao = refeicaoRepository.findByDataAndTurno(dataRefeicao,turno);
        try {
            if (verificaRefeiçoesDataExistem(refeicao)){
                return refeicao;
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    public boolean verificaRefeiçoesDataExistem(List<RefeicaoEntity> refeicao) throws DataNaoPossuiComprasException {
        if (refeicao.isEmpty()){
            throw new DataNaoPossuiComprasException();
        }
        return true;
    }

}
