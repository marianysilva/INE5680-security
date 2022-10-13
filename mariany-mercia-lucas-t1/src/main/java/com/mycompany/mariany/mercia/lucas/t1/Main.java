package com.mycompany.mariany.mercia.lucas.t1;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class Main {
    
    public static void main(String[] args) {
        
        UserClient client = new UserClient();
        
        
        /* ------------------- TESTES DO QUE JA TA PRONTO --------------------*/
        SCRYPT scrypt = new SCRYPT();
        GCM gcm = new GCM();
        PBKDF2 pbkdf2 = new PBKDF2();
        TwoFactorAuthenticator twoFA = new TwoFactorAuthenticator();
        Salt saltGenerator = new Salt();
          
        try {
            
            String name = "Mariany Ferreira da Silva (19200646)";
            String password = "2222222222";
            String salt = saltGenerator.getSalt(); // ok
    
            SecretKey secretKey = pbkdf2.createSecretKey(password, salt); // ok
             
            String textSCRYPTencrypt = scrypt.encrypt(name, salt); // ok
            String textSCRYPTdecrypt = scrypt.decrypt(textSCRYPTencrypt, salt); // to do
    
            String textGCMencrypt = gcm.encrypt(password, secretKey, salt); // ok
            String textGCMdecrypt = gcm.decrypt(textGCMencrypt, secretKey, salt); // ok
            
            String code = twoFA.getTOTPCode(secretKey); // ok
    
        } catch (Exception e) {
            e.printStackTrace();
        }
           
        /* -------------------------------------------------------------------*/

        int selectedOption = client.getMenuSelectedOption();
        while (selectedOption > 0 && selectedOption < 3) {
            
            UserServer server = new UserServer();
            String[] userInputs = client.getUserInputs();
            String name = userInputs[0];
            String password = userInputs[1];
            
            switch(selectedOption){
                case 1:
                    try {
                        String salt = saltGenerator.getSalt();
                        server.createUser(
                                name,
                                password,
                                salt
                        );
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }

                    break;
                case 2:
                    String code = server.validateUser(
                        name,
                        password
                    );
                                       
                    if (code != null) {
                        String codeInput = client.get2FACode(
                            code
                        );
                        
                        if (server.validateUserCode(codeInput)) {
                            client.loginSuccess();
                        } else {
                            client.userCodeError();
                        }
                    } else {
                        client.userNotFound();
                    }
                    break;
            }

            selectedOption = client.getMenuSelectedOption();
        }
    }
}
