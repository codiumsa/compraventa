package compraventa.jms;

import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * <p>
 * Simple Message Driven Bean que recibe y procesa mensajes de forma asíncrona.
 * Los mensajes se reciben en una cola.
 * </p>
 * 
 * @author jorge
 * 
 */
@MessageDriven(name = "TextQueueMDB", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "compraventa/queue/TextQueueMDB"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class TextQueueMDB implements MessageListener {

	private final static Logger LOGGER = Logger.getLogger(TextQueueMDB.class.toString());

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				LOGGER.info("Mensaje recibido: " + msg.getText());
			} else {
				LOGGER.warning("Mensaje de tipo incorrecto: " + rcvMessage.getClass().getName());
			}
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
}