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

public class SendQueue {
    public static void main(String args[])
    {
        try
        {
            // Create a ConnectionFactory
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create a Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();
            
            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            //Destination destination = session.createQueue("myqueue2");
            Destination destination = session.createTopic("doc-tin-nhan");

            // Create a MessageProducer from the Session to the Topic or Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            for (int i=0; i< 200; i++)
            {
            // Create a messages
            String text = "Hello world! " + i;
            TextMessage message = session.createTextMessage(text);
            //message.set

            // Tell the producer to send the message
            System.out.println("Sent message: "+ text);
            producer.send(message);
            Thread.sleep(10);
            }
            // Clean up
            session.close();
            connection.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
