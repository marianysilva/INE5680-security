package com.mycompany.mariany.mercia.lucas.t1;

import org.apache.commons.codec.binary.Hex;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class Salt {
    public String getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        
        String derivatedText = Hex.encodeHexString(salt);

        System.out.println("[DEBUG] Chave derivada usando SALT: ");
        System.out.println(derivatedText);

        return derivatedText;
    }
}
