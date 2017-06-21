package compraventa.jms;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import compraventa.business.DemoComprasBusiness;

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

	@Inject
	DemoComprasBusiness demoComprasBusiness;

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message rcvMessage) {
		TextMessage msg = null;
		try {
			if (rcvMessage instanceof TextMessage) {
				msg = (TextMessage) rcvMessage;
				LOGGER.info("Comando recibido: " + msg.getText());
				processMsg(msg.getText());
			} else {
				LOGGER.warning("Mensaje de tipo incorrecto: " + rcvMessage.getClass().getName());
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error al procesar el mensaje", e.getCause());
		}
	}

	/**
	 * Se encarga de procesar los mensajes COMPRAR y VENDER.
	 * 
	 * @param command
	 */
	private void processMsg(String command) {
		switch (command) {
		case "COMPRAR":
			try {
				demoComprasBusiness.crearCompra();
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Error al registrar la compra", e.getCause());
			}
			break;
		case "VENDER":
			registrarVenta();
			break;
		default:
			LOGGER.warning("Comando inválido");
			break;
		}
	}

	/**
	 * Simula un proceso de ventas.
	 */
	void registrarVenta() {

	}
}