package compraventa.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import compraventa.dao.ProductosDAO;

/**
 * Clase que representa el recurso "/demo" y los servicios que se exponen para
 * este recurso.
 * 
 * @author jorge
 *
 */
@Path("demo")
public class DemoService {

	@Inject
	ProductosDAO dao;

	private final static Logger LOGGER = Logger.getLogger(DemoService.class.toString());

	@GET
	@Produces("text/plain")
	public String hello() {
		return "Hello world!";
	}

	@GET
	@Path("productos")
	@Produces("application/json")
	public Response getProductos() {
		
		try {
			return Response.ok().entity(dao.all()).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}
}
