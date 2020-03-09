package company;


import java.io.Serializable;

public class Company implements Serializable {
    public String name;
    public String country;
    public int countCourpuses;
    public Company(){
    }
    public Company(String name, String country, int countCourpuses) {
        this.name = name;
        this.country = country;
        this.countCourpuses = countCourpuses;
    }
}
