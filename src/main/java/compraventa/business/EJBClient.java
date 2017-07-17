package compraventa.business;

import java.util.Date;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Esta clase muestra un ejemplo de cómo invocar al EJB
 * {@link DemoComprasBusiness} utilizando su interfaz remota.
 * 
 * @author jorge
 */
public class EJBClient {
	
	public static void main(String[] args) {
		final Properties jndiProperties = new Properties();
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://0.0.0.0:8080");
		jndiProperties.put("jboss.naming.client.ejb.context", true);

		try {
			InitialContext ic = new InitialContext(jndiProperties);
			DemoComprasBusinessRemote demoComprasBusiness = (DemoComprasBusinessRemote) ic
					.lookup("compraventa/DemoComprasBusiness!compraventa.business.DemoComprasBusinessRemote");
			demoComprasBusiness.crearCompra();
			demoComprasBusiness.exportar(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
