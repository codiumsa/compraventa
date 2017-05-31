package compraventa;

import java.util.Date;

import compraventa.reflection.Positivo;

public class Venta {
	private Producto producto;
	private Date fecha;
	
	@Positivo
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
		return "Venta: " + producto.getNombre() + ", " + cantidad + " unidades " + fecha;
	}
	
	public void metodoInterno() {
		
	}
}
