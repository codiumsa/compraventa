package compraventa.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Clase que representa el recurso "/demo" y los servicios que se exponen para
 * este recurso.
 * 
 * @author jorge
 *
 */
@Path("demo")
public class DemoService {
	
	@GET
	@Produces("text/plain")
	public String hello(@QueryParam("nombre") String nombre) {
		return "Hello, " + nombre;
	}
}
