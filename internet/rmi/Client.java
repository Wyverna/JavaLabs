package by.belstu.fit.isit.patsei.base.internet.rmi;

/**
 * Created by Наталья Пацей on 07.04.2016.
 */


    import java.net.MalformedURLException;
    import java.rmi.*;
    import java.util.Scanner;


public class Client {

        public static void main (String args[]) {

               try {
                       DecanatServer myServer = (DecanatServer)
                        Naming.lookup("rmi://localhost:1099/ITService");

                   Scanner scan = new Scanner(System.in);
                   String student = scan.nextLine();
                   String ball = myServer.getMark(String.valueOf(student));
                if  (ball != null){
                    System.out.println("The score of " + student +
                            " is:" + ball);
                }
                else{
                    System.out.println("Invalid FIO. " +
                            "Please use one of these:" +
                            myServer.getStList().toString());
                }

            } catch (MalformedURLException ex) {
                System.out.println(ex.getMessage());
            }catch (RemoteException ex2) {
                System.out.println(ex2.getMessage());
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    }





