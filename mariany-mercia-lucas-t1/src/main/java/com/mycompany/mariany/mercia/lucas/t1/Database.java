package com.mycompany.mariany.mercia.lucas.t1;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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

    String userDir = System.getProperty("user.dir");
    JSONParser parser = new JSONParser();
    public void createUser(String name, String password, String salt) {
        User newUser = new User(name, password, salt);
        JSONObject sampleObject = new JSONObject();

        sampleObject.put("name", newUser.name);
        sampleObject.put("password", newUser.password);
        sampleObject.put("salt", newUser.salt);

        try {
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

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<User>();

        try {
            JSONObject obj = (JSONObject) parser.parse(new FileReader(userDir + "/src/main/java/com/mycompany/mariany/mercia/lucas/t1/db.json"));
            JSONArray usersArray = (JSONArray) obj.get("users");
            usersArray.get(0);
            for (Object user: usersArray ) {
                if (user instanceof JSONObject) {
                    User newUser = new User((String) ((JSONObject) user).get("name"),
                            (String) ((JSONObject) user).get("password"),
                            (String) ((JSONObject) user).get("salt"));
                    users.add(newUser);
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return users;
    }
 
    public User findUser(String name, String password){
        ArrayList<User> users = getUsers();
        
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
