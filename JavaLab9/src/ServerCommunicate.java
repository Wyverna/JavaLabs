import java.io.*;
import java.net.*;
import java.util.*;
    public class ServerCommunicate {
        // Выбираем порт вне пределов 1-1024:
        public static final int PORT = 5080;

        public static void main(String[] args) throws IOException {
            ServerSocket s = new ServerSocket(PORT);
            System.out.println(s.getInetAddress());
            System.out.println("Started: " + s);
            try {
                // Блокирует до тех пор, пока не возникнет соединение:
                while(true) {
                    new HttpConnect(s.accept());
                }
            }
            finally {
                s.close();
            }
        }
    }
class HttpConnect extends Thread{

    public Socket sock;

    HttpConnect(Socket s) {

        sock = s;

        setPriority(NORM_PRIORITY - 1);

        start () ;
        }
        public void run()
        {
            try {
                System.out.println("Connection accepted: " + sock);
                System.out.println("adress: " + sock.getInetAddress());
            }
            finally {
                System.out.println("closing " + sock.getInetAddress());
                try {
                    sock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

