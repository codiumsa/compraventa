package compraventa;

import java.util.Date;

public class CompraBusiness {

	public static Compra comprar(Producto p, int cantidad) {
		if(cantidad < 0) {
			return null;
		}
		
		Compra compra = new Compra();
		compra.setProducto(p);
		compra.setCantidad(cantidad);
		compra.setFecha(new Date());
		p.setExistencia(p.getExistencia() + cantidad);
		return compra;
	}
}
