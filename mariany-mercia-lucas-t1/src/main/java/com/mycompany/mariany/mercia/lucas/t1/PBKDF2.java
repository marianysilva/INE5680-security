package com.mycompany.mariany.mercia.lucas.t1;

import java.security.Security;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jcajce.provider.BouncyCastleFipsProvider;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class PBKDF2 {  
        public SecretKey createSecretKey(String text, String salt){
        int addProvider = Security.addProvider(new BouncyCastleFipsProvider());
        
        int iterations = 1000;
        int keyLength = 128;
        PBEKeySpec spec = new PBEKeySpec(text.toCharArray(), 
                salt.getBytes(), iterations, keyLength);
        SecretKeyFactory pbkdf2 = null;
        String derivedText = null;
        try {
            pbkdf2 = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512", "BCFIPS");
            SecretKey sk = pbkdf2.generateSecret(spec);
            
            derivedText = Hex.encodeHexString(sk.getEncoded());
            System.out.println(
                "[DEBUG] PBKDF2 GENERATE SECRET:"
                + "\n[DEBUG] -- text: " + text
                + "\n[DEBUG] -- salt: " + salt
                + "\n[DEBUG] -- iterations: " + iterations
                + "\n[DEBUG] -- keyLength: " + keyLength
                + "\n[DEBUG] -- instance: PBKDF2WithHmacSHA512 BCFIPS"
                + "\n[DEBUG] -- Chave derivada: " + derivedText
            );

            return sk;
        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
