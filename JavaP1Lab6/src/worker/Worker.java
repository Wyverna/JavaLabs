package worker;

public class Worker {
    public String lastName;
    public String firstName;
    public String secondName;
    public int experience;
    public int salary;

    public Worker(String lastName, String firstName, String secondName, int experience) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.secondName = secondName;
        this.experience = experience;
    }

    public Worker(String lastName, String firstName, String secondName, int experience, int salary) {
        this(lastName, firstName, secondName, experience);
        this.salary = salary;
    }

    @Override
    public String toString() {
        return lastName+" "+firstName+" "+secondName+" experience = "+this.experience;
    }
    protected  void example(){
        System.out.println("Magic");
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
