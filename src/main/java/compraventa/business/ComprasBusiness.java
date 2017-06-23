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
				detalle.setProducto(orden.getProducto());
				detalle.setCantidad(orden.getCantidad());
				detalles.add(detalle);

				// incrementamos la existencia del producto
				Producto p = orden.getProducto();
				p.setExistencia(p.getExistencia() + orden.getCantidad());
				productosDAO.update(p);
			}
			dao.persist(compra);
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
				br.write(c.getId() + ";" + sdf.format(c.getFecha()) + ";" + c.getTotal());
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
}
