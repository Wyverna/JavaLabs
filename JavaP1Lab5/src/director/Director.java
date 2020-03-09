package director;

import com.google.gson.annotations.SerializedName;
import company.Company;
import org.apache.log4j.Logger;
import worker.Worker;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement(name="director")
@XmlAccessorType(XmlAccessType.FIELD)
public class Director<T extends  Worker> implements Serializable{
    private static final Logger LOG = Logger.getLogger(Director.class.getName());
    @XmlElement(name="name")
    private String name;
    private static Director ourInstance = new Director("Раскоша");
    public Company company = new Company("IBR", "Belarus", 4);
    public Director(String name){
        this.name = name;
        workers = new ArrayList<>();
    }
    public static Director getInstance() {
        return ourInstance;
    }

    public static class Secretary extends Worker{
        public Secretary(String lastName, String firstName, String secondName, int experience, int salary){
            super(lastName,firstName,secondName, experience, salary);
        }
        public void call(){
            System.out.println("Hello "+ this.firstName+" "+this.secondName+" is busy");
        }
    }
    private Director() {
        workers = new ArrayList<>();
    }
    @XmlElement(name="worker")
    @SerializedName("workers")
    public ArrayList<T> workers = null;
    public ArrayList<T> getWorkers() {
        return workers;
    }
    public void setWorkers(ArrayList<T> workers) {
        this.workers = workers;
    }

    public void addWorker(Worker worker){
        getInstance().workers.add(worker);
    }
    public int countWorkers()
    {
        return this.workers.size();
    }
    public void sortBySalary() {
    }
    private boolean checkWorkers(){
        if(this.workers.isEmpty())
            return false;
        return true;
    }
    public void printWorkers(){
        for (Worker worker: this.workers
             ) {
            System.out.println(worker.toString());
        }
    }
}
