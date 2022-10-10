package com.mycompany.mariany.mercia.lucas.t1;

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
            
            String text = "Mariany";
            String salt = saltGenerator.getSalt();
     
            String textGCMencrypt = gcm.encrypt(text, salt); // to do
            String textGCMdecrypt = gcm.decrypt(textGCMencrypt, salt); // to do
     
            String textSCRYPTencrypt = scrypt.encrypt(text, salt); // ok
            String textSCRYPTdecrypt = scrypt.decrypt(textSCRYPTencrypt, salt); // to do
            
            String secretKey = pbkdf2.createSecretKey(text, salt); // ok
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
                    server.createUser(
                        name,
                        password
                    );
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
