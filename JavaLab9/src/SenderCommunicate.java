import java.io.*;
import java.net.*;
public class SenderCommunicate {
    public static void main(String[] args) throws IOException {

        try {
            new ClientCommunicate(args[0], Integer.parseInt(args[1]));
        }
        catch (Exception e) {
        System.out.println(e);
        }
        }
    }
class ClientConnectionCommunicateThread extends Thread {
    ClientCommunicate client;
    Socket socket;

    ClientConnectionCommunicateThread(ClientCommunicate client) {
        this.client = client;
    }

    public void run() {
        try {
            Socket socket = new Socket(client.server_address, ServerCommunicate.PORT);
            System.out.println("closing...");
            socket.close();
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}
class ClientCommunicate {
    public boolean alive = true;
    public String server_address;
    public int server_port;

    ClientCommunicate(String server_address, int server_port) {
        this.server_address = server_address;
        this.server_port = server_port;
        ClientConnectionCommunicateThread cct = new ClientConnectionCommunicateThread(this);
        cct.start();
    }
}