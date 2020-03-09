package serialization;


import org.apache.log4j.Logger;
import worker.Worker;

import java.io.*;

public class MySerializator {
    private static Logger LOG = Logger.getLogger(MySerializator.class.toString());
    public static void serialization(Worker worker, String fileName) {
        boolean flag = false;
        File file = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(file)) {
            if (fos != null) {
                ObjectOutputStream ostream = new ObjectOutputStream(fos);
                ostream.writeObject(worker);
                flag = true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
