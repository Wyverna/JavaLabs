package Task1_1;

import java.util.ArrayDeque;

public class Doors {
    private ArrayDeque<Student> firstDoor;
    private ArrayDeque<Student> secondDoor;
    public int DoorsSize;
    static int countOfStudents = 0;
    static boolean selectDoor;

    public Doors(int size, int count)
    {
        this.DoorsSize = size;
        firstDoor = new ArrayDeque<Student>(count);
        secondDoor = new ArrayDeque<Student>(count);
    }

    public synchronized void DoorAway()
    {
        String line = new String();
        Student Student = null;
        Student = getStudent(selectDoor);
        if (Student == null) {
            line = "\nDon't have door";
        }
        else {
            line = Student.getStudentsName() + " is left";
            this.countOfStudents++;
        }
        System.out.println(line);
    }

    public synchronized void crossTheDoor() {
        showDoors();
        if ((this.countOfStudents >= this.DoorsSize || this.checkDoor(selectDoor)) && !this.checkDoor(!selectDoor))
        {
            selectDoor = !selectDoor;
            this.countOfStudents = 0;
        }
        System.out.println("count = " + this.countOfStudents + ", Door " + (selectDoor?1:2));
        DoorAway();
    }

    public synchronized Student getStudent(boolean flag)
    {
        if(flag)
        {
            return getStudentFromFirstDoor();
        }
        else
        {
            return getStudentFromSecondDoor();
        }
    }

    public synchronized boolean checkDoor(boolean flag)
    {
        if(flag)
        {
            return checkStudentInFirstDoor();
        }
        else
        {
            return checkStudentInSecondDoor();
        }
    }

    public synchronized void addStudentToFirstDoor(Student Student)
    {
        this.firstDoor.addLast(Student);
    }

    public synchronized void addStudentToSecondDoor(Student Student)
    {
        this.secondDoor.addLast(Student);
    }

    public synchronized Student getStudentFromFirstDoor()
    {
        if (!this.checkStudentInFirstDoor())
        {
            return this.firstDoor.pollFirst();
        }
        else
        {
            return null;
        }
    }

    public synchronized Student getStudentFromSecondDoor()
    {
        if (!this.checkStudentInSecondDoor())
        {
            return this.secondDoor.pollFirst();
        }
        else
        {
            return null;
        }
    }

    public synchronized boolean checkStudentInFirstDoor()
    {
        return this.firstDoor.isEmpty();
    }

    public synchronized boolean checkStudentInSecondDoor()
    {
        return this.secondDoor.isEmpty();
    }

    public synchronized void showDoors()
    {
        System.out.print("Doors places:\nFirst Door { ");
        for (Student c: this.firstDoor) {
            System.out.print(c.getStudentsName()+" ");
        }
        System.out.print("};\nSecond Door { ");
        for (Student c: this.secondDoor) {
            System.out.print(c.getStudentsName()+" ");
        }
        System.out.println("}");
    }
}