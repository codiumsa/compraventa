package compraventa.business;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import compraventa.dao.ComprasDAO;
import compraventa.exception.BusinessException;
import compraventa.model.Compra;
import compraventa.model.OrdenCompra;

/**
 * Clase que representa la lógica de negocios para el módulo de compras.
 * 
 * @author jorge
 */
@Stateless
public class ComprasBusiness {

	@Inject
	ComprasDAO dao;
	
	private final static Logger LOGGER = Logger.getLogger(ComprasBusiness.class.toString());

	public Compra comprar(List<OrdenCompra> ordenesCompras) throws BusinessException {
		LOGGER.info("Invocando CompraBusiness.comprar");
		return null;
	}
}
