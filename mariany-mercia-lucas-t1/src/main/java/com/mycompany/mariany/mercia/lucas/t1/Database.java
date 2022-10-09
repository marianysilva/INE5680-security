package com.mycompany.mariany.mercia.lucas.t1;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class Database {
    
    /*
        QUANDO CRIAR USUARIOS PRA GUARDAR NO JSON
            TEMOS QUE CIFRAR NAME, PASSWORD E CRIAR O SALT
        QUANDO RECUPERARMOS OS USUÁRIOS DO JSON
            TEMOS QUE MANTER O QUE ESTÁ CIFRADO NO JSON
    */
    public void createUser(String name, String password) {
        User newUser = new User(name, password, null);
        /* ADICIONAR O NOVO USUARIO NO ARQUIVO JSON */
    }

    public User[] getUsers() {
        User[] users = new User[] {};
        /*  PEGAR TODOS OS USUARIOS NO ARQUIVO JSON
            TRANSFORMAR EM ARRAY DE USERS
        */
        return users;
    }
 
    public User findUser(String name, String password){
        User[] users = getUsers();
        
        for (User user : users){
            if(
                user.validateName(name)
                && user.validatePassword(password)
            ){
                return user;
            }
        }
        return null;
    }
}
