package compraventa.business;

import javax.ejb.Remote;

import compraventa.exception.BusinessException;

/**
 * Interfaz remota para el EJB {@link DemoComprasBusiness}
 * 
 * @author jorge
 *
 */
@Remote
public interface DemoComprasBusinessRemote {

	void crearCompra() throws BusinessException;
}
