package com.mycompany.mariany.mercia.lucas.t1;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class Main {
    
    public static void main(String[] args) {
        
        Client client = new Client();
        Server server = new Server();

        int selectedOption = client.getMenuSelectedOption();
        while (selectedOption > 0) {
            
            String[] userInputs = client.getUserInputs();    
            
            switch(selectedOption){
                case 1:
                    server.createUser(
                        userInputs[0],
                        userInputs[1]
                    );
                    break;
                case 2:
                    User user = server.validateUser(
                        userInputs[0],
                        userInputs[1]
                    );
                                       
                    if (user != null) {
                        String code = client.get2FACode(
                            user.getCode()
                        );
                        
                        if (server.validateUserCode(user, code)) {
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
    
    // cadastrar ou logar?
    // cadastra
    // login
    //  user, password
    //  2 factor authenticator
    //      gerar o TOTP  
    
//    
//    name, password = Client.getLogin()
//            
//    Server.login(name, password)
//    
//    if true 
//       Server.getTwoFactorAuth()
//       Server.validateToken()
//       if true
//            Client.successfullLogin()
//       else
//            Client.failLogin()
}
