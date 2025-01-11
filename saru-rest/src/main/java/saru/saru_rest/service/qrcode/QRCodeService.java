package saru.saru_rest.service.qrcode;

import org.springframework.stereotype.Service;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import saru.saru_rest.entity.RefeicaoEntity;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


@Service

public class QRCodeService {

    public byte[] getQRCodeImage(RefeicaoEntity refeicao)throws WriterException, IOException{
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode("Codigo"+ refeicao.getToken() ,BarcodeFormat.QR_CODE, 400,400);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG", pngOutputStream);
        return pngOutputStream.toByteArray();
    }



}
