package com.mycompany.mariany.mercia.lucas.t1;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import de.taimos.totp.TOTP;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class TwoFactorAuthenticator {
    
    // https://github.com/taimos/totp/blob/master/src/main/java/de/taimos/totp/TOTP.java
    // TOTP Code permanece válido por 30 segundos
    public String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }
    
    private static void createQRCode(
        String codeData,
        String filePath,
        int height,
        int width
    )
            throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(
            codeData,
            BarcodeFormat.QR_CODE,
            246,
            246
        );
        try (FileOutputStream out = new FileOutputStream("QRCode.png")) {
            MatrixToImageWriter.writeToStream(matrix, "png", out);
        }

    }
}