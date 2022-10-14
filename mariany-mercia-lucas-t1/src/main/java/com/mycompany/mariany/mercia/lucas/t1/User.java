package com.mycompany.mariany.mercia.lucas.t1;

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
    
    public User(String name, String password) {
        this.salt = this.generateSalt();
        this.name = setName(name);
        this.password = setPassword(password);
    }

    public User(String name, String password, String salt) {
        this.salt = salt;
        this.name = name;
        this.password = password;
    }

    private String getPlanTextPassword(String password){
        return gcm.decrypt(
                this.getPassword(),
                this.getSecretKey(password),
                getSalt(),
                getName()
        );
    }
    
    private SecretKey getSecretKey(String password){
        return pbkdf2.createSecretKey(
                password,
            this.getSalt()
        );
    }
    private SecretKey generateSecretKey(String password){
        return pbkdf2.createSecretKey(
                password,
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
    
    public String getCode(String password){
        return this.twoFA.getTOTPCode(
            getSecretKey(password)
        );
    }
    
    public boolean validateName(String name){
        return getName().equals(scrypt.createDerivedKey(name, getSalt()));
    }
    
    public boolean validatePassword(String password){
        return password.equals(
            this.getPlanTextPassword(password)
        );
    }
    
    public boolean validadeCode(String code, String password){
        String text = this.getCode(password);
        return code.equals(text);
    }
    
    public String encriptName(String name){
        return this.scrypt.createDerivedKey(name, getSalt());
    }

    public String encriptPassword(String password){
        return this.gcm.encrypt(password, generateSecretKey(password), getSalt(), getName());
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
