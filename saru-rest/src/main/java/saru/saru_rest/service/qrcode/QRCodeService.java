package saru.saru_rest.service.qrcode;

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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@Service
public class QRCodeService {

    private static final Logger logger = LoggerFactory.getLogger(QRCodeService.class);
    private final RefeicaoRepository refeicaoRepository;

    public QRCodeService(RefeicaoRepository refeicaoRepository) {
        this.refeicaoRepository = refeicaoRepository;
    }

    public byte[] getQRCodeImage(RefeicaoEntity refeicao) throws WriterException, IOException {
        logger.info("Gerando QR Code para refeição com ID: {}", refeicao.getIdRefeicao());
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode("http://localhost:8080/refeicao/validarQrCode/" + refeicao.getToken(), BarcodeFormat.QR_CODE, 400, 400);
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
                return true;
            }
            logger.error("QR Code já foi utilizado.");
            throw new RefeicaoJaUtilizadaException();
        }
        logger.error("Refeição não encontrada para o QR Code.");
        throw new RefeicaoNaoEncontradaException();
    }
}
