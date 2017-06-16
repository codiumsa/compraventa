package compraventa.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Venta {
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
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/y");
		long total = producto.getPrecio() * cantidad;
		return "Venta: " + producto.getNombre() + ", " + cantidad + " unidades. Total: " + total + " Gs. Fecha: " + sdf.format(fecha);
	}
}
