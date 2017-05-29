package compraventa;

import java.util.Date;

import exceptions.StockMinimoException;

/**
 * Clase que encapsula la lógica de ventas.
 * 
 * @author jorge
 */
public class VentasBusiness {
	
	
	/**
	 * Define la lógica de ventas.
	 * 
	 * @param p
	 * @param cantidad
	 * @return
	 * @throws StockMinimoException
	 */
	public static Venta vender(Producto p, int cantidad) throws StockMinimoException {
		if(p.getExistencia() <= cantidad) {
			throw new StockMinimoException();
		}
		
		Venta venta = new Venta();
		venta.setProducto(p);
		venta.setCantidad(cantidad);
		venta.setFecha(new Date());
		
		p.setExistencia(p.getExistencia() - cantidad);
		return venta;
	}
}
