package com.mycompany.mariany.mercia.lucas.t1;

import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.SecretKey;

/**
 * @author Lucas Nascimento Falcão (17100915)
 * @author Mariany Ferreira da Silva (19200646)
 * @author Mércia de Souza Maguerroski Castilho (18105221)
 */

public class User {
    protected String name;
    protected String password;
    protected String salt;
    
    protected SCRYPT scrypt = new SCRYPT();
    protected GCM gcm = new GCM();
    protected PBKDF2 pbkdf2 = new PBKDF2();
    protected TwoFactorAuthenticator twoFA = new TwoFactorAuthenticator();
    protected Salt saltGenerator = new Salt();
    
    public User(String name, String password, String salt) {
        this.name = name;
        this.password = password;
        this.salt = salt;
    }

    public User() {

    }

    private String getPlanTextPassword(){
        return gcm.decrypt(
            this.getPassword(),
            this.getSecretKey(), 
                this.getSalt()
        );
    }
    
    private SecretKey getSecretKey(){
        return pbkdf2.createSecretKey(
            this.getPlanTextPassword(),
            this.getSalt()
        );
    }
    
    public String getPlanTextName(){
        return scrypt.decrypt(
            this.getName(),
            this.getSalt()
        );
    }

    public String getName(){
        return this.name;
    }
    
    public String getPassword(){
        return this.password;
    }

    public String getSalt(){
        return this.salt;
    }
    
    public String getCode(){
        return this.twoFA.getTOTPCode(
            getSecretKey()
        );
    }
    
    public boolean validateName(String name){
        return name.equals(
            this.getPlanTextName()
        );
    }
    
    public boolean validatePassword(String password){
        return password.equals(
            this.getPlanTextPassword()
        );
    }
    
    public boolean validadeCode(String code){
        return code.equals(this.getCode());
    }
    
    public String encriptName(String name){
        return this.scrypt.encrypt(name, getSalt());
    }

    public String encriptPassword(String password){
        return this.gcm.encrypt(password, getSecretKey(), getSalt());
    }
    
    public String generateSalt(){
        try {
            return this.saltGenerator.getSalt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public String setName(String name){
        return this.encriptName(name);
    }
    
    public String setPassword(String password){
        return this.encriptPassword(password);
    }

    public String setSalt(){
        return this.generateSalt();
    }
}
