package by.belstu.fit.isit.patsei.base.internet.rmi;

/**
 * Created by Наталья Пацей on 07.04.2016.
 */

    import java.net.MalformedURLException;
    import java.rmi.RemoteException;
    import java.rmi.Naming;
    import java.rmi.registry.LocateRegistry;

    public class StartServer {

        public static void main (String args[]) {

            try {

               //  FitServerInmpl - bind it
                // регистрирует ITService
                FitServerImpl shiman = new FitServerImpl();
                Naming.rebind("rmi://localhost:1099/ITService",shiman);

                System.out.println("<ITService> server is ready.");

            }catch (MalformedURLException e1){
                System.out.println(e1.getMessage());
            }catch(RemoteException ex) {
        ex.printStackTrace();
        }
        }
        }


