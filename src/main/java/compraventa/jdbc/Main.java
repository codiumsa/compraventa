package compraventa.jdbc;

import java.util.List;

import compraventa.Persona;
import compraventa.Producto;

/**
 * Clase que muestra cómo utilizar {@link ProductosDAO}.
 * 
 * @author jorge
 */
public class Main {

	public static void main(String[] args) {
		testProducto();
		testPersona();
	}

	public static void testProducto() {
		ProductosDAO dao = new ProductosDAO();

		try {
			dao.dropTable();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			dao.createTable();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Producto nuevo = new Producto();
			nuevo.setCodigo("BAN01");
			nuevo.setNombre("Banana");
			nuevo.setExistencia(10);
			nuevo.setPrecio(2000L);
			dao.persist(nuevo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<Producto> productos = dao.all();

			for (Producto p : productos) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testPersona() {
		PersonasDAO dao = new PersonasDAO();

		try {
			dao.dropTable();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			dao.createTable();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			Persona nuevo = new Persona();
			nuevo.setNombre("Jorge Ramirez");
			dao.persist(nuevo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			List<Persona> personas = dao.all();

			for (Persona p : personas) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
