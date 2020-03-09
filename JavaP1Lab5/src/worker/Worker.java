package worker;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="worker", propOrder={
        "lastName",
        "firstName",
        "secondName",
        "experience",
        "salary"
})
public class Worker implements Serializable{
    @XmlElement(required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    public String lastName;
    @XmlElement(required = true)
    public String firstName;
    @XmlElement
    public String secondName;
    @XmlElement
    public int experience;
    @XmlElement()
    public int salary;
    public Worker(){

    }
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
