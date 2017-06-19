package compraventa.jms;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import compraventa.business.ComprasBusiness;
import compraventa.dao.ProductosDAO;
import compraventa.model.OrdenCompra;
import compraventa.model.Producto;

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
	ComprasBusiness comprasBusiness;

	@Inject
	ProductosDAO productosDAO;

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
			registrarCompra();
			break;
		case "VENDER":
			registrarVenta();
		default:
			LOGGER.warning("Comando inválido");
			break;
		}
	}

	/**
	 * Simula un proceso de compras.
	 */
	void registrarCompra() {
		Producto producto = new Producto("BAN01", "Banana de oro");
		producto.setExistencia(10);
		producto.setPrecio(2000L);

		try {
			productosDAO.persist(producto);
			
			OrdenCompra oc = new OrdenCompra();
			oc.setProducto(producto);
			oc.setCantidad(20);
			List<OrdenCompra> ordenes = new ArrayList<>();
			ordenes.add(oc);
			
			comprasBusiness.comprar(ordenes);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Error al registrar la compra", e.getCause());
		}
	}

	/**
	 * Simula un proceso de ventas.
	 */
	void registrarVenta() {

	}
}