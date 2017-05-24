package compraventa;

import java.util.Date;

/**
 * Esta es la entidad que represneta a las compras.
 * 
 * @author jorge
 *
 */
public class Compra {
	private Producto producto;
	private Date fecha;
	private Integer cantidad;
	
	
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	
	
}

