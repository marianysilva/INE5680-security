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
public class UserServer {
    
    Database db = new Database();
    User user = null;
    
    public void createUser(String name, String password, String salt){
        try {
            db.createUser(name, password, salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String validateUser(String name, String password){
        try {
            user = db.findUser(name, password);
            if (user != null) {
                return user.getCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean validateUserCode(String code){
        if (user == null){
            return false;
        }
        return user.validadeCode(code);
    }
}
