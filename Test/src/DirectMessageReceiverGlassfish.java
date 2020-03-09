import javax.annotation.Resource;
import javax.jms.*;
import javax.jms.MessageListener;
import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;

public class DirectMessageReceiverGlassfish implements MessageListener {
            ConnectionFactory factory = new com.sun.messaging.ConnectionFactory();;
            Destination ordersQueue;
    JMSConsumer consumer;
    public DirectMessageReceiverGlassfish(){
        try(
                JMSContext context = factory.createContext("admin","admin")){
            factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
            ordersQueue = context.createQueue("TradingOrderQueue");
            consumer = context.createConsumer(ordersQueue);
            consumer.setMessageListener(this);
            System.out.println("Listening to the TradingOrdersTopic...");
            // Keep the program running - wait for messages
            while(true) {
                Thread.sleep(100000);
            }

        }  catch (JMSException e) {
            System.out.println("Error: " + e.getMessage());
        }
        catch (InterruptedException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void onMessage(Message msg){
        try{
            System.out.println("Got the text message from the TradingOrdersTopic: " +
                    msg.getBody(String.class));

            System.out.println("\n === Here's what toString() on the message prints \n" + msg);
        } catch (JMSException e){
            System.err.println("JMSException: " + e.toString());
        }
    }

    public static void main(String[] args){
        new DirectMessageReceiverGlassfish();
    }
}

