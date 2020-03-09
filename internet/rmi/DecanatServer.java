package by.belstu.fit.isit.patsei.base.internet.rmi;

/**
 * Created by Наталья Пацей on 07.04.2016.
 */


    import java.rmi.Remote;
    import java.rmi.RemoteException;
    import java.util.List;

    public interface DecanatServer extends Remote {

        public String getMark(String symbol) throws
                RemoteException;

        public List<String> getStList()  throws
                RemoteException;

    }



