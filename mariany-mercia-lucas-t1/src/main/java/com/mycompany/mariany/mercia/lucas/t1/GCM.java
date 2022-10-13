package com.mycompany.mariany.mercia.lucas.t1;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;


/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class GCM {

    private final int T_LEN = 128;
    
    private Cipher encryptionCipher;
    
    public String encrypt(String text, SecretKey secretKey, String salt){
        try {
            byte[] messageInBytes = text.getBytes();
            byte[] nonce = org.apache.commons.codec.binary.Hex.decodeHex(salt.toCharArray());
            GCMParameterSpec​ gcmParameters = new GCMParameterSpec​(T_LEN, nonce);
            encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding", "BCFIPS");
            encryptionCipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameters);
            byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);

            String derivatedText = encode(encryptedBytes);

            System.out.println(
                "[DEBUG] GCM ENCRIPT:"
                + "\n[DEBUG] -- text: " + text
                + "\n[DEBUG] -- secretKey: " + secretKey
                + "\n[DEBUG] -- Chave derivada: " + derivatedText
            );

            return derivatedText;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
    
        
    public String decrypt(String derivatedText, SecretKey secretKey, String salt){
        try {
            byte[] messageInBytes = decode(derivatedText);
            Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding", "BCFIPS");
            byte[] nonce = org.apache.commons.codec.binary.Hex.decodeHex(salt.toCharArray());
            GCMParameterSpec​ gcmParameters = new GCMParameterSpec​(T_LEN, nonce);
            decryptionCipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameters);
            byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);

            String text = new String(decryptedBytes);

            System.out.println(
                "[DEBUG] GCM DECRIPT:"
                + "\n[DEBUG] -- derivatedText: " + derivatedText
                + "\n[DEBUG] -- secretKey: " + secretKey
                + "\n[DEBUG] -- text: " + text
            );

            return text;
        } catch (Exception e) {
             e.printStackTrace();
        }
        return null;
    }
    
    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }
}
