package compraventa.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonView;

import compraventa.dao.ProductosDAO;
import compraventa.model.Producto;

@Path("productos")
public class ProductosService {

	@Inject
	ProductosDAO dao;

	private final static Logger LOGGER = Logger.getLogger(DemoService.class.toString());

	/**
	 * Implementa el servicio para obtener un listado de productos.
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response all() {

		try {
			List<Producto> productos = dao.all();
			return Response.ok().entity(productos).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}

	/**
	 * Implementa el servicio que permite crear un nuevo producto.
	 * 
	 * @param producto
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response create(@JsonView(View.Public.class) Producto producto) {

		try {
			dao.persist(producto);
			return Response.ok().entity(producto).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}

	/**
	 * Implementa el servicio que permite obtener un producto a partir de su ID.
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response getOne(@PathParam("id") Long id) {

		try {
			Producto producto = dao.find(id);
			return Response.ok().entity(producto).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}

	/**
	 * Implementa el servicio que permite actualizar información de un producto.
	 * 
	 * @param producto
	 * @return
	 */
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response update(@JsonView(View.Public.class) Producto producto) {

		try {
			Producto updated = dao.update(producto);
			return Response.ok().entity(updated).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}

	/**
	 * Implementa el servicio que permite eliminar un producto.
	 * 
	 * @param id
	 * @return
	 */
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response delete(@PathParam("id") Long id) {

		try {
			dao.remove(id);
			return Response.ok().build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}
}
