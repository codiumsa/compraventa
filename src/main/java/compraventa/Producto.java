package compraventa;

public class Producto {
	private String nombre;
	private Integer existencia = 0;
	private String codigo;
	
	public Producto() {
	}
	
	public Producto(String nombre, Integer existencia) {
		this.nombre = nombre;
		this.existencia = existencia;
	}
	
	public Producto(String codigo, String nombre) {
		this.codigo = codigo;
		this.nombre = nombre;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String toString() {
		return nombre + " " + existencia + " unidades en stock";
	}
}
