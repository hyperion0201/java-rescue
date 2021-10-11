/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;

public class ReceiveQueue {
    public synchronized void onException(JMSException ex) {
        System.out.println("JMS Exception occured.  Shutting down client.");
    }
    
    public static void main(String argsp[])
    {
        try {
 
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            //connection.setExceptionListener(this);

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            //Destination destination = session.createQueue("myqueue2");
            Topic destination = session.createTopic("mytopic");

            // Create a MessageConsumer from the Session to the Topic or Queue
            MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(new MyMessageListener("Topic1"));
            /*
            do
            {
            // Wait for a message
            Message message = consumer.receive(1000);

            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Received: " + text);
            } else {
                System.out.println("Received: " + message);
            }
            if (message == null)
                break;
            }
            while (true);
            */
            
            //consumer.close();
            //session.close();
            //connection.close();
        } 
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
