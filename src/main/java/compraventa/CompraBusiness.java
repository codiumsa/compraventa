package compraventa;

import java.util.Date;

import compraventa.exceptions.CantidadNegativaException;

public class CompraBusiness {

	public static Compra comprar(Producto p, int cantidad) throws CantidadNegativaException {
		if(cantidad < 0) {
			throw new CantidadNegativaException();
		}
		
		Compra compra = new Compra();
		compra.setProducto(p);
		compra.setCantidad(cantidad);
		compra.setFecha(new Date());
		p.setExistencia(p.getExistencia() + cantidad);
		return compra;
	}
}
