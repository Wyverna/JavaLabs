package Task1_1;

public class Main {
    public static final int StudentCOUNT = 20;
    public static final int DoorsSIZE = 1;

    public static void main(String[] args)
    {
        Doors Doors = new Doors(DoorsSIZE,StudentCOUNT);

        for (int i = 0; i < StudentCOUNT; i++)
        {
            (new Student(i+1, Doors)).start();
            try
            {
                Thread.sleep(100);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}