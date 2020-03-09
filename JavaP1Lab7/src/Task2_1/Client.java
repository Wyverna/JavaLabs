package Task2_1;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Client extends Thread {
    private String name;
    private Restaraunt Restaran;
    private Semaphore semaphore;
    private int priority;
    private String age;
    public Client(Restaraunt pRestaraunt, Semaphore pSemaphore, int pName, int pPriority) {
        Random rand = new Random();
        this.name = String.valueOf(pName);
        this.semaphore = pSemaphore;
        this.Restaran = pRestaraunt;
        if(rand.nextBoolean())
        {
            this.priority = Thread.MAX_PRIORITY;
            this.age = "senior";
        }
        else{
            this.priority = Thread.MIN_PRIORITY;
            this.age = "young";
        }
    }

    public String getClientName() {
        return this.name + ", age " + this.age;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int waitingTime = rand.nextInt(50000) + 100;
        System.out.println("Client " + this.getClientName() + " coming..");

        try {
            if (semaphore.tryAcquire(waitingTime, TimeUnit.MICROSECONDS)) {
                System.out.println("Client " + this.getClientName() + " have a dialog");
                Restaran.connect(this);
                Restaran.showLines();
                Thread.sleep(rand.nextInt(1000) + 500);
                Restaran.disconnect(this);
                semaphore.release();
                System.out.println("Client " + this.getClientName() + " end dialog");
            } else {
                System.out.println("Client " + this.getClientName() + " left");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
