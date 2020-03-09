package worker.programmer;

import worker.Worker;

public class Junior extends Worker implements ProgrammerAction {
    public Junior(String lastName, String firstName, String secondName, int experience, Language language) {
        super(lastName, firstName, secondName, experience);
        this.language = language;

    }
    public Language language;
    @Override
    public void writeProgramm() {
        System.out.println("Write Progaamm");
    }

    @Override
    public void ficsBags() {
        System.out.println("Time for fixing bags- ");
    }

    @Override
    public int disutityProgrammer() {
        int disutity = (int) (0.2*this.experience*4);
        return disutity;
    }

    @Override
    public int getBonus() {
        int bonuses = (int) (this.salary*0.1);
        return bonuses;
    }

    @Override
    public String toString() {
        return  super.toString();
    }
}
