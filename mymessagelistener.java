
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class MyMessageListener implements MessageListener{
    private String consumerName;

	public MyMessageListener(String consumerName) {
		this.consumerName = consumerName;
	}

	synchronized public void onMessage(Message message) {
		TextMessage textMessage = (TextMessage) message;
		try {
                    /*
                    BytesMessage bytesXMLMessage = ((BytesMessage) message);
                    byte[] b = new byte[(int) bytesXMLMessage.getBodyLength()];
                    bytesXMLMessage.readBytes(b);
                    //Print Message received as String
                    System.out.println("Message received: "+new String(b));
                    //Get JMS type of message
                    System.out.println(bytesXMLMessage.getJMSType());
                    //message.acknowledge();
                    */
                    String text = textMessage.getText();
                    System.out.println("Message received: " + text);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
