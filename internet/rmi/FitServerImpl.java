package by.belstu.fit.isit.patsei.base.internet.rmi;

/**
 * Created by Наталья Пацей on 07.04.2016.
 */
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

public class FitServerImpl extends UnicastRemoteObject  implements DecanatServer {
    private String mark=null;
    private ArrayList<String> stList = new ArrayList<String>();

    public FitServerImpl() throws RemoteException {
        super();
        LocateRegistry.createRegistry(1099);
        // Определение списка
        stList.add("GUZU");
        stList.add("SAMAL");
        stList.add("SMOLIK");
        stList.add("SHATILO");
        stList.add("ZELINSKI");
    }

    public String getMark(String symbol)
            throws RemoteException {

mark=null;
        if(stList.indexOf(symbol.toUpperCase()) != -1) {

            // Генерация оценки

            mark = String.valueOf(((int)(Math.random()*10)));
        }


            return mark;
    }

    @Override
    public List<String> getStList() throws RemoteException {
        return stList;
    }

}
