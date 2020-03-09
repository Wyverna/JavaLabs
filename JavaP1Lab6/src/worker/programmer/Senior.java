package worker.programmer;

public class Senior extends Junior{
    public boolean teamLead;

    public Senior(String lastName, String firstName, String secondName, int experience, Language language) {
        super(lastName, firstName, secondName, experience, language);
    }

    @Override
    public void writeProgramm(){
        System.out.println("Override method in Senior class implements ProgrammerAction");
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
