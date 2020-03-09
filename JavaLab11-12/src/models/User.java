package models;

import java.io.Serializable;

public class User implements Serializable {
    private String Name;
    private String Password;
    private String Type;
    public String getName() {
        return Name;
    }
    public User(){
    }
    public User(String name, String password, String type){
        this.setName(name);
        this.setPassword(password);
        this.setType(type);
    }

    public void setName(String name) { this.Name = name; }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getType() {
        return Type;
    }
    public void setType(String type) {this.Type = type;}
    @Override
    public String toString(){
        if(this!=null){
            return "\nUser:\n" +
                    "Name: "+this.getName()+
                    "\nPassword: "+this.getPassword()+
                    "\nType:"+this.getType();
        }
        return "";
    }
}
