package worker.programmer;

public class Middle extends Junior implements ProgrammerAction{

    public Middle(String lastName, String firstName, String secondName, int experience, Language language) {
        super(lastName, firstName, secondName, experience, language);
    }
    public String[] companies;
    @Override
    public int getBonus(){
        return (int)(this.salary*0.2);
    }
    public int disutityProgrammer(){
        return 0;
    }

    @Override
    public String toString() {
        return "Middle: "+ super.toString();
    }
}
