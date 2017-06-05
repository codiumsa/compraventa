package compraventa;

public class Producto {
	private Long id;
	private String nombre;
	private Integer existencia = 0;
	private String codigo;
	private Long precio;
	
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
		return nombre + " (" + codigo + ") " + existencia + " unidades en stock. Precio: " + precio;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
