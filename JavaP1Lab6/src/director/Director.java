package director;

import company.Company;
import org.apache.log4j.Logger;
import worker.Worker;

import java.util.ArrayList;

public class Director<T extends  Worker>{
    private static final Logger LOG = Logger.getLogger(Director.class.getName());
    public String name;
    private static Director ourInstance = new Director("Демосюк");
    public static Company company = new Company("IBR", "Belarus", 4);
    public Director(String name){
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
    public Director() {
    }
    public ArrayList<T> workers = new ArrayList<>();
    public void addWorker(Worker worker){
        getInstance().workers.add(worker);
    }
    public ArrayList<T> getWorkers(){
        return this.workers;
    }
    public void setWorkers(ArrayList<T> newWorkerList){
        this.workers = newWorkerList;
    }
    public int countWorkers(){
        return this.workers.size();
    }
    public void sortBySalary(){
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
