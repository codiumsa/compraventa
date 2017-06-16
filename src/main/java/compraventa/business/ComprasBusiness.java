package compraventa.business;

import java.util.List;

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

	public Compra comprar(List<OrdenCompra> ordenesCompras) throws BusinessException {
		// TODO
		return null;
	}
}
