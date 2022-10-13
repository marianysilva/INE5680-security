package com.mycompany.mariany.mercia.lucas.t1;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.simple.parser.JSONParser;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

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
        JSONObject sampleObject = new JSONObject();
        JSONParser parser = new JSONParser();

        sampleObject.put("name", newUser.name);
        sampleObject.put("password", newUser.password);
        sampleObject.put("salt", newUser.salt);

        try {
            String userDir = System.getProperty("user.dir");
            JSONObject obj = (JSONObject) parser.parse(new FileReader(userDir + "/src/main/java/com/mycompany/mariany/mercia/lucas/t1/db.json"));
            JSONArray users = (JSONArray) obj.get("users");
            users.add(sampleObject);
            JSONObject mainObject = new JSONObject();
            mainObject.put("users", users);
            Files.write(Paths.get(userDir + "/src/main/java/com/mycompany/mariany/mercia/lucas/t1/db.json"), mainObject.toJSONString().getBytes());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    public JSONArray createJson() {
        JSONArray users = new JSONArray();

        JSONObject mainObj = new JSONObject();
        mainObj.put("users", users);

        return users;
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
