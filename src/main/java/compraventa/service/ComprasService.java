package compraventa.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonView;

import compraventa.dao.ComprasDAO;
import compraventa.model.Compra;
import compraventa.model.CompraDetalle;

@Path("compras")
public class ComprasService {

	@Inject
	ComprasDAO dao;

	private final static Logger LOGGER = Logger.getLogger(ComprasService.class.toString());

	/**
	 * Implementa el servicio para obtener un listado de compras.
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response all() {

		try {
			List<Compra> compras = dao.all();
			return Response.ok().entity(compras).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}

	/**
	 * Implementa el servicio para obtener un listado de detalles de la compra.
	 * 
	 * @return
	 */
	@GET
	@Path("{id}/detalles")
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response detalles(@PathParam("id") Long id) {

		try {
			List<CompraDetalle> detalles = dao.getDetalles(id);
			return Response.ok().entity(detalles).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
			return Response.serverError().build();
		}
	}
}