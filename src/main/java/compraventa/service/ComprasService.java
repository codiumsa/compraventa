package compraventa.service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.annotation.JsonView;

import compraventa.business.ComprasBusiness;
import compraventa.exception.BusinessException;
import compraventa.model.Compra;
import compraventa.model.CompraDetalle;
import compraventa.model.OrdenCompra;

@Path("compras")
public class ComprasService {

	@Inject
	ComprasBusiness business;

	private final static Logger LOGGER = Logger.getLogger(ComprasService.class.toString());

	/**
	 * Implementa el servicio para obtener un listado de compras.
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response all(@QueryParam("desde") final String desde, @QueryParam("hasta") final String hasta) {

		try {
			List<Compra> compras;

			if (desde != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				if (hasta != null) {
					compras = business.allInDateRange(sdf.parse(desde), sdf.parse(hasta));
				} else {
					compras = business.allInDateRange(sdf.parse(desde), null);
				}

			} else {
				compras = business.all();
			}
			return Response.ok().entity(compras).build();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage() + "\n" + e.getCause().getMessage());
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
			List<CompraDetalle> detalles = business.getDetalles(id);
			return Response.ok().entity(detalles).build();
		} catch (BusinessException e) {
			LOGGER.log(Level.SEVERE, e.getMessage() + "\n" + e.getCause().getMessage());
			return Response.serverError().build();
		}
	}

	/**
	 * Implementa el servicio para registrar una compra.
	 * 
	 * @param ordenes
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@JsonView(View.Public.class)
	public Response comprar(@JsonView(View.Public.class) List<OrdenCompra> ordenes) {
		try {
			Compra compra = business.comprar(ordenes);
			return Response.ok().entity(compra).build();
		} catch (BusinessException e) {
			LOGGER.log(Level.SEVERE, e.getMessage() + "\n" + e.getCause().getMessage());
			return Response.serverError().build();
		}
	}
}
