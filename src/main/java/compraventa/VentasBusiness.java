package compraventa;

import java.util.Date;

public class VentasBusiness {
	
	
	public static Venta vender(Producto p, int cantidad) {
		if(p.getExistencia() <= cantidad) {
			return null;
		}
		
		Venta venta = new Venta();
		venta.setProducto(p);
		venta.setCantidad(cantidad);
		venta.setFecha(new Date());
		
		p.setExistencia(p.getExistencia() - cantidad);
		return venta;
	}
}
