package com.mycompany.mariany.mercia.lucas.t1;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Hex;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class PBKDF2 {
    public String createSecretKey(String password, String salt){
        int iterations = 1000;
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), 
                salt.getBytes(), iterations, 128);
        SecretKeyFactory pbkdf2 = null;
        String derivedText = null;
        try {
            pbkdf2 = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512", "BCFIPS");
            SecretKey sk = pbkdf2.generateSecret(spec);
            derivedText = Hex.encodeHexString(sk.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("[DEBUG] Chave derivada usando PBKDF2: ");
        System.out.println(derivedText);

        return derivedText;
    }

}
