package compraventa.jpa;

import java.util.List;

import compraventa.Producto;

/**
 * Clase de pruebas para la capa de acceso a datos utilizando JPA.
 * 
 * @author jorge
 */
public class Main {

	public static void main(String[] args) {
		ProductosDAO dao = new ProductosDAO();
		
		Producto p = new Producto();
		p.setCodigo("BAN01");
		p.setNombre("Banana de Oro");
		p.setPrecio(2000L);
		p.setExistencia(100);
		
		System.out.println("Se inicia la creación del producto...");
		dao.persist(p);
		System.out.println("Producto creado!");
		
		List<Producto> productos = dao.all();
		
		for(Producto producto: productos) {
			System.out.println(producto);
		}
		dao.close();
	}
}
