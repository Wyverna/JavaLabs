package json_work;


import com.google.gson.Gson;
import director.Director;
import org.apache.log4j.Logger;
import worker.Worker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    private static Logger LOG = Logger.getLogger(JsonReader.class.toString());
    public static void parser(String path){
        BufferedReader bufread = null;
        Gson gson = new Gson();
        try {
            bufread = new BufferedReader(new FileReader(path));
            Director director = gson.fromJson(bufread, Director.class);
            if(director!=null){
                Worker workerBuf;
                for(Object worker: director.workers){
                    LOG.info(worker.toString());
                }
            }
        } catch (FileNotFoundException e) {
            LOG.error(e);
        }
        finally{
            if(bufread!=null){
                try {
                    bufread.close();
                } catch (IOException e) {
                    LOG.error(e);
                }
            }
        }
    }
}
