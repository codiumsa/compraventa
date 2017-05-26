package compraventa;

public class Vendedor extends Persona {
	
	private Producto producto;

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	public String toString() {
		return super.toString() + " y vendo " + producto.getNombre();
	}
}
