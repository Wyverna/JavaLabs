package by.belstu.fit.isit.patsei.base.internet.socket;

/**
 * Created by Наталья Пацей on 07.04.2016.
 */

    import java.io.*;
    import java.net.*;
    import java.util.Scanner;

public class Client {
        public static void main(java.lang.String[] args) {



            Socket clientSocket = null;
            try{
                // Открыть клиентское socket connection
                clientSocket = new Socket("localhost", 3000);
                System.out.println("Client: " + clientSocket);
            }catch (UnknownHostException uhe){
                System.out.println("UnknownHostException: " + uhe);
            } catch (IOException ioe){
                System.err.println("IOException: " + ioe);
            }

            try (OutputStream outbound = clientSocket.getOutputStream();
                 BufferedReader inbound = new  BufferedReader(new
                         InputStreamReader(clientSocket.getInputStream()));  ){
                Scanner scan = new Scanner(System.in);
                int group = scan.nextInt();
                // Послать символ серверу
                outbound.write((group+"\n").getBytes());

                String topStudent;
                while (true){
                    topStudent = inbound.readLine();
                    if (topStudent.length() == 0) continue;

                    if (topStudent.equals("End")){
                        break;
                    }
                    System.out.println("Got the student for " + group+":" + topStudent);
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
    }

