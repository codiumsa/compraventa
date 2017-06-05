package compraventa.jdbc;

import java.util.List;

import compraventa.Producto;

/**
 * Clase que muestra cómo utilizar {@link ProductosDAO}.
 * 
 * @author jorge
 */
public class Main {

	public static void main(String[] args) {
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
			
			for(Producto p: productos) {
				System.out.println(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
