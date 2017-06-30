package compraventa.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import compraventa.dao.ProductosDAO;
import compraventa.model.Producto;

@Path("productos")
public class ProductosService {
	
	@Inject
	ProductosDAO dao;

	private final static Logger LOGGER = Logger.getLogger(DemoService.class.toString());

	@GET
	@Produces("application/json")
	public Response all() {

		try {
			List<Producto> productos = dao.all();
			return Response.ok().entity(productos).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}
}
