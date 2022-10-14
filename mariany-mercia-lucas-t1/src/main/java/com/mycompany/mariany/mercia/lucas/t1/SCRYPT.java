package com.mycompany.mariany.mercia.lucas.t1;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.fips.Scrypt;
import org.bouncycastle.util.Strings;
import org.bouncycastle.crypto.KDFCalculator;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class SCRYPT {
    
    private final int costParameter = 1024;
    private final int blocksize = 8;
    private final int parallelizationParam = 1;

    // Adaptado de https://downloads.bouncycastle.org/fips-java/BC-FJA-UserGuide-1.0.2.pdf
    private static byte[] useScryptKDF(
        char[] text,
        byte [] salt,
        int costParameter,
        int blocksize,
        int parallelizationParam
    ) {

        KDFCalculator<Scrypt.Parameters> calculator = new Scrypt.KDFFactory()
            .createKDFCalculator(Scrypt.ALGORITHM.using(
                salt,
                costParameter,
                blocksize,
                parallelizationParam,
                Strings.toUTF8ByteArray(text)
            ));

        byte[] output = new byte[32];
        calculator.generateBytes(output);
        return output;
    }
    
   
    public String createDerivedKey(String text, String salt){
        byte[] derivedKeyFromScrypt;
        derivedKeyFromScrypt = useScryptKDF(
            text.toCharArray(),
            salt.getBytes(), 
            this.costParameter,
            this.blocksize,
            this.parallelizationParam
        );

        String derivatedText = Hex.encodeHexString(derivedKeyFromScrypt);

        System.out.println(
            "[DEBUG] SCRYPT ENCRIPT:"
            + "\n[DEBUG] -- text: " + text
            + "\n[DEBUG] -- salt: " + salt
            + "\n[DEBUG] -- costParameter: " + this.costParameter
            + "\n[DEBUG] -- blocksize: " + this.blocksize
            + "\n[DEBUG] -- parallelizationParam: " + this.parallelizationParam
            + "\n[DEBUG] -- Chave derivada: " + derivatedText
        );

        return derivatedText;
    }
    

}
