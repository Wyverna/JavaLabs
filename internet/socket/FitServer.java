package by.belstu.fit.isit.patsei.base.internet.socket;

/**
 * Created by Наталья Пацей on 06.04.2016.
 */

    import java.io.*;
    import java.net.*;
    import java.util.ArrayList;
    import java.util.List;


public class FitServer {
        public static void main(java.lang.String[] args) {
            ServerSocket serverSocket = null;
            Socket client = null;

            BufferedReader inbound = null;
            OutputStream outbound = null;

            List<String> gr3 = new ArrayList<String>();
            gr3.add("Raibchenko");
            gr3.add("Korovkin");
            gr3.add("Skorobogataia");
            gr3.add("Murkov");
            try
            {
                // Создаем server socket
                serverSocket = new ServerSocket(3000);

                System.out.println("Waiting for a student request...");
                while (true)
                {
                    // Ждем запрос
                    client = serverSocket.accept();

                    // Получаем поток
                    inbound=new BufferedReader(new
                            InputStreamReader(client.getInputStream()));
                    outbound = client.getOutputStream();

                    String symbol = inbound.readLine();

                    //Генерируем студента
                    String student= gr3.get((int)(Math.random()*3));
                    outbound.write(("\n The top student of "+symbol+
                            " is " + student + "\n").getBytes());

                    System.out.println("Request for " + symbol +
                            " has been processed - the top student of " + symbol+
                            " is " + student + "\n" );
                    outbound.write("End\n".getBytes());
                }
            }
            catch (IOException ioe) {
                System.out.println("Error in Server: " + ioe);
            }  finally{
                try{
                    inbound.close();
                    outbound.close();
                }catch(Exception e){
                    System.out.println("FitServer: can't close streams" + e.getMessage());
                }
            }
        }
    }

