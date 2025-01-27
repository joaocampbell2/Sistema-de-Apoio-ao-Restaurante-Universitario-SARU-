package saru.saru_rest.service.qrcode;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import saru.saru_rest.entity.RefeicaoEntity;
import com.google.zxing.qrcode.QRCodeWriter;
import saru.saru_rest.exceptions.RefeicaoJaUtilizadaException;
import saru.saru_rest.exceptions.RefeicaoNaoEncontradaException;
import saru.saru_rest.repository.RefeicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import saru.saru_rest.service.LogService;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class QRCodeService {

    private static final Logger logger = LoggerFactory.getLogger(QRCodeService.class);
    private final RefeicaoRepository refeicaoRepository;
    private LogService logService;

    public QRCodeService(RefeicaoRepository refeicaoRepository, LogService logService) {
        this.refeicaoRepository = refeicaoRepository;
        this.logService = logService;
    }

    public byte[] getQRCodeImage(RefeicaoEntity refeicao) throws WriterException, IOException {
        logger.info("Gerando QR Code para refeição com ID: {}", refeicao.getIdRefeicao());
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = qrCodeWriter.encode("http://localhost:4200/#/validar-refeicao/"+ refeicao.getToken() ,BarcodeFormat.QR_CODE, 400,400);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public boolean validarQrCode(String qrCodeToken) {
        logger.info("Validando QR Code com token: {}", qrCodeToken);
        Optional<RefeicaoEntity> refeicao = refeicaoRepository.findByToken(qrCodeToken);
        if (refeicao.isPresent()) {
            RefeicaoEntity refeicaoEntity = refeicao.get();

            if (!refeicaoEntity.isUtilizado()) {
                refeicaoEntity.setUtilizado(true);
                refeicaoRepository.save(refeicaoEntity);
                logger.info("QR Code validado com sucesso.");
                logService.criarLog((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),"cliente","QR Code gerado com sucesso","sucess","","Computador");
                return true;
            }
            logger.error("QR Code já foi utilizado.");
            logService.criarLog((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),"cliente","Gerar QR Code","error","","Computador");
            throw new RefeicaoJaUtilizadaException();
        }
        logger.error("Refeição não encontrada para o QR Code.");
        logService.criarLog((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal(),"cliente","Gerar QR Code","error","","Computador");
        throw new RefeicaoNaoEncontradaException();
    }
}
