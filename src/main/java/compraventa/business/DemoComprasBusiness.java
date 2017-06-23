package compraventa.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import compraventa.exception.BusinessException;
import compraventa.model.OrdenCompra;
import compraventa.model.Producto;

/**
 * Clase que implementa una prueba de la lógica de compras. Además, se muestra
 * el usa {@link Instance} para poder inyectar una instancia de la misma clase,
 * para poder invocar de forma correcta al método crearProducto.
 * 
 * Se implementa la interfaz {@link DemoComprasBusinessRemote} para poder
 * disponibilizar el EJB a clientes remotos, ejemplo {@link EJBClient}.
 * 
 * @author jorge
 */
@Stateless
@LocalBean
public class DemoComprasBusiness implements DemoComprasBusinessRemote {

	@Inject
	EntityManager em;

	@Inject
	ComprasBusiness comprasBusiness;

	// esta referencia nos permite invocar a métodos de ésta clase, siempre
	// pasando por el contenedor.
	@Inject
	Instance<DemoComprasBusiness> selfBean;

	/**
	 * El método se encarga de crear un producto en la base de datos. Se marca
	 * como REQUIRES_NEW su transacción para que se cree siempre en una nueva
	 * transacción.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public Producto crearProducto() throws BusinessException {
		Producto producto = new Producto("BAN01", "Banana de Oro");
		producto.setExistencia(10);
		producto.setPrecio(2000L);

		try {
			em.persist(producto);
			return producto;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Se encarga de crear un registro de compra.
	 */
	public void crearCompra() throws BusinessException {
		try {
			// si acá hacemos this.crearProducto() la lógica de crearProducto va
			// a formar parte de la transacción iniciada por crearCompra y no de
			// lo que definimos con el @TransactionAttribute.
			Producto producto = selfBean.get().crearProducto();

			OrdenCompra oc = new OrdenCompra();
			oc.setProducto(producto);
			oc.setCantidad(20);
			List<OrdenCompra> ordenes = new ArrayList<>();
			ordenes.add(oc);

			comprasBusiness.comprar(ordenes);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void exportar(Date fecha) throws BusinessException {
		comprasBusiness.exportar(fecha);
	}
}
