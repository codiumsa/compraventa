package compraventa.jms;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Cliente simple para el queue {@link TextQueueMDB}.
 * 
 * @author jorge
 */
public class JMSClient {

	public static void main(String[] args) {
		try {

			final Properties jndiProperties = new Properties();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProperties.put("jboss.naming.client.ejb.context", true);
			
			InitialContext ic = new InitialContext(jndiProperties);
			ConnectionFactory cf = (ConnectionFactory) ic.lookup("jms/RemoteConnectionFactory");
			Queue textQueue = (Queue) ic.lookup("compraventa/queue/TextQueueMDB");
			Connection connection = cf.createConnection("demo", "demo");
			Session session = connection.createSession(Session.AUTO_ACKNOWLEDGE);
			MessageProducer producer = session.createProducer(textQueue);
			
			connection.start();
			TextMessage message = session.createTextMessage("Hola mundo!");
			producer.send(message);
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
