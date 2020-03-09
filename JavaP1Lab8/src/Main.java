import controllers.ItemProducerController;
import db.connection.ConnectionPool;
import models.Item;
import models.Producer;
import org.apache.log4j.Logger;

import java.sql.SQLException;


public class Main {
    private static Logger LOG = Logger.getLogger(Main.class.toString());
    public static void main(String[] args) throws SQLException, InterruptedException {
        ConnectionPool connectionPool = new ConnectionPool("jdbc:mysql://localhost:3306/Java8"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC", "root", "1111");
        Item item = new Item("Kiya", "1222", "2018-12-02", "Fix", 140);
        Producer producer = new Producer("Fix","Russia");
        ItemProducerController itemProducerController = new ItemProducerController(connectionPool);
        itemProducerController.getItemsWithCost(120);
        ItemProducerController itemProducerController1 = new ItemProducerController(connectionPool);
        itemProducerController1.getItemsWithCountry("Germany");
        ItemProducerController itemProducerController2 = new ItemProducerController(connectionPool);
        itemProducerController2.getItemsWithName("Dire");
        ItemProducerController itemProducerController3 = new ItemProducerController(connectionPool);
        itemProducerController3.getItemsWithYear(2014);
        connectionPool.closeConnection();
    }
}
