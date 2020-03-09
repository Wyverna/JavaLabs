package Task1_1;

import java.util.Random;

public class Student extends Thread {
    public String StudentsName;
    public Doors Doors;
    Random random = new Random();

    public Student(int pStudentName, Doors pDoors)
    {
        this.StudentsName = String.valueOf(pStudentName);
        this.Doors = pDoors;
    }

    @Override
    public void run()
    {
        synchronized (Doors) {
            boolean flag = random.nextBoolean();
            if (flag) {
                Doors.addStudentToFirstDoor(this);
            }
            else {
                Doors.addStudentToSecondDoor(this);
            }
            System.out.println("Student " + this.getStudentsName() + " is on " + (flag?1:2) + " Door");
            try
            {
                Doors.wait(1500);
                Doors.crossTheDoor();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStudentsName(){
        return this.StudentsName;
    }
}
