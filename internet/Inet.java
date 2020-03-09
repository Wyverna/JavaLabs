package by.belstu.fit.isit.patsei.base.internet;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Наталья Пацей on 06.04.2016.
 */
public class Inet {

        public static void main(String[] args) {
            InetAddress currentIP = null;
            InetAddress belstuIP = null;
            try {
                currentIP  =  InetAddress.getLocalHost();
// вывод IP-адреса локального компьютера
                System.out.println("IP -> " + currentIP.getHostAddress());
                belstuIP  =  InetAddress.getByName("www.belstu.by");
// вывод IP-адреса БГУ www.belstu.by
                System.out.println("BSTU -> " + belstuIP.getHostAddress());
            }  catch (UnknownHostException e) {
// если не удается найти IP
                System.err.println("адрес недоступен " + e);
            }

            //Определение доступа
            // задание IP-адреса в виде массива
            byte ip[] ={(byte)123, (byte)162, (byte)204, (byte)87};
            try {
                InetAddress addr = InetAddress.getByAddress("Unknow", ip);
                System.out.println(addr.getHostName()
                        + "-> cоединение:" + addr.isReachable(100));
            }  catch (UnknownHostException e) {
                System.err.println("адрес недоступен " + e);
            }  catch (IOException e) {
                System.err.println("ошибка  " + e);
            }



        }
    }



