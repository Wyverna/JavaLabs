package json_work;

import com.google.gson.Gson;
import director.Director;
import org.apache.log4j.Logger;

import java.io.OutputStreamWriter;

public class JsonGenerator {
    private static Logger LOG = Logger.getLogger(JsonGenerator.class.toString());
    public static void serialize(Director director, String path){
        Gson json = new Gson();
        String result = json.toJson(director);
        LOG.info(result);
    }
}
