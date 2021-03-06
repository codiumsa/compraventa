package compraventa.business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;

import compraventa.dao.ComprasDAO;
import compraventa.dao.ProductosDAO;
import compraventa.exception.BusinessException;
import compraventa.exception.CantidadNegativaException;
import compraventa.model.Compra;
import compraventa.model.CompraDetalle;
import compraventa.model.OrdenCompra;
import compraventa.model.Producto;

/**
 * Clase que representa la lógica de negocios para el módulo de compras.
 * 
 * @author jorge
 */
@Stateless
public class ComprasBusiness {

	@Inject
	ComprasDAO dao;

	@Inject
	ProductosDAO productosDAO;

	private final static Logger LOGGER = Logger.getLogger(ComprasBusiness.class.toString());

	/**
	 * Implementa la lógica de compras.
	 * 
	 * @param ordenesCompras
	 * @return
	 * @throws BusinessException
	 */
	public Compra comprar(List<OrdenCompra> ordenesCompras) throws BusinessException {
		LOGGER.info("[BE] Proceso CompraBusiness.comprar iniciando");

		if (ordenesCompras == null) {
			LOGGER.warning("Invocando CompraBusiness.comprar con ordenesCompras NULL");
			return Compra.NULL;
		}

		if (ordenesCompras.isEmpty()) {
			LOGGER.warning("Invocando CompraBusiness.comprar con ordenesCompras vacío");
			return Compra.NULL;
		}
		Compra compra = new Compra();
		List<CompraDetalle> detalles = new ArrayList<>();
		compra.setDetalles(detalles);
		compra.setFecha(new Date());

		try {
			for (OrdenCompra orden : ordenesCompras) {

				if (orden.getCantidad() < 0) {
					throw new CantidadNegativaException();
				}
				// creamos el detalle de la compra
				CompraDetalle detalle = new CompraDetalle();
				detalle.setCompra(compra);
				Producto producto = productosDAO.find(orden.getProducto().getId());
				detalle.setProducto(producto);
				detalle.setCantidad(orden.getCantidad());
				detalles.add(detalle);

				// incrementamos la existencia del productos. No hace falta
				// utilizar el método update del dao de productos, porque la
				// instancia producto se encuentra en el contexto de
				// persistencia.
				producto.setExistencia(producto.getExistencia() + orden.getCantidad());
			}
			dao.persist(compra);
			compra.setTotal(getTotal(compra));
			LOGGER.info("[BE] Proceso CompraBusiness.comprar finalizado");
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return compra;
	}

	/**
	 * Implementa la lógica de exportación de registros de compra a formato CSV
	 * a la fecha dada como parámetro.
	 * 
	 * @param fecha
	 * @throws BusinessException
	 */
	public void exportar(Date fecha) throws BusinessException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			FileWriter writer = new FileWriter("/tmp/compras.csv");
			BufferedWriter br = new BufferedWriter(writer);
			List<Compra> compras = dao.allBeforeDate(fecha);

			for (Compra c : compras) {
				br.write(c.getId() + ";" + sdf.format(c.getFecha()) + ";" + getTotal(c));
				br.newLine();

				for (CompraDetalle d : c.getDetalles()) {
					br.write(d.getId() + ";" + d.getProducto().getCodigo() + ";" + d.getCantidad() + ";"
							+ d.getProducto().getPrecio());
					br.newLine();
				}
			}
			br.close();
			writer.close();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Retorna todas las compras registradas en el sistema.
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public List<Compra> all() throws BusinessException {
		try {
			List<Compra> compras = dao.all();

			for (Compra c : compras) {
				c.setTotal(getTotal(c));
			}
			return compras;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Retorna todas las compras registradas en el sistema para el rango dado
	 * como parámetro.
	 * 
	 * @param desde
	 *            Fecha de inicio
	 * @param hasta
	 *            Fecha fin. Si se pasa un valor nulo, se asume la fecha actual.
	 * @return
	 * @throws BusinessException
	 */
	public List<Compra> allInDateRange(Date desde, Date hasta) throws BusinessException {
		if (hasta == null) {
			hasta = new Date();
		}

		try {
			List<Compra> compras = dao.allInDateRange(desde, hasta);

			for (Compra c : compras) {
				c.setTotal(getTotal(c));
			}
			return compras;
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Retorna los detalles de la compra dada como parámetro.
	 * 
	 * @param compraId
	 * @return
	 * @throws BusinessException
	 */
	public List<CompraDetalle> getDetalles(Long compraId) throws BusinessException {
		try {
			return dao.getDetalles(compraId);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	/**
	 * Calcula el total de la compra.
	 * 
	 * @param compra
	 *            Compra que se encuentra en el contexto de persistencia
	 * @return
	 * @throws BusinessException
	 */
	public Long getTotal(Compra compra) throws BusinessException {
		long total = 0;

		for (CompraDetalle d : compra.getDetalles()) {
			total += d.getCantidad() * d.getProducto().getPrecio();
		}
		return total;
	}

	/**
	 * Calcula el total de la compra.
	 * 
	 * @param compraId
	 *            Identificador de la compra
	 * @return
	 * @throws BusinessException
	 */
	public Long getTotal(Long compraId) throws BusinessException {
		try {
			return getTotal(dao.find(compraId));
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
}
