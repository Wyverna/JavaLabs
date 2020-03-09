
import javax.jms.*;

import com.sun.messaging.ConnectionFactory;
import com.sun.messaging.ConnectionConfiguration;

public class DirectMessageSender{
 public static void main(String[] args){

   ConnectionFactory factory;
	 
   factory = new com.sun.messaging.ConnectionFactory();  

	try( JMSContext context = factory.createContext("admin","admin")){

		factory.setProperty(ConnectionConfiguration.imqAddressList, "mq://127.0.0.1:7676,mq://127.0.0.1:7676");
		      Destination ordersTopic = context.createTopic("TradingOrdersTopic");
	          JMSProducer producer = context.createProducer();
	      
	          // Send msg to buy 200 shares of IBM at market price
		      TextMessage o=context.createTextMessage("IBM 200 Mkt(Priority 1)");
			  TextMessage co=context.createTextMessage("IBM 200 Mkt(Priority 9");
			  producer.setPriority(1);
	          producer.send(ordersTopic,o);
	          producer.setPriority(9);
	          producer.send(ordersTopic,co);
	          System.out.println("Placed an order to purchase 200 shares of IBM to TradingOrdersQueue");
	          
	          
	          
	          
	 } catch (JMSException e){
	           System.out.println("Error: " + e.getMessage());
	 } 
 }		
}
