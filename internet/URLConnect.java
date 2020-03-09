package by.belstu.fit.isit.patsei.base.internet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Наталья Пацей on 06.04.2016.
 */
public class URLConnect {


        public static void main(String[] args) {
            String urlName = "http://www.onliner.com";
            int timeout = 10_000;
            URL url;
            try {
                url = new URL(urlName);
                final URLConnection connection = url.openConnection();
// установка таймаута на соединение
                connection.setConnectTimeout(timeout);
                System.out.println(urlName + " content type: "+
                        "\n" + connection.getContentType()+ "\n" +connection.getClass()+
                        "\n" + connection.getContentLength());
// продолжение извлечения информации из соединения
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

}
