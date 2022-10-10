package com.mycompany.mariany.mercia.lucas.t1;


/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class GCM {
    private static final int MAC_SIZE = 128; // in bits
    // AES-GCM parameters
    private static final int AES_KEY_SIZE = 128; // in bits
    private static final int GCM_NONCE_LENGTH = 12; // in bytes
    private static final int GCM_TAG_LENGTH = 16; // in bytes
    
    public String encrypt(String text, String salt){
        String derivatedText = "-----";

        System.out.println(
            "[DEBUG] GCM ENCRIPT:"
            + "\n[DEBUG] text: " + text
            + "\n[DEBUG] salt: " + salt
            + "\n[DEBUG] Chave derivada: " + derivatedText
        );

        return derivatedText;
    }
    
        
    public String decrypt(String derivatedText, String salt){
        String text = "-----";

        System.out.println(
            "[DEBUG] GCM DECRIPT:"
            + "\n[DEBUG] derivatedText: " + derivatedText
            + "\n[DEBUG] salt: " + salt
            + "\n[DEBUG] text: " + text
        );

        return text;
    }
}
