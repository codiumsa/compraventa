package compraventa;

public class Persona implements Nombrable {
	private Long id;
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String toString() {
		return "Soy, " + nombre;
	}
	
	public boolean equals(Object otro) {
		Persona p = (Persona) otro;
		return nombre.equals(p.getNombre());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
