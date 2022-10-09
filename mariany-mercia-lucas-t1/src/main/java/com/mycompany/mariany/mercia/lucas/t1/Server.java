/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mariany.mercia.lucas.t1;

/**
 *
 * @author bacon
 */
public class Server {
    
    Database db = new Database();
    
    public void createUser(String name, String password){
        try {
            db.createUser(name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public User validateUser(String name, String password){
        try {
            return db.findUser(name, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean validateUserCode(User user, String code){
        return user.validadeCode(code);
    }
}
