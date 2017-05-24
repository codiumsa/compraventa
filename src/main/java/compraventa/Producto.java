package compraventa;

public class Producto {
	private String nombre;
	private Integer existencia = 0;
	
	public Producto() {
	}
	
	public Producto(String nombre, Integer existencia) {
		this.nombre = nombre;
		this.existencia = existencia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getExistencia() {
		return existencia;
	}
	public void setExistencia(Integer existencia) {
		this.existencia = existencia;
	}
}
