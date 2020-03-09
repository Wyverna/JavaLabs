package worker;

public class Admin extends Worker{

    public Admin(String lastName, String firstName, String secondName, int experience, int salary) {
        super(lastName, firstName, secondName, experience, salary);
    }
    public void saveReserveCopy()
    {
        System.out.println("saveReserveCopy");
    }
    public void configUpdating()
    {
        System.out.println("configUpdating");
    }
    public void createUpdateAccount()
    {
        System.out.println("createUpdateAccount");
    }
    @Override
    public void example(){
        System.out.println("createUpdateAccount");
    }
}
