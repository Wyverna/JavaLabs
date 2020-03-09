package by.belstu.fit.isit.patsei.base.internet;

/**
 * Created by Наталья Пацей on 06.04.2016.
 */


import java.net.URL;
import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.net.MalformedURLException;
    import java.net.URL;
    public class ReadDocument {
        public static void main(String[] args) {
            URL bsu = null;
            String urlName = "http://www.tut.by";
            try {
                bsu  =  new URL(urlName);
            } catch (MalformedURLException e) {
// некорректно заданы протокол, доменное имя или путь к файлу
                e.printStackTrace();
            }
            if (bsu == null) {
                throw new RuntimeException();
            }
            try (BufferedReader d = new BufferedReader(new InputStreamReader(bsu.openStream())))
            {
            String line = "";
            while ((line = d.readLine()) != null) {
                System.out.println(line);
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
