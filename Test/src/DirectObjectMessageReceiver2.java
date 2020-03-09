import javax.jms.*;
import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;

public class DirectObjectMessageReceiver2 {
    ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();
    JMSConsumer consumer;

    DirectObjectMessageReceiver2() {
        try (JMSContext context = factory.createContext("admin", "admin")) {
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");

            Destination ordersQueue = context.createQueue("TradingOrdersQueue");
            while (true) {
                System.out.println(context.createConsumer(ordersQueue).receiveBody
                        (Order.class));
            }
        }
        catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static void main(String[] args){
        new DirectObjectMessageReceiver2();
    }
}
